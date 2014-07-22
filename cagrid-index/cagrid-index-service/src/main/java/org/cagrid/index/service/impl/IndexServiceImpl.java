package org.cagrid.index.service.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBElement;

import org.cagrid.core.common.JAXBUtils;
import org.cagrid.core.soapclient.ClientConfigurer;
import org.cagrid.index.aggregator.types.AggregatorConfig;
import org.cagrid.index.aggregator.types.AggregatorContent;
import org.cagrid.index.aggregator.types.AggregatorData;
import org.cagrid.index.aggregator.types.AggregatorPollType;
import org.cagrid.index.aggregator.types.GetMultipleResourcePropertiesPollType;
import org.cagrid.index.aggregator.types.GetResourcePropertyPollType;
import org.cagrid.index.aggregator.types.QueryResourcePropertiesPollType;
import org.cagrid.index.rpaccess.client.ResourcePropertyAccessClient;
import org.cagrid.index.service.IndexService;
import org.cagrid.index.service.impl.database.xml.xindice.XindiceIndexDatabase;
import org.cagrid.index.types.BigIndexContent;
import org.cagrid.index.types.BigIndexEntry;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.EntryType;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.AddRefusedFault;
import org.w3c.dom.Element;

public class IndexServiceImpl implements IndexService {

    private static final Logger LOG = Logger.getLogger(IndexServiceImpl.class.getName());

    private Map<String, EntryHolder> entries = new HashMap<String, EntryHolder>();
    private XindiceIndexDatabase db;
    private ScheduledExecutorService executorService;
    private int poolSize = 10;
    private Map<String, ResourcePropertyAccessClient> clientCache;
    private Lock clientCacheLock = new ReentrantLock();

    private ClientConfigurer configurer = null;

    public IndexServiceImpl(ClientConfigurer configurer) {
        LOG.info("Starting up IndexService");
        // initialize database
        initializeDatabase();
        this.executorService = Executors.newScheduledThreadPool(poolSize);
        this.clientCache = new HashMap<String, ResourcePropertyAccessClient>();
        this.configurer = configurer;
    }

    private ResourcePropertyAccessClient getClient(String url) throws Exception {
        try {
            LOG.log(Level.FINEST, "Acquiring client lock, with " + this.clientCache.size() + " entries in cache.");
            this.clientCacheLock.lock();

            if (this.clientCache.containsKey(url)) {
                LOG.log(Level.FINEST, "Found client in cache for:" + url);
                return this.clientCache.get(url);
            } else {
                LOG.log(Level.FINEST, "Didn't find client in cache for:" + url);
                ResourcePropertyAccessClient client = new ResourcePropertyAccessClient(url);
                configurer.configureClient(client);
                this.clientCache.put(url, client);
                LOG.log(Level.FINEST, "Stored client in cache for:" + url);
                return client;
            }
        } finally {
            LOG.log(Level.FINEST, "Releasing client lock.");
            this.clientCacheLock.unlock();
        }
    }

    private boolean removeClient(String url) {
        try {
            this.clientCacheLock.lock();
            if (this.clientCache.remove(url) != null) {
                return true;
            } else {
                return false;
            }
        } finally {
            this.clientCacheLock.unlock();
        }
    }

    public void shutdown() {
        LOG.info("Shutdown called.");
        if (this.executorService != null) {
            LOG.info("Attempting to cancel executor tasks.");
            List<Runnable> runners = this.executorService.shutdownNow();
            LOG.info("Executor cancelled with " + runners.size() + " pending tasks.");
        } else {
            LOG.info("Executor was null.");
        }
    }

    public String add(EntryType entry, Calendar termTime) throws AddRefusedFault {
        String entryId = UUID.randomUUID().toString();

        EntryHolder holder = new EntryHolder();
        holder.setEntryId(entryId);
        holder.setEntry(entry);
        holder.setTerminationTime(termTime);
        LOG.log(Level.FINEST, "Adding entry:" + holder);
        entries.put(entryId, holder);

        // TODO:for content RP
        // BigIndexContent indexContent = new BigIndexContent();
        // indexContent.setContentID(entryId);
        // indexContent.setContent(entry.getContent());

        Object content = entry.getContent();
        if (content instanceof AggregatorContent) {
            AggregatorContent agg = (AggregatorContent) content;
            AggregatorConfig config = agg.getAggregatorConfig();

            AggregatorPollType poll = null;
            for (Object any : config.getAny()) {
                LOG.fine("Processing config of type (" + any.getClass() + ") with info:" + any);

                if (any instanceof JAXBElement) {
                    any = ((JAXBElement) any).getValue();
                    LOG.fine("Found a JAXBElement, extracted value (" + any.getClass() + ") with info:" + any);
                }

                if (any instanceof AggregatorPollType) {
                    poll = (AggregatorPollType) any;
                    if (poll instanceof QueryResourcePropertiesPollType) {
                        LOG.fine("Found query type");
                    } else if (poll instanceof GetResourcePropertyPollType) {
                        LOG.fine("Found get type");
                    } else if (poll instanceof GetMultipleResourcePropertiesPollType) {
                        LOG.fine("Found multi-get type");
                    } else {
                        LOG.log(Level.WARNING, "Unsupported polltype:" + poll);
                    }
                } else {
                    LOG.log(Level.WARNING, "Unknown aggregator type!:" + any);
                }
            }

            if (poll != null) {
                int intervalMS = poll.getPollIntervalMillis();
                LOG.fine("Will poll on interval:" + intervalMS + " ms.");
                // add the schedule to the executor here, as we have a valid/supported poll type now
                AggregatorPollTask pollTask = new AggregatorPollTask(poll, holder);
                ScheduledFuture<?> future = this.executorService.scheduleWithFixedDelay(pollTask, 5000, intervalMS,
                        TimeUnit.MILLISECONDS);
                holder.setScheduledFuture(future);

            } else {
                LOG.log(Level.WARNING, "Unable to process AggregatorConfig!");
                throw new AddRefusedFault("Unable to process AggregatorConfig!");
            }

        } else {
            LOG.log(Level.WARNING, "Unsupported registration content:" + content.getClass());
            throw new AddRefusedFault("Unsupported registration content:" + content.getClass());
        }

        updateDatabaseEntry(entry, entryId);

        return entryId;
    }

    private void updateDatabaseEntry(EntryType entry, String entryId) throws AddRefusedFault {
        String collectionURI = this.db.getDefaultCollectionURI();

        BigIndexEntry bigEntry = new BigIndexEntry();
        bigEntry.setEntry(entry);
        Element element = null;
        try {
            element = JAXBUtils.marshalToElement(bigEntry);
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Unable to marshall entry!", e);
            throw new AddRefusedFault("Unable to marshall entry!", e);
        }

        try {
            if (element != null) {
                this.db.addDocument(collectionURI, entryId, element, false);
                if (LOG.isLoggable(Level.FINEST)) {
                    String document = (String) this.db.getDocument(collectionURI, entryId, true);
                    LOG.finest("Stored doc:" + document);
                }
            } else {
                LOG.log(Level.WARNING, "Can't save a null element to the database!");
            }
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Unable to save entry to database!", e);
            throw new AddRefusedFault("Unable to save entry to database!", e);
        }
    }

    public Calendar getEntryTerminationTime(String entryID) throws ResourceUnknownFault {
        EntryHolder entryHolder = entries.get(entryID);
        if (entryHolder != null) {
            LOG.log(Level.FINEST, "Request to find get termination time for entry:" + entryHolder);
            return entryHolder.getTerminationTime();
        } else {
            LOG.log(Level.FINEST, "Unable to find entry for request to get termination time:" + entryID);
            throw new ResourceUnknownFault();
        }
    }

    public Calendar setEntryTerminationTime(String entryID, Calendar termTime) throws ResourceUnknownFault {
        EntryHolder entryHolder = entries.get(entryID);
        if (entryHolder != null) {
            Calendar oldTermTime = entryHolder.getTerminationTime();
            entryHolder.setTerminationTime(termTime);
            LOG.log(Level.FINEST,
                    "Changed termination time from (" + oldTermTime.getTime() + ") to (" + termTime.getTime()
                            + ") for entry:" + entryID);
            return oldTermTime;
        } else {
            LOG.log(Level.FINEST, "Unable to find entry for request to set termination time:" + entryID);
            throw new ResourceUnknownFault();
        }
    }

    @Override
    public EntryType getEntry(String entryID) throws ResourceUnknownFault {
        EntryHolder entryHolder = entries.get(entryID);
        if (entryHolder != null) {
            return entryHolder.getEntry();
        } else {
            LOG.log(Level.FINEST, "Unable to find entry for request to get entry:" + entryID);
            throw new ResourceUnknownFault();
        }
    }

    public XindiceIndexDatabase getDatabase() {
        return this.db;
    }

    @Override
    public List<BigIndexContent> getContent(List<String> contentIDs) {
        // get db instance
        XindiceIndexDatabase db = getDatabase();
        if (db == null) {
            throw new RuntimeException("Error: database instance is null");
        }
        if (contentIDs == null) {
            throw new IllegalArgumentException("No contentIDs provided!");
        }

        String colURI = db.getDefaultCollectionURI();
        List<BigIndexContent> results = new ArrayList<BigIndexContent>(contentIDs.size());

        for (String id : contentIDs) {
            try {
                String doc = (String) db.getDocument(colURI, id, true);
                BigIndexEntry bigEntry = JAXBUtils.unmarshal(BigIndexEntry.class, doc);
                BigIndexContent content = new BigIndexContent();
                content.setContentID(id);
                EntryType entry = bigEntry.getEntry();
                if (entry != null) {
                    content.setContent(entry.getContent());
                }
                results.add(content);
            } catch (Exception e) {
                LOG.log(Level.FINE, "Exception getting a document from the database: " + e.getMessage(), e);
                continue;
            }
        }

        return results;
    }

    private void initializeDatabase() {
        // initialize database
        // TODO: what to use here? use a config property?
        // MessageContext context = MessageContext.getCurrentContext();
        // String serviceName = ContextUtils.getTargetServicePath(context);
        // String serviceAddr = ServiceHost.getHost() + "." + Integer.toString(ServiceHost.getPort());
        // String rootCollectionName = serviceAddr + "." + serviceName;

        String rootCollectionName = this.getClass().getName() + ".XMLDB";
        this.db = new XindiceIndexDatabase(rootCollectionName);

        LOG.fine("Initialized database rootCollection: " + rootCollectionName);

        // TODO: what is this actually for? seems to shutdown the db manager
        // this.checkpointThread.setDaemon(true);
        // this.checkpointThread.start();
    }

    /** Gets rid of dead registrations */
    public void sweepEntries() {
        // statistics for this sweep run
        int totalSwept = 0;
        int totalKept = 0;
        int totalRemoved = 0;

        LOG.info("Starting sweep");

        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.FULL);
        Iterator<Entry<String, EntryHolder>> iter = entries.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, EntryHolder> entry = iter.next();
            EntryHolder entryResource = entry.getValue();
            totalSwept++;
            try {

                // ResourceKey entryResourceKey = (ResourceKey) entryResource.getEntryId() getKey();
                Calendar now = new GregorianCalendar();
                Calendar termination = entryResource.getTerminationTime();

                if (termination == null) {
                    LOG.finest("Entry has infinite lifetime: " + entryResource.getEntry().getMemberServiceEPR());
                } else {
                    LOG.finest("Checking entry: now=" + dateFormat.format(now.getTime()) + ", termination="
                            + dateFormat.format(termination.getTime()));

                    // if termination time is in past
                    if (now.after(termination)) {
                        LOG.fine("Removing entry (" + entryResource.getEntryId() + ") with address: "
                                + getURLSafe(entryResource.getEntry()) + " as termination time="
                                + dateFormat.format(termination.getTime()) + " is in the past.");
                        // this will remove the entry from the map
                        iter.remove();
                        // cancel its query job
                        entryResource.getScheduledFuture().cancel(true);
                        // remove the cached client; should I store EPRs instead?
                        removeClient(getURLSafe(entryResource.getEntry()));
                        try {
                            this.db.removeDocument(this.db.getDefaultCollectionURI(), entryResource.getEntryId());
                        } catch (Exception e) {
                            LOG.warning("Error while removing document from database:" + e);
                        }

                        totalRemoved++;
                    } else {
                        totalKept++;
                    }
                }
            } catch (Exception e) {
                LOG.log(Level.WARNING, "Exception in entry sweeper thread: ", e);
                break;
            }
        }
        if (totalRemoved > 0) {
            // TODO: for ws-notification
            // notifyChange();
        }
        LOG.info("Finished sweep, with " + totalKept + " entries kept, " + totalRemoved + " entries removed, out of "
                + totalSwept + " entries.");
        if (LOG.isLoggable(Level.FINEST)) {
            try {
                LOG.log(Level.FINEST,
                        "Found document ids:"
                                + Arrays.toString(this.db.listDocuments(this.db.getDefaultCollectionURI())));
            } catch (Exception e) {
                LOG.log(Level.FINEST, "Problem listing document ids", e);
            }
        }
    }

    private String getURLSafe(EntryType entry) {
        if (entry != null && entry.getMemberServiceEPR() != null && entry.getMemberServiceEPR().getAddress() != null
                && entry.getMemberServiceEPR().getAddress().getValue() != null) {
            return entry.getMemberServiceEPR().getAddress().getValue().trim();
        } else {
            return null;
        }
    }

    @Override
    public List<Element> query(String queryStr) throws Exception {
        // get db instance
        XindiceIndexDatabase db = getDatabase();
        if (db == null) {
            throw new RuntimeException("Error: database instance is null");
        }

        // TODO: fix this awful use of "throw new Exception() in globus code"
        return db.query(queryStr, null);
    }

    class AggregatorPollTask implements Runnable {

        private EntryHolder entry;
        private AggregatorPollType poll;

        public AggregatorPollTask(AggregatorPollType poll, EntryHolder entry) {
            this.poll = poll;
            this.entry = entry;
        }

        @Override
        public void run() {

            try {
                String memberURL = getURLSafe(entry.getEntry());
                ResourcePropertyAccessClient client = null;
                try {
                    client = getClient(memberURL);
                } catch (Exception e) {
                    LOG.log(Level.WARNING, "Problem getting client for entry:" + entry, e);
                    return;
                }
                List<Object> result = null;
                if (poll instanceof QueryResourcePropertiesPollType) {
                    QueryResourcePropertiesPollType queryType = (QueryResourcePropertiesPollType) poll;
                    LOG.fine("Executing query type [" + queryType.getQueryExpression() + "] for entry:" + memberURL);
                    try {
                        result = client.queryResourceProperties(queryType.getQueryExpression());
                    } catch (UnknownQueryExpressionDialectFault e) {
                        LOG.log(Level.WARNING, "Problem with query dialect:"
                                + queryType.getQueryExpression().getDialect(), e);
                    } catch (InvalidQueryExpressionFault e) {
                        LOG.log(Level.WARNING, "Problem executing query.", e);
                    } catch (QueryEvaluationErrorFault e) {
                        LOG.log(Level.WARNING, "Problem executing query.", e);
                    } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault e) {
                        LOG.log(Level.WARNING, "Problem executing query.", e);
                    } catch (InvalidResourcePropertyQNameFault e) {
                        LOG.log(Level.WARNING, "Problem executing query.", e);
                    }
                } else if (poll instanceof GetResourcePropertyPollType) {
                    GetResourcePropertyPollType getType = (GetResourcePropertyPollType) poll;
                    LOG.fine("Executing get type [" + getType.getResourcePropertyName() + "] for entry:" + memberURL);
                    try {
                        result = client.getResourceProperty(getType.getResourcePropertyName());
                    } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault e) {
                        LOG.log(Level.WARNING, "Problem executing get.", e);
                    } catch (InvalidResourcePropertyQNameFault e) {
                        LOG.log(Level.WARNING, "Problem executing get.", e);
                    }

                } else if (poll instanceof GetMultipleResourcePropertiesPollType) {
                    GetMultipleResourcePropertiesPollType getType = (GetMultipleResourcePropertiesPollType) poll;
                    LOG.fine("Executing multi-get type for entry:" + memberURL);
                    try {
                        result = client.getMultipleResourceProperties(getType.getResourcePropertyNames());
                    } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault e) {
                        LOG.log(Level.WARNING, "Problem executing get.", e);
                    } catch (InvalidResourcePropertyQNameFault e) {
                        LOG.log(Level.WARNING, "Problem executing get.", e);
                    }
                } else {
                    LOG.log(Level.WARNING, "Unsupported polltype:" + poll);
                }

                if (result != null) {
                    if (LOG.isLoggable(Level.FINEST)) {
                        LOG.finest("Got results:" + Arrays.toString(result.toArray()));
                    }

                    Object val = null;
                    if (result.size() == 1) {
                        if (result.get(0) instanceof JAXBElement) {
                            val = ((JAXBElement) result.get(0)).getValue();
                            LOG.fine("Found a JAXBElement, extracted value (" + result.get(0).getClass()
                                    + ") with info:" + val);
                        } else if (result.get(0) instanceof Element) {
                            val = result.get(0);
                            LOG.fine("Found an Element with info:" + val);
                        }
                    } else {
                        // TODO:handle multiple results; do I create an element and append?
                        LOG.fine("Unable to support multiple values.");
                    }

                    if (val != null) {

                        Object content = this.entry.getEntry().getContent();
                        if (content instanceof AggregatorContent) {
                            synchronized (this) {
                                AggregatorContent agg = (AggregatorContent) content;
                                AggregatorData value = agg.getAggregatorData();
                                value.getAny().clear();
                                value.getAny().add(val);
                                agg.setAggregatorData(value);
                                updateDatabaseEntry(entry.getEntry(), entry.getEntryId());
                                // clear the in-memory version once we've stored it in the database
                                value.getAny().clear();
                            }
                        } else {
                            LOG.fine("Unexpected content type, unable to update!");
                        }

                    } else {
                        LOG.fine("Unable to extract value, skipping setting of content.");
                    }
                } else {
                    LOG.log(Level.WARNING, "Got no results for polltype:" + poll);
                }

            } catch (Exception e) {
                LOG.log(Level.WARNING, "Unhandled exception in  poll task:" + poll, e);
            }
        }
    }
}

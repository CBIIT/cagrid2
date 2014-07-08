package org.cagrid.index.service.impl;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.cagrid.core.common.JAXBUtils;
import org.cagrid.index.service.IndexService;
import org.cagrid.index.service.impl.database.xml.xindice.XindiceIndexDatabase;
import org.cagrid.index.types.BigIndexEntry;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.EntryType;
import org.w3c.dom.Element;

public class IndexServiceImpl implements IndexService {

    // TODO: query impl
    private static final Logger LOG = Logger.getLogger(IndexServiceImpl.class.getName());

    // TODO: replace this with the xml db
    private Map<String, EntryHolder> entries = new HashMap<String, EntryHolder>();
    private XindiceIndexDatabase db;

    public IndexServiceImpl() {
        LOG.info("Starting up IndexService");
        // initialize database
        this.initializeDatabase();
    }

    public String add(EntryType entry, Calendar termTime) {
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

        // this.db.

        String collectionURI = this.db.getDefaultCollectionURI();

        BigIndexEntry bigEntry = new BigIndexEntry();
        bigEntry.setEntry(entry);
        // String document = ObjectSerializer.toString(bigEntry, bigEntry.getTypeDesc().getXmlType());
        Element element = null;
        try {
            element = JAXBUtils.marshalToElement(bigEntry);
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Unable to marshall entry!", e);
        }

        try {
            if (element != null) {
                this.db.addDocument(collectionURI, entryId, element, false);

                if (LOG.isLoggable(Level.FINEST)) {
                    String document = (String) this.db.getDocument(collectionURI, entryId, true);
                    LOG.finest("Stored doc:" + document);
                }
            }
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Unable to save entry to database!", e);
        }

        return entryId;
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

        for (String key : entries.keySet()) {
            totalSwept++;
            try {

                EntryHolder entryResource = entries.get(key);
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
                        LOG.fine("Removing entry (" + key + ") with address: "
                                + entryResource.getEntry().getMemberServiceEPR().getAddress().getValue()
                                + " as termination time=" + dateFormat.format(termination.getTime())
                                + " is in the past.");
                        entries.remove(key);
                        try {
                            this.db.removeDocument(this.db.getDefaultCollectionURI(), key);
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
}

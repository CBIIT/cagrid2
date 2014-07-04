package org.cagrid.index.service.impl;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.cagrid.index.service.IndexService;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.EntryType;

public class IndexServiceImpl implements IndexService {

    // TODO: query impl
    private static final Logger LOG = Logger.getLogger(IndexServiceImpl.class.getName());

    // TODO: replace this with the xml db
    private Map<String, EntryHolder> entries = new HashMap<String, EntryHolder>();

    public IndexServiceImpl() {
        LOG.info("Starting up IndexService");
    }

    public String add(EntryType entry, Calendar termTime) {
        String entryId = UUID.randomUUID().toString();

        EntryHolder holder = new EntryHolder();
        holder.setEntryId(entryId);
        holder.setEntry(entry);
        holder.setTerminationTime(termTime);
        LOG.log(Level.FINEST, "Adding entry:" + holder);
        entries.put(entryId, holder);

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

                        // TODO: do i need to do something like this?
                        // try {
                        // AggregatorSource source = entryResource.getAggregatorSource();
                        // if (source != null) {
                        // source.removeAggregation(sessionKey);
                        // }
                        // } catch (Exception e) {
                        // LOG.warn("Error while removing aggregation" + e);
                        // }
                        // try {
                        // entryResource.remove();
                        // } catch (Exception e) {
                        // LOG.warn("Error while invoking resource remove method: " + e);
                        // }
                        // entries.remove(entryResourceKey);
                        // sessionKeys.remove(entryResourceKey);

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
    }
}

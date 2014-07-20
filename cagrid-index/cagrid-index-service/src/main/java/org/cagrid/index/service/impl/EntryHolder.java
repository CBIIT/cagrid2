package org.cagrid.index.service.impl;

import java.util.Calendar;
import java.util.TimerTask;

import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.EntryType;

public class EntryHolder {
    private String entryId;
    private EntryType entry;
    private Calendar termTime;
    private TimerTask task;

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public EntryType getEntry() {
        return entry;
    }

    public void setEntry(EntryType entry) {
        this.entry = entry;
    }

    public Calendar getTerminationTime() {
        return termTime;
    }

    public void setTerminationTime(Calendar termTime) {
        this.termTime = termTime;
    }

    @Override
    public String toString() {
        return "EntryHolder [entryId=" + entryId + ", entry=" + entry + ", termTime=" + termTime + "]";
    }

    public TimerTask getTask() {
        return task;
    }

    public void setTask(TimerTask task) {
        this.task = task;
    }
}

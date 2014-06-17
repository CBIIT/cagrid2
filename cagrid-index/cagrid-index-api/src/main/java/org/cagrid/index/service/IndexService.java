package org.cagrid.index.service;

import java.util.Calendar;

import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.EntryType;

public interface IndexService {

    String add(EntryType entry, Calendar termTime);
    
    public EntryType getEntry(String entryID) throws ResourceUnknownFault;

    public Calendar getEntryTerminationTime(String entryID) throws ResourceUnknownFault;

    public Calendar setEntryTerminationTime(String entryID, Calendar termTime) throws ResourceUnknownFault;

}

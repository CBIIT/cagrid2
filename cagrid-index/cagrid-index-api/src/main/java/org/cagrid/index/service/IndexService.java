package org.cagrid.index.service;

import java.util.Calendar;
import java.util.List;

import org.cagrid.index.types.BigIndexContent;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.EntryType;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.AddRefusedFault;
import org.w3c.dom.Element;

public interface IndexService {

    String add(EntryType entry, Calendar termTime) throws AddRefusedFault;

    public EntryType getEntry(String entryID) throws ResourceUnknownFault;

    public Calendar getEntryTerminationTime(String entryID) throws ResourceUnknownFault;

    public Calendar setEntryTerminationTime(String entryID, Calendar termTime) throws ResourceUnknownFault;

    public List<BigIndexContent> getContent(List<String> contentIds);

    // TODO: fix the awful use of Exception inherited from Globus code
    public List<Element> query(String queryStr) throws Exception;

}

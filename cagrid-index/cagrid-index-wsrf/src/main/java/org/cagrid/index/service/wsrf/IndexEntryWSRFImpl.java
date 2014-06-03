package org.cagrid.index.service.wsrf;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.jaxws.context.WrappedMessageContext;
import org.apache.cxf.message.Message;
import org.cagrid.index.service.IndexService;
import org.cagrid.index.wsrf.stubs.BigIndexEntryPortTypeImpl;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.TerminationTimeChangeRejectedFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.UnableToSetTerminationTimeFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourceProperties;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourcePropertiesResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class IndexEntryWSRFImpl extends BigIndexEntryPortTypeImpl {

    public static final String BIGINDEX_NS = "http://mds.globus.org/bigindex/2008/11/24";
    public static final QName ENTRY_KEY = new QName(BIGINDEX_NS, "ServiceGroupEntryKey");

    private static final Logger LOG = Logger.getLogger(IndexEntryWSRFImpl.class.getName());

    private IndexService indexService;

    @javax.annotation.Resource
    private WebServiceContext wsContext;

    public IndexEntryWSRFImpl(IndexService indexService) {
        this.indexService = indexService;
    }

    @Override
    public GetMultipleResourcePropertiesResponse getMultipleResourceProperties(
            GetMultipleResourceProperties getMultipleResourcePropertiesRequest) throws ResourceUnknownFault,
            InvalidResourcePropertyQNameFault {
        // TODO Auto-generated method stub
        return super.getMultipleResourceProperties(getMultipleResourcePropertiesRequest);
    }

    @Override
    public GetResourcePropertyResponse getResourceProperty(QName getResourcePropertyRequest)
            throws ResourceUnknownFault, InvalidResourcePropertyQNameFault {
        // TODO Auto-generated method stub
        return super.getResourceProperty(getResourcePropertyRequest);
    }

    @Override
    public QueryResourcePropertiesResponse queryResourceProperties(
            QueryResourceProperties queryResourcePropertiesRequest) throws UnknownQueryExpressionDialectFault,
            InvalidQueryExpressionFault, QueryEvaluationErrorFault, ResourceUnknownFault,
            InvalidResourcePropertyQNameFault {
        // TODO Auto-generated method stub
        return super.queryResourceProperties(queryResourcePropertiesRequest);
    }

    @Override
    public void setTerminationTime(Calendar requestedTerminationTime, Holder<Calendar> newTerminationTime,
            Holder<Calendar> currentTime) throws TerminationTimeChangeRejectedFault,
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault,
            UnableToSetTerminationTimeFault {

        String entryId = extractEntryIdFromHeaders();
        if (entryId == null) {
            throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault();
        }
        this.indexService.setEntryTerminationTime(entryId, requestedTerminationTime);
        newTerminationTime.value=requestedTerminationTime;
        currentTime.value=new GregorianCalendar();
    }

    private List<Header> getHeaders() {
        MessageContext messageContext = wsContext.getMessageContext();
        if (messageContext == null || !(messageContext instanceof WrappedMessageContext)) {
            return null;
        }

        Message message = ((WrappedMessageContext) messageContext).getWrappedMessage();
        List<Header> headers = CastUtils.cast((List<?>) message.get(Header.HEADER_LIST));
        return headers;
    }

    private String extractEntryIdFromHeaders() {
        String did = null;

        List<Header> headers = getHeaders();
        if (headers != null) {
            for (Header h : headers) {
                QName hName = h.getName();
                if (hName.equals(ENTRY_KEY)) {
                    LOG.finest("Found resource key.");
                    Object o = h.getObject();
                    did = getEntryId((Node) o);
                    if (did != null) {
                        break;
                    }
                } else {
                    LOG.finest("Ignoring header:" + hName);
                }
            }
        } else {
            LOG.log(Level.WARNING, "Unable to locate SOAP headers.");
            // throw new CDSInternalFaultFaultMessage("Unable to locate SOAP headers.");
        }
        return did;
    }

    private String getEntryId(Node parent) {
        if (parent == null) {
            return null;
        }

        if (parent.getLocalName() != null && parent.getLocalName().equals("EntryKey")) {
            LOG.finest("Found entry id: " + parent.getTextContent());
            return parent.getTextContent();
        }

        if (parent.hasChildNodes()) {
            NodeList nl = parent.getChildNodes();
            for (int i = 0; i < nl.getLength(); i++) {
                String entryId = getEntryId(nl.item(i));
                if (entryId != null) {
                    return entryId;
                }
            }
        }

        return null;
    }

    // <ns9:ServiceGroupEntryKey xmlns:ns9="http://mds.globus.org/bigindex/2008/11/24"
    // xmlns="http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd"
    // xmlns:ns2="http://schemas.xmlsoap.org/ws/2004/03/addressing"
    // xmlns:ns3="http://mds.globus.org/metrics/2004/09"
    // xmlns:ns4="http://mds.globus.org/aggregator/types"
    // xmlns:ns5="http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-BaseFaults-1.2-draft-01.xsd"
    // xmlns:ns6="http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd">
    // <ns4:GroupKey>8c4b1ed4-e2dc-4397-bbf4-28babb18449e</ns4:GroupKey>
    // <ns4:EntryKey>5db77e55-98a3-4762-a0b8-54aba932b27f</ns4:EntryKey>
    // </ns9:ServiceGroupEntryKey>

}

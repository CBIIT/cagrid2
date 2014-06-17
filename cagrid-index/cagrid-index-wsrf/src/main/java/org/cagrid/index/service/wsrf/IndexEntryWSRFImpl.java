package org.cagrid.index.service.wsrf;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.jaxws.context.WrappedMessageContext;
import org.apache.cxf.message.Message;
import org.cagrid.core.common.JAXBUtils;
import org.cagrid.core.resource.ExternalSingletonResourceProperty;
import org.cagrid.core.resource.ExternalSingletonResourcePropertyValue;
import org.cagrid.core.resource.ResourceImpl;
import org.cagrid.core.resource.ResourcePropertyDescriptor;
import org.cagrid.index.service.IndexService;
import org.cagrid.index.wsrf.stubs.BigIndexEntryPortTypeImpl;
import org.cagrid.index.wsrf.stubs.BigIndexResourceProperties;
import org.cagrid.wsrf.properties.Resource;
import org.cagrid.wsrf.properties.ResourceProperty;
import org.cagrid.wsrf.properties.ResourcePropertySet;
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
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.EntryType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class IndexEntryWSRFImpl extends BigIndexEntryPortTypeImpl {

    public static final String BIGINDEX_NS = "http://mds.globus.org/bigindex/2008/11/24";
    public static final QName ENTRY_KEY = new QName(BIGINDEX_NS, "ServiceGroupEntryKey");
    public static final QName RP_SET = new QName("http://mds.globus.org/aggregator", "AggregatorServiceGroupEntryRP");

    private static final Logger LOG = Logger.getLogger(IndexEntryWSRFImpl.class.getName());
    private DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.FULL);

    private IndexService indexService;
    private Resource resourceFacade;

    @javax.annotation.Resource
    private WebServiceContext wsContext;

    public IndexEntryWSRFImpl(IndexService indexService) {
        this.indexService = indexService;
        resourceFacade = getResourceFacade();
    }

    private Resource getResourceFacade() {
        ResourceImpl resource = new ResourceImpl(RP_SET);
        try {
            // What resource properties should we know about?
            Collection<ResourcePropertyDescriptor<?>> resourcePropertyDescriptors = ResourcePropertyDescriptor
                    .analyzeResourcePropertiesHolder(BigIndexResourceProperties.class);

            // Map them by field.
            Map<String, ResourcePropertyDescriptor<?>> descriptorsByField = ResourcePropertyDescriptor
                    .mapByField(resourcePropertyDescriptors);

            {
                @SuppressWarnings("unchecked")
                ResourcePropertyDescriptor<Calendar> timeDescriptor = (ResourcePropertyDescriptor<Calendar>) descriptorsByField
                        .get("currentTime");
                if (timeDescriptor != null) {
                    ExternalSingletonResourcePropertyValue<Calendar> propertyValue = new ExternalSingletonResourcePropertyValue<Calendar>() {
                        @Override
                        public Calendar getPropertyValue() {
                            return getCurrentTime();
                        }
                    };
                    ResourceProperty<Calendar> resourceProperty = new ExternalSingletonResourceProperty<Calendar>(
                            timeDescriptor, propertyValue);
                    resource.add(resourceProperty);
                }
            }

            {
                @SuppressWarnings("unchecked")
                ResourcePropertyDescriptor<Calendar> termTimeDescriptor = (ResourcePropertyDescriptor<Calendar>) descriptorsByField
                        .get("terminationTime");
                if (termTimeDescriptor != null) {
                    ExternalSingletonResourcePropertyValue<Calendar> propertyValue = new ExternalSingletonResourcePropertyValue<Calendar>() {
                        @Override
                        public Calendar getPropertyValue() {
                            try {
                                return getCurrentResourceTermintationTime();
                            } catch (ResourceUnknownFault e) {
                                return null;
                            }
                        }
                    };
                    ResourceProperty<Calendar> resourceProperty = new ExternalSingletonResourceProperty<Calendar>(
                            termTimeDescriptor, propertyValue);
                    resource.add(resourceProperty);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resource;
    }

    protected Calendar getCurrentResourceTermintationTime() throws ResourceUnknownFault {
        String entryId = extractEntryIdFromHeaders();
        if (entryId == null) {
            throw new ResourceUnknownFault("Unable to extract key from headers.");
        }
        try {
            return this.indexService.getEntryTerminationTime(entryId);
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault e) {
            throw new ResourceUnknownFault("Unable to locate entry" + entryId);
        }

    }

    @Override
    public GetMultipleResourcePropertiesResponse getMultipleResourceProperties(
            GetMultipleResourceProperties getMultipleResourcePropertiesRequest) throws ResourceUnknownFault,
            InvalidResourcePropertyQNameFault {
        LOG.info("getMultipleResourceProperty " + getMultipleResourcePropertiesRequest.getResourceProperty());
        GetMultipleResourcePropertiesResponse response = new GetMultipleResourcePropertiesResponse();

        // this handles throwing ResourceUnknownFault, as the facade doesn't
        extractAndValidateEntryKey();

        for (Iterator<QName> iterator = getMultipleResourcePropertiesRequest.getResourceProperty().iterator(); iterator
                .hasNext();) {
            QName qname = iterator.next();
            if (resourceFacade instanceof ResourcePropertySet) {
                ResourcePropertySet resourcePropertySet = (ResourcePropertySet) resourceFacade;
                ResourceProperty<?> resourceProperty = resourcePropertySet.get(qname);
                if (resourceProperty != null) {
                    Object resourcePropertyValue = resourceProperty.get(0);
                    LOG.info("getResourceProperty " + qname + " value is " + resourcePropertyValue);
                    if (resourcePropertyValue == null) {
                        resourcePropertyValue = new JAXBElement(resourceProperty.getMetaData().getName(),
                                resourceProperty.getMetaData().getType(), null);
                    } else if (!(resourcePropertyValue instanceof Node)
                            && !(resourcePropertyValue instanceof JAXBElement<?>)) {
                        // TODO: what to do for other types
                        resourcePropertyValue = JAXBUtils.wrap(resourcePropertyValue, resourceProperty.getMetaData()
                                .getName());
                    }
                    LOG.info("getResourceProperty " + qname + " returning " + resourcePropertyValue);
                    response.getAny().add(resourcePropertyValue);
                }
            } else {
                throw new InvalidResourcePropertyQNameFault("Unsupported resource key:" + qname);
            }
        }

        return response;
    }

    @Override
    public GetResourcePropertyResponse getResourceProperty(QName resourcePropertyQName) throws ResourceUnknownFault,
            InvalidResourcePropertyQNameFault {
        LOG.info("getResourceProperty " + resourcePropertyQName);
        GetResourcePropertyResponse response = null;
        // this handles throwing ResourceUnknownFault, as the facade doesn't
        extractAndValidateEntryKey();

        if (resourceFacade instanceof ResourcePropertySet) {
            ResourcePropertySet resourcePropertySet = (ResourcePropertySet) resourceFacade;
            ResourceProperty<?> resourceProperty = resourcePropertySet.get(resourcePropertyQName);
            if (resourceProperty != null) {
                Object resourcePropertyValue = resourceProperty.get(0);
                LOG.info("getResourceProperty " + resourcePropertyQName + " returning " + resourcePropertyValue);
                if (resourcePropertyValue == null) {
                    resourcePropertyValue = new JAXBElement(resourceProperty.getMetaData().getName(), resourceProperty
                            .getMetaData().getType(), null);
                } else if (!(resourcePropertyValue instanceof Node)
                        && !(resourcePropertyValue instanceof JAXBElement<?>)) {
                    // TODO: what to do for other types
                    resourcePropertyValue = JAXBUtils.wrap(resourcePropertyValue, resourceProperty.getMetaData()
                            .getName());
                }
                response = new GetResourcePropertyResponse();
                response.getAny().add(resourcePropertyValue);
            }
        }

        if ((response == null)) {
            throw new InvalidResourcePropertyQNameFault("No resource for '" + resourcePropertyQName + "'");
        }
        return response;

    }

    private EntryType extractAndValidateEntryKey() throws ResourceUnknownFault {
        EntryType entry;
        String entryId = extractEntryIdFromHeaders();
        if (entryId == null) {
            throw new ResourceUnknownFault("Unable to extract key from headers.");
        }
        try {
            entry = this.indexService.getEntry(entryId);
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault ruf) {
            throw new ResourceUnknownFault("Unable to locate entry" + entryId);
        }
        return entry;
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
        newTerminationTime.value = requestedTerminationTime;
        currentTime.value = new GregorianCalendar();
    }

    public Calendar getCurrentTime() {
        Calendar now = Calendar.getInstance();
        LOG.finest("getCurrentTime()=" + dateFormat.format(now.getTime()));
        return now;
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

package org.cagrid.index.service.wsrf;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.cagrid.core.common.JAXBUtils;
import org.cagrid.core.resource.ExternalSingletonResourceProperty;
import org.cagrid.core.resource.ExternalSingletonResourcePropertyValue;
import org.cagrid.core.resource.ResourceImpl;
import org.cagrid.core.resource.ResourcePropertyDescriptor;
import org.cagrid.core.resource.SimpleResourceKey;
import org.cagrid.core.resource.SingletonResourceHomeImpl;
import org.cagrid.index.aggregator.types.AggregatorConfig;
import org.cagrid.index.aggregator.types.AggregatorContent;
import org.cagrid.index.aggregator.types.PairedKeyType;
import org.cagrid.index.aggregator.utils.AggregatorUtils;
import org.cagrid.index.service.IndexService;
import org.cagrid.index.types.BigIndexContentIDList;
import org.cagrid.index.wsrf.stubs.BigIndexPortTypeImpl;
import org.cagrid.index.wsrf.stubs.BigIndexResourceProperties;
import org.cagrid.index.wsrf.stubs.GetContentResponse;
import org.cagrid.wsrf.properties.InvalidResourceKeyException;
import org.cagrid.wsrf.properties.NoSuchResourceException;
import org.cagrid.wsrf.properties.Resource;
import org.cagrid.wsrf.properties.ResourceException;
import org.cagrid.wsrf.properties.ResourceHome;
import org.cagrid.wsrf.properties.ResourceKey;
import org.cagrid.wsrf.properties.ResourceProperty;
import org.cagrid.wsrf.properties.ResourcePropertySet;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceNotDestroyedFault;
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
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.Add;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.EntryType;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.AddRefusedFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.ContentCreationFailedFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.UnsupportedMemberInterfaceFault;
import org.w3c.dom.Node;
import org.xmlsoap.schemas.ws._2004._03.addressing.AttributedURI;
import org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType;
import org.xmlsoap.schemas.ws._2004._03.addressing.ReferencePropertiesType;

public class IndexWSRFImpl extends BigIndexPortTypeImpl {
    static public final QName KEY = new QName("http://mds.globus.org/inmemoryservicegroup", "ServiceGroupKey");
    private ResourceKey key = new SimpleResourceKey(KEY, UUID.randomUUID().toString());

    private static final Logger LOG = Logger.getLogger(IndexWSRFImpl.class.getName());

    private IndexService indexService;
    private ResourceHome resourceHome;
    private DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.FULL);

    @javax.annotation.Resource
    private WebServiceContext wsContext;

    public IndexWSRFImpl(IndexService indexService) {
        this.indexService = indexService;
        this.resourceHome = getResourceHome();
    }

    private ResourceHome getResourceHome() {
        ResourceImpl resource = new ResourceImpl(null);
        ResourceHome resourceHome = new SingletonResourceHomeImpl(resource);
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
                            // we don't really support termination
                            return null;
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
        return resourceHome;
    }

    @Override
    public GetMultipleResourcePropertiesResponse getMultipleResourceProperties(
            GetMultipleResourceProperties getMultipleResourcePropertiesRequest) throws ResourceUnknownFault,
            InvalidResourcePropertyQNameFault {
        LOG.info("getMultipleResourceProperty " + getMultipleResourcePropertiesRequest.getResourceProperty());
        GetMultipleResourcePropertiesResponse response = new GetMultipleResourcePropertiesResponse();
        for (Iterator<QName> iterator = getMultipleResourcePropertiesRequest.getResourceProperty().iterator(); iterator
                .hasNext();) {
            QName qname = iterator.next();
            Exception e;
            try {
                Resource resource = resourceHome.find(null);
                if (resource instanceof ResourcePropertySet) {
                    ResourcePropertySet resourcePropertySet = (ResourcePropertySet) resource;
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
                            resourcePropertyValue = JAXBUtils.wrap(resourcePropertyValue, resourceProperty
                                    .getMetaData().getName());
                        }
                        LOG.info("getResourceProperty " + qname + " returning " + resourcePropertyValue);
                        response.getAny().add(resourcePropertyValue);
                    }
                }
            } catch (NoSuchResourceException nsre) {
                e = nsre;
            } catch (InvalidResourceKeyException irke) {
                e = irke;
            } catch (ResourceException re) {
                e = re;
            }
        }

        return response;
    }

    @Override
    public GetResourcePropertyResponse getResourceProperty(QName resourcePropertyQName) throws ResourceUnknownFault,
            InvalidResourcePropertyQNameFault {
        LOG.info("getResourceProperty " + resourcePropertyQName);
        Exception e = null;
        GetResourcePropertyResponse response = null;
        try {
            Resource resource = resourceHome.find(null);
            if (resource instanceof ResourcePropertySet) {
                ResourcePropertySet resourcePropertySet = (ResourcePropertySet) resource;
                ResourceProperty<?> resourceProperty = resourcePropertySet.get(resourcePropertyQName);
                if (resourceProperty != null) {
                    Object resourcePropertyValue = resourceProperty.get(0);
                    LOG.info("getResourceProperty " + resourcePropertyQName + " returning " + resourcePropertyValue);
                    if (resourcePropertyValue == null) {
                        resourcePropertyValue = new JAXBElement(resourceProperty.getMetaData().getName(),
                                resourceProperty.getMetaData().getType(), null);
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
        } catch (NoSuchResourceException nsre) {
            e = nsre;
        } catch (InvalidResourceKeyException irke) {
            e = irke;
        } catch (ResourceException re) {
            e = re;
        }
        if ((response == null) || (e != null)) {
            throw new ResourceUnknownFault("No resource for '" + resourcePropertyQName + "'", e);
        }
        return response;
    }

    @Override
    public GetContentResponse getContent(BigIndexContentIDList getContentRequest) {
        // TODO: pull the content from the service
        // TODO Auto-generated method stub
        return super.getContent(getContentRequest);
    }

    public Calendar getCurrentTime() {
        Calendar now = Calendar.getInstance();
        LOG.finest("getCurrentTime()=" + dateFormat.format(now.getTime()));
        return now;
    }

    @Override
    public EndpointReferenceType add(Add addRequest) throws ContentCreationFailedFault,
            UnsupportedMemberInterfaceFault, AddRefusedFault {
        EndpointReferenceType memberEPR = addRequest.getMemberEPR();
        Calendar termTime = addRequest.getInitialTerminationTime();
        Object content = addRequest.getContent();

        LOG.info("Request to add:" + memberEPR.getAddress().getValue() + " with terminiation time of:"
                + termTime.getTime() + " using content:" + content);

        if (content instanceof AggregatorContent) {
            AggregatorContent aggCon = (AggregatorContent) content;
            AggregatorConfig config = aggCon.getAggregatorConfig();
            // TODO: handle config
            LOG.info("Add using config:" + config);
        } else {
            LOG.warning("Got request to add using unsupported content type:" + content.getClass() + ", refusing add.");
            throw new AddRefusedFault("Unsupported content type:" + content);
        }

        MessageContext msgContext = wsContext.getMessageContext();
        HttpServletRequest request = (HttpServletRequest) msgContext.get("HTTP.REQUEST");
        String transportURL = request.getRequestURL().toString();

        // EndpointReferenceType entryEPR = null;
        EndpointReferenceType serviceGroupEPR = null;
        serviceGroupEPR = createEndpointReference(transportURL, null);

        if (AggregatorUtils.detectLoopback(memberEPR, serviceGroupEPR)) {
            LOG.warning("Loopback or duplicate registrant address submitted");
            throw new AddRefusedFault("Loopback or duplicate registrant address submitted");
        }

        EntryType entry = new EntryType();
        entry.setContent(content);
        entry.setMemberServiceEPR(memberEPR);
        entry.setServiceGroupEntryEPR(serviceGroupEPR);

        String entryId = this.indexService.add(entry, termTime);
        ResourceKey entryKey = getResourceKey(entryId);

        // construct an EPR to entry through the entry service.
        // TODO: is there a better way to get the URL to use?
        transportURL = transportURL + "Entry";
        EndpointReferenceType entryEPR = createEndpointReference(transportURL, entryKey);
        // TODO: do we need to actually save this EPR on the "entry" resource?
        // entry.setEntryEPR(entryEPR);

        return entryEPR;
    }

    @Override
    public void setTerminationTime(Calendar requestedTerminationTime, Holder<Calendar> newTerminationTime,
            Holder<Calendar> currentTime) throws TerminationTimeChangeRejectedFault,
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault,
            UnableToSetTerminationTimeFault {
        TerminationTimeChangeRejectedFault fault = new TerminationTimeChangeRejectedFault(
                "Terminating this resource is not supported.");
        throw fault;
    }

    @Override
    public void destroy() throws ResourceNotDestroyedFault,
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault {
        ResourceNotDestroyedFault fault = new ResourceNotDestroyedFault("Terminating this resource is not supported.");
        throw fault;
    }

    @Override
    public QueryResourcePropertiesResponse queryResourceProperties(
            QueryResourceProperties queryResourcePropertiesRequest) throws UnknownQueryExpressionDialectFault,
            InvalidQueryExpressionFault, QueryEvaluationErrorFault, ResourceUnknownFault,
            InvalidResourcePropertyQNameFault {
        // TODO Auto-generated method stub
        return super.queryResourceProperties(queryResourcePropertiesRequest);
    }

    private EndpointReferenceType createEndpointReference(String address, ResourceKey key) {
        EndpointReferenceType reference = new EndpointReferenceType();
        if (key != null) {
            ReferencePropertiesType referenceProperties = new ReferencePropertiesType();

            SOAPElement elem = key.toSOAPElement();

            setAny(referenceProperties, elem);

            reference.setReferenceProperties(referenceProperties);
        }

        AttributedURI uri = new AttributedURI();
        uri.setValue(address);
        reference.setAddress(uri);

        return reference;
    }

    private ResourceKey getResourceKey(String entryId) {
        PairedKeyType pk = new PairedKeyType();
        pk.setGroupKey((String) this.getKey().getValue());
        pk.setEntryKey(entryId);
        return new SimpleResourceKey(IndexEntryWSRFImpl.ENTRY_KEY, pk);

    }

    public ResourceKey getKey() {
        return key;
    }

    private void setAny(ReferencePropertiesType object, SOAPElement value) {
        if (value == null || object == null) {
            return;
        }
        if (!(value instanceof SOAPBodyElement)) {
            throw new IllegalArgumentException();
        }
        object.getAny().add(value);
    }

}

package org.cagrid.authentication.service.wsrf;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;

import org.cagrid.authentication.service.AuthenticationService;
import org.cagrid.core.common.JAXBUtils;
import org.cagrid.core.resource.ExternalSingletonResourceProperty;
import org.cagrid.core.resource.ExternalSingletonResourcePropertyValue;
import org.cagrid.core.resource.JAXBResourceProperties;
import org.cagrid.core.resource.JAXBResourcePropertySupport;
import org.cagrid.core.resource.ResourceImpl;
import org.cagrid.core.resource.ResourcePropertyDescriptor;
import org.cagrid.core.resource.SingletonResourceHomeImpl;
import org.cagrid.gaards.authentication.AuthenticateUserRequest;
import org.cagrid.gaards.authentication.AuthenticateUserResponse;
import org.cagrid.gaards.authentication.AuthenticationProfiles;
import org.cagrid.gaards.authentication.AuthenticationProviderFaultFaultMessage;
import org.cagrid.gaards.authentication.AuthenticationServicePortTypeImpl;
import org.cagrid.gaards.authentication.AuthenticationServiceResourceProperties;
import org.cagrid.gaards.authentication.BasicAuthentication;
import org.cagrid.gaards.authentication.CredentialNotSupportedFaultFaultMessage;
import org.cagrid.gaards.authentication.InsufficientAttributeFaultFaultMessage;
import org.cagrid.gaards.authentication.InvalidCredentialFaultFaultMessage;
import org.cagrid.gaards.authentication.faults.AuthenticationProviderException;
import org.cagrid.gaards.authentication.faults.CredentialNotSupportedException;
import org.cagrid.gaards.authentication.faults.InsufficientAttributeException;
import org.cagrid.gaards.authentication.faults.InvalidCredentialException;
import org.cagrid.gaards.authentication.service.SAMLAssertion;
import org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataRequest;
import org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse;
import org.cagrid.wsrf.properties.InvalidResourceKeyException;
import org.cagrid.wsrf.properties.NoSuchResourceException;
import org.cagrid.wsrf.properties.Resource;
import org.cagrid.wsrf.properties.ResourceException;
import org.cagrid.wsrf.properties.ResourceHome;
import org.cagrid.wsrf.properties.ResourceProperty;
import org.cagrid.wsrf.properties.ResourcePropertySet;
import org.oasis.names.tc.saml.assertion.AssertionType;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourceProperties;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourcePropertiesResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

@javax.jws.WebService(
        serviceName = "AuthenticationServiceService",
        targetNamespace = "http://authentication.gaards.cagrid.org/AuthenticationService/service",
        wsdlLocation = "/schema/org/cagrid/AuthenticationService/AuthenticationService_service.wsdl",
        endpointInterface = "org.cagrid.gaards.authentication.AuthenticationServicePortType")
public class AuthenticationServiceWSRFImpl extends AuthenticationServicePortTypeImpl {

    private AuthenticationService authenticationService;
    private final Logger logger;
    private final static String AUTHENTICATION_PROFILES_PREFIX = "gauth";

    private ResourceProperty<ServiceMetadata> serviceMetadataResourceProperty;
    private ResourceProperty<ServiceSecurityMetadata> serviceSecurityMetadataResourceProperty;

    @javax.annotation.Resource
    private WebServiceContext wsContext;

    private final ResourceHome resourceHome;

    public AuthenticationServiceWSRFImpl(AuthenticationService service, Map<String, String> jaxbResourcePropertiesMap) {
        this.logger = LoggerFactory.getLogger(getClass());
        this.authenticationService = service;
        resourceHome = getResourceHome(jaxbResourcePropertiesMap);
    }

    // @Override
    // public AuthenticateResponse authenticate(AuthenticateRequest parameters) throws
    // InvalidCredentialFaultFaultMessage,
    // InsufficientAttributeFaultFaultMessage, AuthenticationProviderFaultFaultMessage {
    // String message = "authenticate";
    // try {
    // SAMLAssertion assertion = authenticationService.authenticate(parameters.getCredential().getCredential());
    // AuthenticateResponse response = new AuthenticateResponse();
    // response.setSAMLAssertion(assertion);
    // return response;
    // } catch (InvalidCredentialException e) {
    // throw new InvalidCredentialFaultFaultMessage(message + ":" + e.getMessage(), e.getFault());
    // } catch (InsufficientAttributeException e) {
    // throw new InsufficientAttributeFaultFaultMessage(message + ":" + e.getMessage(), e.getFault());
    // } catch (AuthenticationProviderException e) {
    // throw new AuthenticationProviderFaultFaultMessage(message + ":" + e.getMessage(), e.getFault());
    // }
    // }

    @Override
    public AuthenticateUserResponse authenticateUser(AuthenticateUserRequest parameters)
            throws InvalidCredentialFaultFaultMessage, CredentialNotSupportedFaultFaultMessage,
            InsufficientAttributeFaultFaultMessage, AuthenticationProviderFaultFaultMessage {
        String message = "authenticate";
        try {
            gov.nih.nci.cagrid.opensaml.SAMLAssertion assertion = authenticationService.authenticateUser(parameters
                    .getCredential().getCredential());
            AuthenticateUserResponse response = new AuthenticateUserResponse();
            AssertionType aType = new AssertionType();
            aType.setSamlAssertion(assertion);
            response.setAssertion(aType);
            return response;
        } catch (InvalidCredentialException e) {
            throw new InvalidCredentialFaultFaultMessage(message + ":" + e.getMessage(), e.getFault());
        } catch (InsufficientAttributeException e) {
            throw new InsufficientAttributeFaultFaultMessage(message + ":" + e.getMessage(), e.getFault());
        } catch (AuthenticationProviderException e) {
            throw new AuthenticationProviderFaultFaultMessage(message + ":" + e.getMessage(), e.getFault());
        } catch (CredentialNotSupportedException e) {
            throw new CredentialNotSupportedFaultFaultMessage(message + ":" + e.getMessage(), e.getFault());
        }
    }

    @Override
    public GetMultipleResourcePropertiesResponse getMultipleResourceProperties(
            GetMultipleResourceProperties getMultipleResourcePropertiesRequest) throws ResourceUnknownFault,
            InvalidResourcePropertyQNameFault {
        logger.info("getMultipleResourceProperty " + getMultipleResourcePropertiesRequest);
        System.out.println(getMultipleResourcePropertiesRequest);
        GetMultipleResourcePropertiesResponse response = new GetMultipleResourcePropertiesResponse();
        for (Iterator iterator = getMultipleResourcePropertiesRequest.getResourceProperty().iterator(); iterator
                .hasNext();) {
            QName qname = (QName) iterator.next();
            Exception e;
            try {
                Resource resource = resourceHome.find(null);
                if (resource instanceof ResourcePropertySet) {
                    ResourcePropertySet resourcePropertySet = (ResourcePropertySet) resource;
                    ResourceProperty<?> resourceProperty = resourcePropertySet.get(qname);
                    if (resourceProperty != null) {
                        Object resourcePropertyValue = resourceProperty.get(0);
                        logger.info("getResourceProperty " + qname + " returning " + resourcePropertyValue);
                        if (!(resourcePropertyValue instanceof Node)
                                && !(resourcePropertyValue instanceof JAXBElement<?>)) {
                            resourcePropertyValue = JAXBUtils.wrap(resourcePropertyValue);
                        }
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
        logger.info("getResourceProperty " + resourcePropertyQName);
        Exception e = null;
        GetResourcePropertyResponse response = null;
        try {
            Resource resource = resourceHome.find(null);
            if (resource instanceof ResourcePropertySet) {
                ResourcePropertySet resourcePropertySet = (ResourcePropertySet) resource;
                ResourceProperty<?> resourceProperty = resourcePropertySet.get(resourcePropertyQName);
                if (resourceProperty != null) {
                    Object resourcePropertyValue = resourceProperty.get(0);
                    logger.info("getResourceProperty " + resourcePropertyQName + " returning " + resourcePropertyValue);
                    if (!(resourcePropertyValue instanceof Node) && !(resourcePropertyValue instanceof JAXBElement<?>)) {
                        resourcePropertyValue = JAXBUtils.wrap(resourcePropertyValue);
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
    public QueryResourcePropertiesResponse queryResourceProperties(
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties queryResourcePropertiesRequest)
            throws QueryEvaluationErrorFault, InvalidQueryExpressionFault, ResourceUnknownFault,
            InvalidResourcePropertyQNameFault, UnknownQueryExpressionDialectFault {
        logger.info("queryResourceProperties");
        QueryResourcePropertiesResponse response = null;
        response = new QueryResourcePropertiesResponse();
        return response;
    }

    @Override
    public GetServiceSecurityMetadataResponse getServiceSecurityMetadata(
            GetServiceSecurityMetadataRequest getServiceSecurityMetadataRequest) {
        logger.info("getServiceSecurityMetadata");
        ServiceSecurityMetadata serviceSecurityMetadata = (serviceSecurityMetadataResourceProperty != null) ? serviceSecurityMetadataResourceProperty
                .get(0) : null;
        GetServiceSecurityMetadataResponse response = new GetServiceSecurityMetadataResponse();
        response.setServiceSecurityMetadata(serviceSecurityMetadata);
        return response;
    }

    private ResourceHome getResourceHome(Map<String, String> jaxbResourcePropertiesMap) {
        ResourceImpl resource = new ResourceImpl(null);
        ResourceHome resourceHome = new SingletonResourceHomeImpl(resource);
        try {
            // What resource properties should we know about?
            Collection<ResourcePropertyDescriptor<?>> resourcePropertyDescriptors = ResourcePropertyDescriptor
                    .analyzeResourcePropertiesHolder(AuthenticationServiceResourceProperties.class);

            // Map them by field.
            Map<String, ResourcePropertyDescriptor<?>> descriptorsByField = ResourcePropertyDescriptor
                    .mapByField(resourcePropertyDescriptors);

            // Load the static jaxb resource properties.
            if (jaxbResourcePropertiesMap != null) {
                JAXBResourceProperties jaxbResourceProperties = new JAXBResourceProperties(getClass().getClassLoader(),
                        descriptorsByField, jaxbResourcePropertiesMap);

                // The serviceMetadata property is static.
                @SuppressWarnings("unchecked")
                ResourcePropertyDescriptor<ServiceMetadata> serviceMetadataDescriptor = (ResourcePropertyDescriptor<ServiceMetadata>) descriptorsByField
                        .get("serviceMetadata");
                if (serviceMetadataDescriptor != null) {
                    @SuppressWarnings("unchecked")
                    ResourceProperty<ServiceMetadata> resourceProperty = (ResourceProperty<ServiceMetadata>) jaxbResourceProperties
                            .getResourceProperties().get(serviceMetadataDescriptor);
                    serviceMetadataResourceProperty = resourceProperty;
                    resource.add(serviceMetadataResourceProperty);
                }

                // The rest of the properties are callbacks.
                @SuppressWarnings("unchecked")
                ResourcePropertyDescriptor<AuthenticationProfiles> authenticationProfilesDescriptor = (ResourcePropertyDescriptor<AuthenticationProfiles>) descriptorsByField
                        .get("authenticationProfiles");
                if (authenticationProfilesDescriptor != null) {
                    // Must treat auth profiles as Element!
                    ResourcePropertyDescriptor<Element> authenticationProfilesElementDescriptor = new ResourcePropertyDescriptor<Element>(
                            authenticationProfilesDescriptor.getResourcePropertyQName(), Element.class,
                            authenticationProfilesDescriptor.getFieldName());

                    ExternalSingletonResourcePropertyValue<Element> propertyValue = new ExternalSingletonResourcePropertyValue<Element>() {
                        @Override
                        public Element getPropertyValue() {
                            return getAuthenticationProfilesElement();
                        }
                    };
                    ResourceProperty<Element> resourceProperty = new ExternalSingletonResourceProperty<Element>(
                            authenticationProfilesElementDescriptor, propertyValue);
                    resource.add(resourceProperty);
                }

                /*
                 * ServiceSecurityMetadata isn't a resource property, but use that framework to handle it.
                 */
                String serviceSecurityMetadataURLString = jaxbResourcePropertiesMap.get("serviceSecurityMetadata");
                if (serviceSecurityMetadataURLString != null) {
                    URL url = null;
                    try {
                        url = new URL(serviceSecurityMetadataURLString);
                    } catch (MalformedURLException ignored) {
                    }
                    if (url == null) {
                        url = getClass().getClassLoader().getResource(serviceSecurityMetadataURLString);
                    }
                    if (url != null) {
                        QName serviceSecurityMetadataQName = new QName(AuthenticationServiceWSRFImpl.class.getName(),
                                "serviceSecurityMetadata");
                        ResourcePropertyDescriptor<ServiceSecurityMetadata> serviceSecurityMetadataDescriptor = new ResourcePropertyDescriptor<ServiceSecurityMetadata>(
                                serviceSecurityMetadataQName, ServiceSecurityMetadata.class, "serviceSecurityMetadata");
                        serviceSecurityMetadataResourceProperty = JAXBResourcePropertySupport
                                .createJAXBResourceProperty(serviceSecurityMetadataDescriptor, url);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourceHome;
    }

    private AuthenticationProfiles getAuthenticationProfiles() {
        AuthenticationProfiles authProfiles = new AuthenticationProfiles();
        QName basicAuthenticationQName = JAXBUtils.getQName(BasicAuthentication.class);
        authProfiles.getProfile().add(basicAuthenticationQName);
        return authProfiles;
    }

    /*
     * The client-side reconstruction of QNames from the getResourceProperty response is broken. It depends on the
     * namespace prefix in the 'profile' element content being the same as in the element tag. To try to work around
     * this, regenerate the appropriate QNames with a specific prefix and marshal the container with that prefix. The
     * final response probably won't have the prefix used here, but the necessary prefixes should agree.
     */
    private Element getAuthenticationProfilesElement() {
        AuthenticationProfiles authProfiles = getAuthenticationProfiles();
        QName authProfilesQName = JAXBUtils.getQName(AuthenticationProfiles.class);
        String authProfilesNamespace = authProfilesQName.getNamespaceURI();

        // New QName for marshalling
        authProfilesQName = new QName(authProfilesNamespace, authProfilesQName.getLocalPart(),
                AUTHENTICATION_PROFILES_PREFIX);

        // New QName elements
        List<QName> oldQNames = authProfiles.getProfile();
        List<QName> newQNames = new ArrayList<QName>(oldQNames.size());
        for (QName oldQName : oldQNames) {
            QName newQName = oldQName;
            if (authProfilesNamespace.equals(oldQName.getNamespaceURI())) {
                newQName = new QName(authProfilesNamespace, oldQName.getLocalPart(), AUTHENTICATION_PROFILES_PREFIX);
            }
            newQNames.add(newQName);
        }
        oldQNames.clear();
        oldQNames.addAll(newQNames);

        // Marshal to element with, hopefully, consistent prefixes.
        Element authProfilesElement = null;
        try {
            authProfilesElement = JAXBUtils.marshalToElement(authProfiles, authProfilesQName);
        } catch (Exception e) {
            logger.error("Exception marshalling AuthenticationProfiles", e);
        }
        return authProfilesElement;
    }
}

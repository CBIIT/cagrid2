#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.wsrf;

import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.cagrid.opensaml.SAMLException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.cagrid.core.common.JAXBUtils;
import ${package}.service.${serviceName}Service;
import org.cagrid.gaards.authentication.AuthenticateUserRequest;
import org.cagrid.gaards.authentication.AuthenticateUserResponse;
import org.cagrid.gaards.authentication.AuthenticationProviderFaultFaultMessage;
import org.cagrid.gaards.authentication.BasicAuthentication;
import org.cagrid.gaards.authentication.Credential;
import org.cagrid.gaards.authentication.CredentialNotSupportedFaultFaultMessage;
import org.cagrid.gaards.authentication.InsufficientAttributeFaultFaultMessage;
import org.cagrid.gaards.authentication.InvalidCredentialFaultFaultMessage;
import org.cagrid.gaards.authentication.WebServiceCallerId;
import org.cagrid.gaards.authentication.faults.AuthenticationProviderException;
import org.cagrid.gaards.authentication.faults.CredentialNotSupportedException;
import org.cagrid.gaards.authentication.faults.InvalidCredentialException;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
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
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties;
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

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.WebServiceContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ${serviceName}WSRFImpl implements ${serviceName}PortType {

	private final static String ANONYMOUS_ID = "anonymous";

	private final static Logger LOG = LoggerFactory.getLogger(${serviceName}WSRFImpl.class);

	private final ${serviceName}Service service;
	private final ResourceHome resourceHome;

	@javax.annotation.Resource
	private WebServiceContext wsContext;

	public${serviceName}WSRFImpl(${serviceName}Service service) {
		this.service = service;
		resourceHome = service.getResourceHome();
	}

   

    @Override
    public GetMultipleResourcePropertiesResponse getMultipleResourceProperties(GetMultipleResourceProperties getMultipleResourcePropertiesRequest) throws ResourceUnknownFault, InvalidResourcePropertyQNameFault    {
    	LOG.info("getMultipleResourceProperty "
				+ getMultipleResourcePropertiesRequest);
		GetMultipleResourcePropertiesResponse response = new GetMultipleResourcePropertiesResponse();
		for (Iterator iterator = getMultipleResourcePropertiesRequest
				.getResourceProperty().iterator(); iterator.hasNext();) {
			QName qname = (QName) iterator.next();
			Exception e;
			try {
				Resource resource = resourceHome.find(null);
				if (resource instanceof ResourcePropertySet) {
					ResourcePropertySet resourcePropertySet = (ResourcePropertySet) resource;
					ResourceProperty<?> resourceProperty = resourcePropertySet
							.get(qname);
					if (resourceProperty != null) {
						Object resourcePropertyValue = resourceProperty.get(0);
						LOG.info("getResourceProperty " + qname
								+ " returning " + resourcePropertyValue);
						if (!(resourcePropertyValue instanceof Node)
								&& !(resourcePropertyValue instanceof JAXBElement<?>)) {
							resourcePropertyValue = JAXBUtils
									.wrap(resourcePropertyValue);
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
    public GetServiceSecurityMetadataResponse getServiceSecurityMetadata(GetServiceSecurityMetadataRequest parameters) {
        LOG.debug("Executing operation getServiceSecurityMetadata");
        ServiceSecurityMetadata serviceSecurityMetadata = service.getServiceSecurityMetadata();
        GetServiceSecurityMetadataResponse response = new GetServiceSecurityMetadataResponse();
        response.setServiceSecurityMetadata(serviceSecurityMetadata);
        return response;
    }

   

    @Override
    public QueryResourcePropertiesResponse queryResourceProperties(QueryResourceProperties queryResourcePropertiesRequest) throws QueryEvaluationErrorFault , ResourceUnknownFault , InvalidResourcePropertyQNameFault , InvalidQueryExpressionFault , UnknownQueryExpressionDialectFault    {
        LOG.debug("Executing operation queryResourceProperties");
        // TODO
        QueryResourcePropertiesResponse response = null;
        response = new QueryResourcePropertiesResponse();
        return response;
    }

    @Override
    public GetResourcePropertyResponse getResourceProperty(QName resourcePropertyQName) throws ResourceUnknownFault, InvalidResourcePropertyQNameFault {
        LOG.debug("Executing operation getResourceProperty");
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

}

package org.cagrid.gaards.authentication.service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Set;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.cagrid.gaards.authentication.AuthenticationProfiles;
import org.cagrid.gaards.authentication.AuthenticationServiceResourceProperties;
import org.cagrid.gaards.authentication.BasicAuthentication;
import org.cagrid.wsrf.properties.ResourceHome;
import org.cagrid.wsrf.properties.ResourceProperty;
import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;

import org.cagrid.authenticationService.exceptions.AuthenticationProviderException;
import org.cagrid.authenticationService.exceptions.CredentialNotSupportedException;
import org.cagrid.authenticationService.exceptions.InsufficientAttributeException;
import org.cagrid.authenticationService.exceptions.InvalidCredentialException;
import org.cagrid.core.common.FaultHelper;
import org.cagrid.core.common.JAXBUtils;
import org.cagrid.core.resource.JAXBResourceProperties;
import org.cagrid.core.resource.JAXBResourcePropertySupport;
import org.cagrid.core.resource.ResourceImpl;
import org.cagrid.core.resource.ResourcePropertyDescriptor;
import org.cagrid.core.resource.SingletonResourceHomeImpl;
import org.cagrid.core.resource.ResourcePropertyDescriptor;
import org.cagrid.gaards.authentication.common.AuthenticationProperties;
import org.cagrid.gaards.authentication.service.AuthenticationManager;
import org.cagrid.gaards.authentication.service.AuthenticationService;
import org.cagrid.gaards.authentication.service.Credential;
import org.cagrid.gaards.authentication.service.SAMLAssertion;

public class AuthenticationServiceImpl implements AuthenticationService {

    protected static Log LOG = LogFactory.getLog(AuthenticationServiceImpl.class.getName());

    private final ResourceImpl resource = new ResourceImpl(null);
    private final ResourceHome resourceHome = new SingletonResourceHomeImpl(resource);
    private final Map<String, String> jaxbResourcePropertiesMap;
    private ResourceProperty<ServiceMetadata> serviceMetadataResourceProperty;
    private ResourceProperty<ServiceSecurityMetadata> serviceSecurityMetadataResourceProperty;
    private ResourceProperty<AuthenticationProfiles> authenticationProfilesResourceProperty;

	private AuthenticationManager auth;
	private AuthenticationProvider provider;
	private AuthenticationProperties authenticationProperties;

	public AuthenticationServiceImpl(
			AuthenticationProvider authenticationProvider,
			Map<String, String> jaxbResourcePropertiesMap) {
		this.provider = authenticationProvider;
		this.jaxbResourcePropertiesMap = jaxbResourcePropertiesMap;
	}

	@Override
	public ServiceSecurityMetadata getServiceSecurityMetadata() {
		return (serviceSecurityMetadataResourceProperty != null) ? serviceSecurityMetadataResourceProperty
				.get(0) : null;
	}

	@Override
	public ServiceMetadata getServiceMetadata() {
		return (serviceMetadataResourceProperty != null) ? serviceMetadataResourceProperty
				.get(0) : null;
	}

	@Override
	public org.cagrid.gaards.authentication.service.SAMLAssertion authenticate(org.cagrid.gaards.authentication.service.Credential credential)
			throws InvalidCredentialException,
			InsufficientAttributeException, AuthenticationProviderException {
		return this.auth.authenticate(credential);
	}

	@Override
	public gov.nih.nci.cagrid.opensaml.SAMLAssertion authenticateUser(org.cagrid.gaards.authentication.Credential credential)
			throws AuthenticationProviderException,
			CredentialNotSupportedException, InsufficientAttributeException,
			InvalidCredentialException {
		return this.auth.authenticate(credential);
	}

	private void initialize() throws JAXBException, AuthenticationProviderException {
		
		try
		{
			this.auth = new AuthenticationManager(provider);
		}
		catch(RemoteException e)
		{
			AuthenticationProviderException fault = FaultHelper.createFaultException(AuthenticationProviderException.class, "Failed to create AuthenticationManager: " + e.getMessage());
			throw fault;
		}
//		Set<QName> set = this.auth.getSupportedAuthenticationProfiles();
//		QName[] list = new QName[set.size()];
//		list = set.toArray(list);
//		AuthenticationProfiles profiles = getAuthenticationProfiles();
//		getResourceHome().getAddressedResource().setAuthenticationProfiles(
//				profiles);
		
		// What resource properties should we know about?
		Collection<ResourcePropertyDescriptor<?>> resourcePropertyDescriptors = ResourcePropertyDescriptor
				.analyzeResourcePropertiesHolder(AuthenticationServiceResourceProperties.class);

		// Map them by field.
		Map<String, ResourcePropertyDescriptor<?>> descriptorsByField = ResourcePropertyDescriptor
				.mapByField(resourcePropertyDescriptors);

		// Load the static jaxb resource properties.
		JAXBResourceProperties jaxbResourceProperties = new JAXBResourceProperties(
				getClass().getClassLoader(), descriptorsByField,
				jaxbResourcePropertiesMap);

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

		/*
		 * ServiceSecurityMetadata isn't a resource property, but use that
		 * framework to handle it.
		 */
		String serviceSecurityMetadataURLString = jaxbResourcePropertiesMap
				.get("serviceSecurityMetadata");
		if (serviceSecurityMetadataURLString != null) {
			URL url = null;
			try {
				url = new URL(serviceSecurityMetadataURLString);
			} catch (MalformedURLException ignored) {
			}
			if (url == null) {
				url = getClass().getClassLoader().getResource(
						serviceSecurityMetadataURLString);
			}
			if (url != null) {
				QName serviceSecurityMetadataQName = new QName(getClass()
						.getName(), "serviceSecurityMetadata");
				ResourcePropertyDescriptor<ServiceSecurityMetadata> serviceSecurityMetadataDescriptor = new ResourcePropertyDescriptor<ServiceSecurityMetadata>(
						serviceSecurityMetadataQName,
						ServiceSecurityMetadata.class,
						"serviceSecurityMetadata");
				serviceSecurityMetadataResourceProperty = JAXBResourcePropertySupport
						.createJAXBResourceProperty(
								serviceSecurityMetadataDescriptor, url);
			}
		}
		
		String authenticationProfilesMetadataURLString = jaxbResourcePropertiesMap
				.get("authenticationProfiles");
		if (authenticationProfilesMetadataURLString != null) {
			URL url = null;
			try {
				url = new URL(authenticationProfilesMetadataURLString);
			} catch (MalformedURLException ignored) {
			}
			if (url == null) {
				url = getClass().getClassLoader().getResource(
						authenticationProfilesMetadataURLString);
			}
			if (url != null) {
				QName authenticationProfilesMetadataQName = new QName(getClass()
						.getName(), "authenticationProfiles");
				ResourcePropertyDescriptor<AuthenticationProfiles> authenticationProfilesDescriptor = new ResourcePropertyDescriptor<AuthenticationProfiles>(
						authenticationProfilesMetadataQName,
						AuthenticationProfiles.class,
						"authenticationProfiles");
				authenticationProfilesResourceProperty = JAXBResourcePropertySupport
						.createJAXBResourceProperty(
								authenticationProfilesDescriptor, url);
			}
		}
	}

	public AuthenticationProfiles getAuthenticationProfiles() {
		return (authenticationProfilesResourceProperty != null) ? authenticationProfilesResourceProperty
				.get(0) : null;
	}

	
	  @Override
	    public ResourceHome getResourceHome() {
	        return resourceHome;
	    }

}

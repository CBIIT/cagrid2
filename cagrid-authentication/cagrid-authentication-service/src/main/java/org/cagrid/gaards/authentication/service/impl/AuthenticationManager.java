package org.cagrid.gaards.authentication.service.impl;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Set;

import javax.xml.namespace.QName;

import org.cagrid.core.common.FaultHelper;
import org.cagrid.gaards.authentication.BasicAuthentication;
import org.cagrid.gaards.authentication.Credential;
import org.cagrid.gaards.authentication.common.AuthenticationProfile;
import org.cagrid.gaards.authentication.faults.AuthenticationProviderException;
import org.cagrid.gaards.authentication.faults.CredentialNotSupportedException;
import org.cagrid.gaards.authentication.faults.InsufficientAttributeException;
import org.cagrid.gaards.authentication.faults.InvalidCredentialException;
import org.cagrid.gaards.authentication.service.BasicAuthenticationCredential;
import org.cagrid.gaards.saml.encoding.SAMLUtils;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.FileSystemResource;

public class AuthenticationManager {

	private AuthenticationProvider auth;

	public AuthenticationManager(File properties, File configuration)
			throws RemoteException {
		this(new FileSystemResource(
				properties), new FileSystemResource(configuration));
	}

	public AuthenticationManager(AbstractResource properties,
			AbstractResource configuration) throws RemoteException {
		try {
			BeanUtils utils = new BeanUtils(configuration, properties);
			this.auth = utils.getAuthenticationProvider();
			Set<QName> set = this.auth.getSupportedAuthenticationProfiles();
			if ((set == null) || (set.size() < 1)) {
				throw new Exception(
						"The authentication provider must support at least 1 valid authentication profile.");
			} else if (!AuthenticationProfile.isValid(set)) {
				throw new Exception(
						"The authentication provider supports an unknown authentication profile.");
			}
		} catch (Exception ex) {
			throw new RemoteException(
					"Error instantiating AuthenticationProvider: "
							+ ex.getMessage(), ex);
		}
	}

	public AuthenticationManager(AuthenticationProvider provider) throws RemoteException {
		try {
			this.auth = provider;
			Set<QName> set = this.auth.getSupportedAuthenticationProfiles();
			if ((set == null) || (set.size() < 1)) {
				throw new Exception(
						"The authentication provider must support at least 1 valid authentication profile.");
			} else if (!AuthenticationProfile.isValid(set)) {
				throw new Exception(
						"The authentication provider supports an unknown authentication profile.");
			}
		} catch (Exception ex) {
			throw new RemoteException(
					"Error instantiating AuthenticationProvider: "
							+ ex.getMessage(), ex);
		}
	}

	
	public SAMLAssertion authenticate(Credential credential)
			throws AuthenticationProviderException,
			CredentialNotSupportedException, InsufficientAttributeException,
			InvalidCredentialException {
		if (!AuthenticationProfile.isSupported(this.auth
				.getSupportedAuthenticationProfiles(), credential)) {
			CredentialNotSupportedException fault = FaultHelper.createFaultException(CredentialNotSupportedException.class, "The credential provided is not accepted by this service.");
			throw fault;
		}

		try {
			return this.auth.authenticate(credential);
		} catch (InvalidCredentialException ex) {
			InvalidCredentialException fault = FaultHelper
					.createFaultException(
							InvalidCredentialException.class, ex.getMessage());
			FaultHelper.addCause(fault, ex.getFault());
			throw fault;
		} catch (InsufficientAttributeException ex) {
			InsufficientAttributeException fault = FaultHelper
					.createFaultException(
							InsufficientAttributeException.class, ex.getMessage());
			FaultHelper.addCause(fault, ex.getFault());
			throw fault;
		} catch (Exception ex) {
			AuthenticationProviderException fault = FaultHelper
					.createFaultException(
							AuthenticationProviderException.class, ex.getMessage());
			throw fault;
		}
	}

	public org.cagrid.gaards.authentication.service.SAMLAssertion authenticate(
			org.cagrid.gaards.authentication.service.Credential credential)
			throws InvalidCredentialException,
			InsufficientAttributeException,
			AuthenticationProviderException {
		if (credential.getBasicAuthenticationCredential() != null) {
			if (credential.getCredentialExtension() != null) {
				InvalidCredentialException fault = FaultHelper
						.createFaultException(
								InvalidCredentialException.class, "The credential extension cannot be used to authenticate with the deprecated authenticate method, only a basic authentication credential is supported.");
				throw fault;
			} else {
				BasicAuthenticationCredential cred = credential
						.getBasicAuthenticationCredential();
				BasicAuthentication auth = new BasicAuthentication();
				auth.setUserId(cred.getUserId());
				auth.setPassword(cred.getPassword());
				try {
					SAMLAssertion saml = this.authenticate(auth);
					org.cagrid.gaards.authentication.service.SAMLAssertion assertion = new org.cagrid.gaards.authentication.service.SAMLAssertion();
					assertion.setXml(SAMLUtils.samlAssertionToString(saml));
					return assertion;
				} catch (InsufficientAttributeException e) {
					InsufficientAttributeException fault = FaultHelper
							.createFaultException(
									InsufficientAttributeException.class, e.getMessage());
					FaultHelper.addCause(fault, e.getFault());
					throw fault;
				} catch (InvalidCredentialException e) {
					InvalidCredentialException fault = FaultHelper
							.createFaultException(
									InvalidCredentialException.class, e.getMessage());
					FaultHelper.addCause(fault, e.getFault());
					throw fault;
				} catch (Exception e) {
					AuthenticationProviderException fault = FaultHelper
							.createFaultException(
									AuthenticationProviderException.class, e.getMessage());
					throw fault;
				}
			}

		} else {
			InvalidCredentialException fault = FaultHelper
					.createFaultException(
							InvalidCredentialException.class, "No basic authentication credential was provided, a basic authentication credential is required to authenticate to this service using the deprecated authenticate method.");
			throw fault;
		}
	}

	public Set<QName> getSupportedAuthenticationProfiles() {
		return this.auth.getSupportedAuthenticationProfiles();
	}

}

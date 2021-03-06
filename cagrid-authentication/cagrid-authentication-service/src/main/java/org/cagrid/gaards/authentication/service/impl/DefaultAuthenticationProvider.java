/**
 * $Id: DefaultAuthenticationProvider.java,v 1.2 2008-05-15 19:54:01 langella Exp $
 *
 */
package org.cagrid.gaards.authentication.service.impl;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import java.rmi.RemoteException;
import java.util.Set;

import javax.security.auth.Subject;
import javax.xml.namespace.QName;

import org.cagrid.core.common.FaultHelper;
import org.cagrid.gaards.authentication.Credential;
import org.cagrid.gaards.authentication.faults.AuthenticationProviderException;
import org.cagrid.gaards.authentication.faults.InsufficientAttributeException;
import org.cagrid.gaards.authentication.faults.InvalidCredentialException;

/**
 * 
 * @version $Revision: 1.2 $
 * @author Joshua Phillips
 * 
 */
public class DefaultAuthenticationProvider implements AuthenticationProvider {

	private SAMLProvider samlProvider;
	private SubjectProvider subjectProvider;

	public SAMLProvider getSamlProvider() {
		return samlProvider;
	}

	public void setSamlProvider(SAMLProvider samlProvider) {
		this.samlProvider = samlProvider;
	}

	public SubjectProvider getSubjectProvider() {
		return subjectProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.cagrid.authentication.common.AuthenticationProvider#authenticate(gov.nih.nci.cagrid.authentication.bean.Credential)
	 */
	public SAMLAssertion authenticate(Credential credential)
			throws RemoteException, InvalidCredentialException,
			InsufficientAttributeException, AuthenticationProviderException {

		try {
			Subject subject = getSubjectProvider().getSubject(credential);
			return getSamlProvider().getSAML(subject);
		} catch (InvalidCredentialException ex) {
			throw ex;
		} catch (InsufficientAttributeException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			AuthenticationProviderException fault = FaultHelper
					.createFaultException(
							AuthenticationProviderException.class, "Error authenticating: " + ex.getMessage());
			throw fault;
		}

	}

	public void setSAMLProvider(SAMLProvider samlProvider) {
		this.samlProvider = samlProvider;
	}

	public void setSubjectProvider(SubjectProvider subjectProvider) {
		this.subjectProvider = subjectProvider;
	}
	
	public Set<QName> getSupportedAuthenticationProfiles(){
		return getSubjectProvider().getSupportedAuthenticationProfiles();
	}

}

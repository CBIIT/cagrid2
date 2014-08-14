package org.cagrid.gaards.authentication.service.impl;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import java.rmi.RemoteException;
import java.util.Set;

import javax.xml.namespace.QName;

import org.cagrid.gaards.authentication.Credential;
import org.cagrid.gaards.authentication.faults.AuthenticationProviderException;
import org.cagrid.gaards.authentication.faults.InsufficientAttributeException;
import org.cagrid.gaards.authentication.faults.InvalidCredentialException;


public interface AuthenticationProvider {
	
    SAMLAssertion authenticate(Credential credential)
	throws RemoteException, InvalidCredentialException,
	InsufficientAttributeException, AuthenticationProviderException;
    
    public Set<QName> getSupportedAuthenticationProfiles();
    
}

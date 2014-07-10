package org.cagrid.gaards.authentication.service;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import java.rmi.RemoteException;
import java.util.Set;

import javax.xml.namespace.QName;

import org.cagrid.authenticationService.exceptions.AuthenticationProviderException;
import org.cagrid.authenticationService.exceptions.InsufficientAttributeException;
import org.cagrid.authenticationService.exceptions.InvalidCredentialException;
import org.cagrid.gaards.authentication.Credential;


public interface AuthenticationProvider {
	
    SAMLAssertion authenticate(Credential credential)
	throws RemoteException, InvalidCredentialException,
	InsufficientAttributeException, AuthenticationProviderException;
    
    public Set<QName> getSupportedAuthenticationProfiles();
    
}

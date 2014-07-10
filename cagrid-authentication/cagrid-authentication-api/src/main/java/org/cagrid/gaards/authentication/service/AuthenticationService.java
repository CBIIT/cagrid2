package org.cagrid.gaards.authentication.service;

import java.rmi.RemoteException;

import org.cagrid.authenticationService.exceptions.AuthenticationProviderException;
import org.cagrid.authenticationService.exceptions.CredentialNotSupportedException;
import org.cagrid.authenticationService.exceptions.InsufficientAttributeException;
import org.cagrid.authenticationService.exceptions.InvalidCredentialException;
import org.cagrid.wsrf.properties.ResourceHome;
import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;

public interface AuthenticationService {
    public gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata getServiceSecurityMetadata();
    public ServiceMetadata getServiceMetadata();
    public ResourceHome getResourceHome();
    public org.cagrid.gaards.authentication.service.SAMLAssertion authenticate(org.cagrid.gaards.authentication.service.Credential credential) throws InvalidCredentialException, InsufficientAttributeException, AuthenticationProviderException;
    public gov.nih.nci.cagrid.opensaml.SAMLAssertion authenticateUser(org.cagrid.gaards.authentication.Credential credential) throws AuthenticationProviderException, CredentialNotSupportedException, InsufficientAttributeException, InvalidCredentialException;
}

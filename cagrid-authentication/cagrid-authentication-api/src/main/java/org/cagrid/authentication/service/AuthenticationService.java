package org.cagrid.authentication.service;

import org.cagrid.gaards.authentication.faults.AuthenticationProviderException;
import org.cagrid.gaards.authentication.faults.CredentialNotSupportedException;
import org.cagrid.gaards.authentication.faults.InsufficientAttributeException;
import org.cagrid.gaards.authentication.faults.InvalidCredentialException;

public interface AuthenticationService {
    public org.cagrid.gaards.authentication.service.SAMLAssertion authenticate(org.cagrid.gaards.authentication.service.Credential credential) throws InvalidCredentialException, InsufficientAttributeException, AuthenticationProviderException;
    public gov.nih.nci.cagrid.opensaml.SAMLAssertion authenticateUser(org.cagrid.gaards.authentication.Credential credential) throws AuthenticationProviderException, CredentialNotSupportedException, InsufficientAttributeException, InvalidCredentialException;
}

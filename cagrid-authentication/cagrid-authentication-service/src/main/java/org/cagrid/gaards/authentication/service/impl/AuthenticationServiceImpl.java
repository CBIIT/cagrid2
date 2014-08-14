package org.cagrid.gaards.authentication.service.impl;

import java.rmi.RemoteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cagrid.authentication.service.AuthenticationService;
import org.cagrid.core.common.FaultHelper;
import org.cagrid.gaards.authentication.faults.AuthenticationProviderException;
import org.cagrid.gaards.authentication.faults.CredentialNotSupportedException;
import org.cagrid.gaards.authentication.faults.InsufficientAttributeException;
import org.cagrid.gaards.authentication.faults.InvalidCredentialException;

public class AuthenticationServiceImpl implements AuthenticationService {

    protected static Log LOG = LogFactory.getLog(AuthenticationServiceImpl.class.getName());

    private AuthenticationManager auth;
    private AuthenticationProvider provider;

    public AuthenticationServiceImpl(AuthenticationProvider authenticationProvider) {
        this.provider = authenticationProvider;
    }

    @Override
    public org.cagrid.gaards.authentication.service.SAMLAssertion authenticate(
            org.cagrid.gaards.authentication.service.Credential credential) throws InvalidCredentialException,
            InsufficientAttributeException, AuthenticationProviderException {
        return this.auth.authenticate(credential);
    }

    @Override
    public gov.nih.nci.cagrid.opensaml.SAMLAssertion authenticateUser(
            org.cagrid.gaards.authentication.Credential credential) throws AuthenticationProviderException,
            CredentialNotSupportedException, InsufficientAttributeException, InvalidCredentialException {
        return this.auth.authenticate(credential);
    }

    private void initialize() throws AuthenticationProviderException {

        try {
            this.auth = new AuthenticationManager(provider);
        } catch (RemoteException e) {
            AuthenticationProviderException fault = FaultHelper.createFaultException(
                    AuthenticationProviderException.class, "Failed to create AuthenticationManager: " + e.getMessage());
            throw fault;
        }

    }
}

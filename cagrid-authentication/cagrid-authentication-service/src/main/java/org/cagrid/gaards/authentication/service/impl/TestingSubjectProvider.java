package org.cagrid.gaards.authentication.service.impl;

import gov.nih.nci.security.authentication.principal.EmailIdPrincipal;
import gov.nih.nci.security.authentication.principal.FirstNamePrincipal;
import gov.nih.nci.security.authentication.principal.LastNamePrincipal;
import gov.nih.nci.security.authentication.principal.LoginIdPrincipal;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.security.auth.Subject;

import org.cagrid.core.common.FaultHelper;
import org.cagrid.gaards.authentication.BasicAuthentication;
import org.cagrid.gaards.authentication.Credential;
import org.cagrid.gaards.authentication.faults.InvalidCredentialException;

public class TestingSubjectProvider extends BasicAuthenticationSubjectProvider {

    public TestingSubjectProvider() {
        super();
    }

    public Subject getSubject(Credential credential) throws InvalidCredentialException {
        String userId = "";

        if (credential instanceof BasicAuthentication) {
            BasicAuthentication c = (BasicAuthentication) credential;
            if (c.getPassword().equals("password")) {
                userId = c.getUserId();
            } else {
                InvalidCredentialException fault = FaultHelper.createFaultException(InvalidCredentialException.class,
                        "Invalid password specified!!!");
                throw fault;
            }
        } else {
            return null;
        }
        Set<Principal> principals = new HashSet<Principal>();
        principals.add(new LoginIdPrincipal(userId));
        principals.add(new FirstNamePrincipal(userId));
        principals.add(new LastNamePrincipal(userId));
        principals.add(new EmailIdPrincipal(userId + "@example.com"));
        Subject subject = new Subject(true, principals, new HashSet(), new HashSet());
        return subject;
    }
}

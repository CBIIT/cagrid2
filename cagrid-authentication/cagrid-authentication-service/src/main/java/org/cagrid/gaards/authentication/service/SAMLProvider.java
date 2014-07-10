package org.cagrid.gaards.authentication.service;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import javax.security.auth.Subject;

import org.cagrid.authenticationService.exceptions.InsufficientAttributeException;

public interface SAMLProvider {
    SAMLAssertion getSAML(Subject subject) throws InsufficientAttributeException;
}

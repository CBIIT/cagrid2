package org.cagrid.gaards.authentication.service.impl;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import javax.security.auth.Subject;

import org.cagrid.gaards.authentication.faults.InsufficientAttributeException;

public interface SAMLProvider {
    SAMLAssertion getSAML(Subject subject) throws InsufficientAttributeException;
}

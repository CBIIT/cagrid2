package org.cagrid.gaards.authentication.service.impl;

import java.util.Set;

import javax.security.auth.Subject;
import javax.xml.namespace.QName;

import org.cagrid.gaards.authentication.Credential;
import org.cagrid.gaards.authentication.faults.InvalidCredentialException;

public interface SubjectProvider {

	public Subject getSubject(Credential credential)
			throws InvalidCredentialException;

	public Set<QName> getSupportedAuthenticationProfiles();

}

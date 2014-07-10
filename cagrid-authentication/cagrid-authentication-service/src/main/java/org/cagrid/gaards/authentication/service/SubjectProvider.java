package org.cagrid.gaards.authentication.service;

import java.util.Set;

import javax.security.auth.Subject;
import javax.xml.namespace.QName;

import org.cagrid.authenticationService.exceptions.InvalidCredentialException;
import org.cagrid.gaards.authentication.Credential;

public interface SubjectProvider {

	public Subject getSubject(Credential credential)
			throws InvalidCredentialException;

	public Set<QName> getSupportedAuthenticationProfiles();

}

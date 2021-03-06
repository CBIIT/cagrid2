package org.cagrid.gaards.authentication.service.impl;

import org.cagrid.gaards.authentication.common.AuthenticationProfile;


public abstract class BasicAuthenticationWithOneTimePasswordSubjectProvider
		extends BaseSubjectProvider {
	public BasicAuthenticationWithOneTimePasswordSubjectProvider() {
		super();
		addSupportedProfile(AuthenticationProfile.ONE_TIME_PASSWORD);
	}

}

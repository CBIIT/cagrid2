package org.cagrid.authentication.soapclient;

import org.cagrid.gaards.authentication.AuthenticationServicePortType;

public abstract class AuthenticationServiceClientBase {

	public final static String LOCAL_URL = "https://localhost:7742/authentication";

	protected AuthenticationServicePortType authentication;

	AuthenticationServiceClientBase(String url) throws Exception {
        authentication = AuthenticationServiceSoapClientFactory.createSoapClient(url);
	}
}

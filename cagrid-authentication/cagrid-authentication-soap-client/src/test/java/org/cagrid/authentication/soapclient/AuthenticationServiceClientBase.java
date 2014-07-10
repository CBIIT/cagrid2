package org.cagrid.authentication.soapclient;

import org.cagrid.authentication.soapclient.AuthenticationServiceSoapClientFactory;
import org.cagrid.gaards.authentication.authenticationservice.AuthenticationServicePortType;

public abstract class AuthenticationServiceClientBase {

	public final static String LOCAL_URL = "https://localhost:7742/authentication";

	protected AuthenticationServicePortType authentication;

	AuthenticationServiceClientBase(String url) throws Exception {
        authentication = AuthenticationServiceSoapClientFactory.createSoapClient(url);
	}
}

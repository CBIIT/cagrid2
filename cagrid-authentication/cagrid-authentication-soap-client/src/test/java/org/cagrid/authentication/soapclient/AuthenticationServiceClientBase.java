package org.cagrid.authentication.soapclient;

import org.cagrid.gaards.authentication.AuthenticationServicePortType;
import javax.net.ssl.KeyManager;

import org.apache.cxf.configuration.security.KeyStoreType;

public abstract class AuthenticationServiceClientBase {

	public final static String LOCAL_URL = "https://localhost:4443/authentication";

	protected AuthenticationServicePortType authentication;

	AuthenticationServiceClientBase(String url) throws Exception {
		KeyStoreType truststore = new KeyStoreType();
		truststore.setUrl(getClass().getClassLoader().getResource("truststore.jks").toString());
		truststore.setType("JKS");
		truststore.setPassword("changeit");
		authentication = AuthenticationServiceSoapClientFactory.createSoapClient(LOCAL_URL, truststore, (KeyManager) null);
	}
}

package org.cagrid.gaards.authentication.common;

public class AuthenticationProperties {
	private String csmContext;
	private String csmTrustStore;
	private String samlCert;
	private String samlKey;
	private String samlKeyPassword;

	public AuthenticationProperties(String csmContext, String csmTrustStore, String samlCert, String samlKey, String samlKeyPassword)
	{
		this.csmContext = csmContext;
		this.csmTrustStore = csmTrustStore;
		this.samlCert = samlCert;
		this.samlKey = samlKey;
		this.samlKeyPassword = samlKeyPassword;
	}

	public String getCsmContext() {
		return csmContext;
	}

	public void setCsmContext(String csmContext) {
		this.csmContext = csmContext;
	}

	public String getCsmTrustStore() {
		return csmTrustStore;
	}

	public void setCsmTrustStore(String csmTrustStore) {
		this.csmTrustStore = csmTrustStore;
	}

	public String getSamlCert() {
		return samlCert;
	}

	public void setSamlCert(String samlCert) {
		this.samlCert = samlCert;
	}

	public String getSamlKey() {
		return samlKey;
	}

	public void setSamlKey(String samlKey) {
		this.samlKey = samlKey;
	}

	public String getSamlKeyPassword() {
		return samlKeyPassword;
	}

	public void setSamlKeyPassword(String samlKeyPassword) {
		this.samlKeyPassword = samlKeyPassword;
	}
}

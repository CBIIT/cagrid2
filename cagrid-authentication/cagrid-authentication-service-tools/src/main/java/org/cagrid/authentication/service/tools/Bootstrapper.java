package org.cagrid.authentication.service.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Properties;

import org.cagrid.core.commandline.BaseCommandLine;
import org.cagrid.dorian.service.core.BeanUtils;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;

public class Bootstrapper extends BaseCommandLine {

	private static final String PROPERTIES_FILE = "src/main/resources/bootstrapper.properties";
	private static final String AUTHENTICATION_SERVICE_DIR = "cagrid-authentication";
	private static final String AUTHENTICATION_SERVICE_CFG = "cagrid.authentication.service.cfg";
	private static final String AUTHENTICATION_WSRF_CFG = "cagrid.authentication.wsrf.cfg";

	public static final String CONFIGURE_LEGACY_WSRF_PROMPT = "Do you want to configure a Legacy WSRF Endpoint (true|false)";
	public static final String CONFIGURE_LEGACY_WSRF_PROPERTY = "cagrid.authentication.configure.legacy.wsrf";

	private static final String DORIAN_CONFIG_PROMPT = "Please enter classpath location of Dorian configuration file";
	private static final String DORIAN_CONFIG_PROPERTY = "cagrid.gme.dorian.config";
	private static final String DORIAN_PROPERTIES_PROMPT = "Please enter location of Dorian property file";
	private static final String DORIAN_PROPERTIES_PROPERTY = "cagrid.gme.dorian.properties";

	private static final String TRUSTSTORE_FILE_NAME = "truststore.jks";
	public static final String ADMIN_USER_ID_PROMPT = "Please enter the user id of admin";
	public static final String ADMIN_USER_ID_PROPERTY = "org.cagrid.dorian.admin.user.id";

	private static final String WSRF_INDEXSVC_PROMPT = "Please enter index service endpoint";
	private static final String WSRF_INDEXSVC_PROPERTY = "cagrid.authentication.wsrf.registration.index.url";
	private static final String WSRF_CERTIFICATE_SOURCE = "cagrid.authentication.wsrf.cert.source";

	private static final String GAARDS_AUTHENTICATION_CSM_TRUSTSTORE_PROMPT = "Please enter the location of a Java trust store containing the trusted credential providers";
	private static final String GAARDS_AUTHENTICATION_CSM_APP_CONTEXT_PROMPT = "Please enter the name of the application context used in the JAAS Configuration file";
	private static final String GAARDS_AUTHENTICATION_SAML_CERT_PROMPT = "Please enter the full path to the file containing the certificate that corresponds to the private key that should be used for signing the SAML Assertions";
	private static final String GAARDS_AUTHENTICATION_SAML_KEY_PROMPT = "Please enter the full path to the file containing the private key that should be used for signing the SAML Assertions";
	private static final String GAARDS_AUTHENTICATION_SAML_KEY_PASSWORD_PROMPT = "Please enter the password (if one exists) used for encrypting/decrypting the private key";

	private static final String GAARDS_AUTHENTICATION_CSM_TRUSTSTORE_PROPERTY = "gaards.authentication.csm.truststore";
	private static final String GAARDS_AUTHENTICATION_CSM_APP_CONTEXT_PROPERTY = "gaards.authentication.csm.app.context";
	private static final String GAARDS_AUTHENTICATION_SAML_CERT_PROPERTY = "gaards.authentication.saml.cert";
	private static final String GAARDS_AUTHENTICATION_SAML_KEY_PROPERTY = "gaards.authentication.saml.key";
	private static final String GAARDS_AUTHENTICATION_SAML_KEY_PASSWORD_PROPERTY = "gaards.authentication.saml.key.password";


	private static final String WSRF_HOSTNAME_PROMPT = "Please enter a hostname for the WSRF endpoint";
	private static final String WSRF_HOSTNAME_PROPERTY = "cagrid.authentication.wsrf.host";
	private static final String WSRF_CERTIFICATE_PROMPT = "Please enter the location of the WSRF endpoint host certificate";
	private static final String WSRF_CERTIFICATE_PROPERTY = "cagrid.authentication.wsrf.certificate.location";
	private static final String WSRF_KEY_PROMPT = "Please enter the location of the WSRF endpoint private key";
	private static final String WSRF_KEY_PROPERTY = "cagrid.authentication.wsrf.key.location";
	private static final String WSRF_KEYSTORE_ALIAS_PROMPT = "Please enter a alias for the WSRF keystore";
	private static final String WSRF_KEYSTORE_ALIAS_PROPERTY = "cagrid.authentication.wsrf.keystore.alias";
	private static final String WSRF_KEYSTORE_PASSWORD_PROMPT = "Please enter a password for the WSRF keystore";
	private static final String WSRF_KEYSTORE_PASSWORD_PROPERTY = "cagrid.authentication.wsrf.keystore.password";
	private static final String WSRF_KEY_PASSWORD_PROMPT = "Please enter a password for the WSRF private key";
	private static final String WSRF_KEY_PASSWORD_PROPERTY = "cagrid.authentication.wsrf.key.password";
	private static final String WSRF_KEYSTORE_FILE_NAME = "authentication-host.jks";
	private static final String WSRF_KEYSTORE_PATH_PROPERTY = "cagrid.authentication.wsrf.keystore.path";
	private static final String WSRF_KEYSTORE_PATH = "${karaf.base}/etc/" + AUTHENTICATION_SERVICE_DIR + "/" + WSRF_KEYSTORE_FILE_NAME;
	private static final String WSRF_TRUSTSTORE_PATH_PROPERTY = "cagrid.authentication.wsrf.truststore.path";
	private static final String WSRF_TRUSTSTORE_PATH = "${karaf.base}/etc/" + AUTHENTICATION_SERVICE_DIR + "/" + TRUSTSTORE_FILE_NAME;
	private static final String WSRF_TRUSTSTORE_PASSWORD_PROMPT = "Please enter a password for the WSRF truststore";
	private static final String WSRF_TRUSTSTORE_PASSWORD_PROPERTY = "cagrid.authentication.wsrf.truststore.password";
	public static final String WSRF_URL_PROPERTY = "cagrid.authentication.wsrf.url";
	public static final String WSRF_PORT_PROMPT = "Enter a port number for the WSRF service";
	public static final String WSRF_PORT_PROPERTY = "cagrid.authentication.wsrf.port";

	private static final String LEGACY_WSRF_HOSTNAME_PROMPT = "Please enter a legacy hostname";
	private static final String LEGACY_WSRF_HOSTNAME_PROPERTY = "cagrid.authentication.legacy-wsrf.host";
	private static final String LEGACY_WSRF_CERTIFICATE_PROMPT = "Please enter the location of the legacy host certificate";
	private static final String LEGACY_WSRF_CERTIFICATE_PROPERTY = "cagrid.authentication.legacy-wsrf.certificate.location";
	private static final String LEGACY_WSRF_KEY_PROMPT = "Please enter the location of the legacy private key";
	private static final String LEGACY_WSRF_KEY_PROPERTY = "cagrid.authentication.legacy-wsrf.key.location";
	private static final String LEGACY_WSRF_KEYSTORE_ALIAS_PROMPT = "Please enter a alias for the legacy keystore";
	private static final String LEGACY_WSRF_KEYSTORE_ALIAS_PROPERTY = "cagrid.authentication.legacy-wsrf.keystore.alias";
	private static final String LEGACY_WSRF_KEYSTORE_PASSWORD_PROMPT = "Please enter a password for the legacy keystore";
	private static final String LEGACY_WSRF_KEYSTORE_PASSWORD_PROPERTY = "cagrid.authentication.legacy-wsrf.keystore.password";
	private static final String LEGACY_WSRF_KEY_PASSWORD_PROMPT = "Please enter a password for the legacy private key";
	private static final String LEGACY_WSRF_KEY_PASSWORD_PROPERTY = "cagrid.authentication.legacy-wsrf.key.password";
	private static final String LEGACY_WSRF_KEYSTORE_FILE_NAME = "legacy-authentication-host.jks";
	private static final String LEGACY_WSRF_KEYSTORE_PATH_PROPERTY = "cagrid.authentication.legacy-wsrf.keystore.path";
	private static final String LEGACY_WSRF_KEYSTORE_PATH = "${karaf.base}/etc/" + AUTHENTICATION_SERVICE_DIR + "/" + LEGACY_WSRF_KEYSTORE_FILE_NAME;
	private static final String LEGACY_WSRF_TRUSTSTORE_PATH_PROPERTY = "cagrid.authentication.legacy-wsrf.truststore.path";
	private static final String LEGACY_WSRF_TRUSTSTORE_PATH = "${karaf.base}/etc/" + AUTHENTICATION_SERVICE_DIR + "/" + TRUSTSTORE_FILE_NAME;
	private static final String LEGACY_WSRF_TRUSTSTORE_PASSWORD_PROPERTY = "cagrid.authentication.legacy-wsrf.truststore.password";
	private static final String LEGACY_WSRF_URL_PROPERTY = "cagrid.authentication.legacy-wsrf.url";
	private static final String LEGACY_WSRF_PORT_PROMPT = "Enter a port number for the legacy WSRF service";
	private static final String LEGACY_WSRF_PORT_PROPERTY = "cagrid.authentication.legacy-wsrf.port";
	private static final String AUTHENTICATION_URL_PROPERTY = "cagrid.authentication.service.url";

	private String truststorePassword;
	private Boolean configureLegacyWSRF;
	private File authEtcDir;
	private String keystorePassword;
	private String hostname;
	private String legacyHostname;
	private String keystoreAlias;
	private String keyPassword;
	private String legacyKeystorePassword;
	private String legacyKeystoreAlias;
	private String legacyKeyPassword;
	private String authenticationURL;
	private String adminIdentity;

	private String csmTrustStore;
	private String csmAppContext;
	private String samlCert;
	private String samlKey;
	private String samlKeyPass;
	private BeanUtils dorianUtils;
	private String wsrfKeystore;

	public Bootstrapper(File propertiesFile) throws Exception {
		super(propertiesFile);

	}

	public Bootstrapper(Properties properties) throws Exception {
		super(properties);

	}

	@Override
	public void execute() throws Exception {
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println("*      Authentication Service Bootstrapper                  *");
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println("");
		authEtcDir = new File(getServiceMixEtc().getAbsolutePath() + File.separator + AUTHENTICATION_SERVICE_DIR);
		authEtcDir.mkdirs();
		configureTruststore();
		createWSRFKeystore();
		configureLegacyWSRFCredentials();
		configureWSRFService();
		configureAuthenticationService();
	}

	private void configureAuthenticationService() throws Exception {
	    Properties props = new Properties();
		props.setProperty(GAARDS_AUTHENTICATION_CSM_TRUSTSTORE_PROPERTY, getCsmTrustStore());
		props.setProperty(GAARDS_AUTHENTICATION_CSM_APP_CONTEXT_PROPERTY, getCsmAppContext());
		props.setProperty(GAARDS_AUTHENTICATION_SAML_CERT_PROPERTY, getSamlCert());
		props.setProperty(GAARDS_AUTHENTICATION_SAML_KEY_PROPERTY, getSamlKey());
		props.setProperty(GAARDS_AUTHENTICATION_SAML_KEY_PASSWORD_PROPERTY, getSamlKeyPass());
		props.setProperty(AUTHENTICATION_URL_PROPERTY, this.authenticationURL);
		File config = new File(getServiceMixEtc(), AUTHENTICATION_SERVICE_CFG);
		props.store(new FileOutputStream(config), "authentication Configuration saved by bootstrapper on " + new Date());
	}

	private void configureWSRFService() throws Exception {
		Properties props = new Properties();
		props.setProperty(WSRF_HOSTNAME_PROPERTY, getHostname());
		props.setProperty(WSRF_TRUSTSTORE_PATH_PROPERTY, WSRF_TRUSTSTORE_PATH);
		props.setProperty(WSRF_TRUSTSTORE_PASSWORD_PROPERTY, getTruststorePassword());
		props.setProperty(WSRF_KEYSTORE_PATH_PROPERTY, WSRF_KEYSTORE_PATH);
		props.setProperty(WSRF_KEYSTORE_PASSWORD_PROPERTY, getKeystorePassword());
		props.setProperty(WSRF_KEYSTORE_ALIAS_PROPERTY, getKeystoreAlias());
		props.setProperty(WSRF_KEY_PASSWORD_PROPERTY, getKeyPassword());
		String port = getValue(WSRF_PORT_PROMPT, WSRF_PORT_PROPERTY);
		props.setProperty(WSRF_PORT_PROPERTY, port);
		String url = "https://" + getHostname() + ":" + port + "/authentication";
		this.authenticationURL = url;
		props.setProperty(WSRF_URL_PROPERTY, url);
		props.setProperty(WSRF_INDEXSVC_PROPERTY, getValue(WSRF_INDEXSVC_PROMPT, WSRF_INDEXSVC_PROPERTY));

		if (this.configureLegacyWSRF()) {
			props.setProperty(LEGACY_WSRF_TRUSTSTORE_PATH_PROPERTY, LEGACY_WSRF_TRUSTSTORE_PATH);
			props.setProperty(LEGACY_WSRF_TRUSTSTORE_PASSWORD_PROPERTY, getTruststorePassword());
			props.setProperty(LEGACY_WSRF_HOSTNAME_PROPERTY, getLegacyHostname());
			props.setProperty(LEGACY_WSRF_KEYSTORE_PATH_PROPERTY, LEGACY_WSRF_KEYSTORE_PATH);
			props.setProperty(LEGACY_WSRF_KEYSTORE_PASSWORD_PROPERTY, getLegacyKeystorePassword());
			props.setProperty(LEGACY_WSRF_KEYSTORE_ALIAS_PROPERTY, getLegacyKeystoreAlias());
			props.setProperty(LEGACY_WSRF_KEY_PASSWORD_PROPERTY, getLegacyKeyPassword());
			String legacyPort = getValue(LEGACY_WSRF_PORT_PROMPT, LEGACY_WSRF_PORT_PROPERTY);
			props.setProperty(LEGACY_WSRF_PORT_PROPERTY, legacyPort);
			String legacyURL = "https://" + getLegacyHostname() + ":" + legacyPort + "/wsrf/services/cagrid/authentication";
			this.authenticationURL = legacyURL;
			props.setProperty(LEGACY_WSRF_URL_PROPERTY, legacyURL);
		}

		File wsrfConfig = new File(getServiceMixEtc(), AUTHENTICATION_WSRF_CFG);
		props.store(new FileOutputStream(wsrfConfig), "Authentication WSRF Service Configuration saved by bootstrapper on " + new Date());
	}

	public void createWSRFKeystore() throws Exception {
		String hostCertificate = getValue(WSRF_CERTIFICATE_PROMPT, WSRF_CERTIFICATE_PROPERTY);
		X509Certificate cert = CertUtil.loadCertificate(new File(hostCertificate));
		String key = getValue(WSRF_KEY_PROMPT, WSRF_KEY_PROPERTY);
		PrivateKey pkey = KeyUtil.loadPrivateKey(new File(key), null);
		java.security.cert.Certificate[] hostCertChain = { cert };
		KeyStore hks = KeyStore.getInstance("jks");
		hks.load(null);

		String alias = getKeystoreAlias();
		String keyPassword = getKeyPassword();
		String password = getKeystorePassword();
		hks.setKeyEntry(alias, pkey, keyPassword.toCharArray(), hostCertChain);
		File hostPath = new File(this.authEtcDir.getAbsolutePath() + File.separator + WSRF_KEYSTORE_FILE_NAME);
		FileOutputStream out = new FileOutputStream(hostPath);
		hks.store(out, password.toCharArray());
		out.close();
        wsrfKeystore = hostPath.getAbsolutePath();
		System.out.println("WSRF keystore created for " + cert.getSubjectDN() + " at " + wsrfKeystore);

	}

	public void configureLegacyWSRFCredentials() throws Exception {
		if (configureLegacyWSRF()) {
			String hostCertificate = getValue(LEGACY_WSRF_CERTIFICATE_PROMPT, LEGACY_WSRF_CERTIFICATE_PROPERTY);
			X509Certificate cert = CertUtil.loadCertificate(new File(hostCertificate));
			String key = getValue(LEGACY_WSRF_KEY_PROMPT, LEGACY_WSRF_KEY_PROPERTY);
			PrivateKey pkey = KeyUtil.loadPrivateKey(new File(key), null);
			java.security.cert.Certificate[] hostCertChain = { cert };
			KeyStore hks = KeyStore.getInstance("jks");
			hks.load(null);

			String alias = getLegacyKeystoreAlias();
			String keyPassword = getLegacyKeyPassword();
			String password = getLegacyKeystorePassword();
			hks.setKeyEntry(alias, pkey, keyPassword.toCharArray(), hostCertChain);
			File hostPath = new File(this.authEtcDir.getAbsolutePath() + File.separator + LEGACY_WSRF_KEYSTORE_FILE_NAME);
			FileOutputStream out = new FileOutputStream(hostPath);
			hks.store(out, password.toCharArray());
			out.close();
			System.out.println("Legacy keystore created for " + cert.getSubjectDN() + " at " + hostPath.getAbsolutePath());
		}
	}

	private void configureTruststore() throws Exception {
		File f = new File(this.authEtcDir.getAbsolutePath() + File.separator + TRUSTSTORE_FILE_NAME);
		this.copyTrustStore(f.getAbsolutePath(), getTruststorePassword());
		System.out.println("Truststore created for Authentication Service at " + f.getAbsolutePath());
	}

	public boolean configureLegacyWSRF() {
		if (configureLegacyWSRF == null) {
			configureLegacyWSRF = Boolean.valueOf(getValue(CONFIGURE_LEGACY_WSRF_PROMPT, CONFIGURE_LEGACY_WSRF_PROPERTY));
		}
		return configureLegacyWSRF;
	}

	public String getCsmTrustStore() {
		if (csmTrustStore == null) {
			csmTrustStore = getValue(GAARDS_AUTHENTICATION_CSM_TRUSTSTORE_PROMPT, GAARDS_AUTHENTICATION_CSM_TRUSTSTORE_PROPERTY);
		}
		return csmTrustStore;
	}

	public String getCsmAppContext() {
		if (csmAppContext == null) {
			csmAppContext = getValue(GAARDS_AUTHENTICATION_CSM_APP_CONTEXT_PROMPT, GAARDS_AUTHENTICATION_CSM_APP_CONTEXT_PROPERTY);
		}
		return csmAppContext;
	}

	public String getSamlCert() {
		if (samlCert == null) {
			samlCert = getValue(GAARDS_AUTHENTICATION_SAML_CERT_PROMPT, GAARDS_AUTHENTICATION_SAML_CERT_PROPERTY);
		}
		return samlCert;
	}

	public String getSamlKey() {
		if (samlKey == null) {
			samlKey = getValue(GAARDS_AUTHENTICATION_SAML_KEY_PROMPT, GAARDS_AUTHENTICATION_SAML_KEY_PROPERTY);
		}
		return samlKey;
	}

	public String getSamlKeyPass() {
		if (samlKeyPass == null) {
			samlKeyPass = getValue(GAARDS_AUTHENTICATION_SAML_KEY_PASSWORD_PROMPT, GAARDS_AUTHENTICATION_SAML_KEY_PASSWORD_PROPERTY);
		}
		return samlKeyPass;
	}

	public String getHostname() {
		if (hostname == null) {
			hostname = getValue(WSRF_HOSTNAME_PROMPT, WSRF_HOSTNAME_PROPERTY);
		}
		return hostname;
	}

	public String getLegacyHostname() {
		if (legacyHostname == null) {
			legacyHostname = getValue(LEGACY_WSRF_HOSTNAME_PROMPT, LEGACY_WSRF_HOSTNAME_PROPERTY);
		}
		return legacyHostname;
	}

	public String getKeystorePassword() {
		if (keystorePassword == null) {
			keystorePassword = getValue(WSRF_KEYSTORE_PASSWORD_PROMPT, WSRF_KEYSTORE_PASSWORD_PROPERTY);
		}
		return keystorePassword;
	}

	public String getKeystoreAlias() {
		if (keystoreAlias == null) {
			keystoreAlias = getValue(WSRF_KEYSTORE_ALIAS_PROMPT, WSRF_KEYSTORE_ALIAS_PROPERTY);
		}
		return keystoreAlias;
	}

	public String getKeyPassword() {
		if (this.keyPassword == null) {
			this.keyPassword = getValue(WSRF_KEY_PASSWORD_PROMPT, WSRF_KEY_PASSWORD_PROPERTY);
		}
		return this.keyPassword;
	}

	public String getLegacyKeystorePassword() {
		if (legacyKeystorePassword == null) {
			legacyKeystorePassword = getValue(LEGACY_WSRF_KEYSTORE_PASSWORD_PROMPT, LEGACY_WSRF_KEYSTORE_PASSWORD_PROPERTY);
		}
		return keystorePassword;
	}

	public String getLegacyKeystoreAlias() {
		if (legacyKeystoreAlias == null) {
			legacyKeystoreAlias = getValue(LEGACY_WSRF_KEYSTORE_ALIAS_PROMPT, LEGACY_WSRF_KEYSTORE_ALIAS_PROPERTY);
		}
		return legacyKeystoreAlias;
	}

	public String getLegacyKeyPassword() {
		if (this.legacyKeyPassword == null) {
			this.legacyKeyPassword = getValue(LEGACY_WSRF_KEY_PASSWORD_PROMPT, LEGACY_WSRF_KEY_PASSWORD_PROPERTY);
		}
		return this.legacyKeyPassword;
	}

	public String getTruststorePassword() {
		if (truststorePassword == null) {
			truststorePassword = getValue(WSRF_TRUSTSTORE_PASSWORD_PROMPT, WSRF_TRUSTSTORE_PASSWORD_PROPERTY);
		}
		return truststorePassword;
	}

	public static void main(String[] args) {
		try {
			Bootstrapper main = new Bootstrapper(new File(PROPERTIES_FILE));
			main.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

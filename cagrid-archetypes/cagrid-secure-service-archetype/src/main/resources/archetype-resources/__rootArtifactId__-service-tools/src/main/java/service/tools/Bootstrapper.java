#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Properties;

import javax.net.ssl.KeyManager;

import org.apache.cxf.configuration.security.KeyStoreType;
import ${groupId}.core.commandline.BaseCommandLine;
import ${groupId}.core.soapclient.SingleEntityKeyManager;
import ${groupId}.dorian.DorianPortType;
import ${groupId}.dorian.RequestHostCertificateRequest;
import ${groupId}.dorian.RequestHostCertificateResponse;
import ${groupId}.dorian.RequestUserCertificateRequest;
import ${groupId}.dorian.RequestUserCertificateRequest.Key;
import ${groupId}.dorian.RequestUserCertificateRequest.Lifetime;
import ${groupId}.dorian.RequestUserCertificateRequest.Saml;
import ${groupId}.dorian.RequestUserCertificateResponse;
import ${groupId}.dorian.model.federation.CertificateLifetime;
import ${groupId}.dorian.model.federation.HostCertificateRecord;
import ${groupId}.dorian.model.federation.HostCertificateRequest;
import ${groupId}.dorian.model.federation.PublicKey;
import ${groupId}.dorian.service.CertificateSignatureAlgorithm;
import ${groupId}.dorian.service.ca.CertificateAuthorityProperties;
import ${groupId}.dorian.service.core.BeanUtils;
import ${groupId}.dorian.service.federation.IdentityAssignmentPolicy;
import ${groupId}.dorian.soapclient.DorianSoapClientFactory;
import ${groupId}.gaards.authentication.AuthenticateUserRequest;
import ${groupId}.gaards.authentication.AuthenticateUserRequest.Credential;
import ${groupId}.gaards.authentication.AuthenticateUserResponse;
import ${groupId}.gaards.authentication.BasicAuthentication;
import ${groupId}.gaards.pki.CertUtil;
import ${groupId}.gaards.pki.KeyUtil;
import org.oasis.names.tc.saml.assertion.AssertionType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

public class Bootstrapper extends BaseCommandLine {

	private static final String PROPERTIES_FILE = "src/main/resources/bootstrapper.properties";
	private static final String SERVICE_DIR = "${parentArtifactId}";
	private static final String SERVICE_CFG = "${package}.service.cfg";
	private static final String WSRF_CFG = "${package}.wsrf.cfg";

	public static final String CONFIGURE_LEGACY_WSRF_PROMPT = "Do you want to configure a Legacy WSRF Endpoint (true|false)";
	public static final String CONFIGURE_LEGACY_WSRF_PROPERTY = "${package}.configure.legacy.wsrf";

	private static final String TRUSTSTORE_FILE_NAME = "truststore.jks";

	public static final String ADMIN_USER_ID_PROMPT = "Please enter the user id of admin";
	public static final String ADMIN_USER_ID_PROPERTY = "${groupId}.dorian.admin.user.id";

	private static final String WSRF_CERTIFICATE_SOURCE = "${package}.wsrf.cert.source";
	
	private static final String DORIAN_CONFIG_PROMPT = "Please enter classpath location of Dorian configuration file";
	private static final String DORIAN_CONFIG_PROPERTY = "${package}.dorian.config";
	private static final String DORIAN_PROPERTIES_PROMPT = "Please enter location of Dorian property file";
	private static final String DORIAN_PROPERTIES_PROPERTY = "${package}.dorian.properties";

    private static final String WSRF_INDEXSVC_PROMPT = "Please enter index service endpoint";
    private static final String WSRF_INDEXSVC_PROPERTY = "${package}.wsrf.registration.index.url";
	private static final String WSRF_HOSTNAME_PROMPT = "Please enter a hostname for the WSRF endpoint";
	private static final String WSRF_HOSTNAME_PROPERTY = "${package}.wsrf.host";
	public static final String WSRF_PORT_PROMPT = "Enter a port number for the WSRF service";
	public static final String WSRF_PORT_PROPERTY = "${package}.wsrf.port";
	public static final String WSRF_URL_PROPERTY = "${package}.wsrf.url";
	private static final String WSRF_URL_PATH_PROMPT = "Please enter the url path of the WSRF endpoint";
	private static final String WSRF_URL_PATH_PROPERTY = "${package}.wsrf.url.path";
	private static final String WSRF_CERTIFICATE_PROMPT = "Please enter the location of the WSRF endpoint host certificate";
	private static final String WSRF_CERTIFICATE_PROPERTY = "${package}.certificate.location";
	private static final String WSRF_KEY_PROMPT = "Please enter the location of the WSRF endpoint private key";
	private static final String WSRF_KEY_PROPERTY = "${package}.key.location";
	private static final String WSRF_KEYSTORE_ALIAS_PROMPT = "Please enter a alias for the WSRF keystore";
	private static final String WSRF_KEYSTORE_ALIAS_PROPERTY = "${package}.wsrf.keystore.alias";
	private static final String WSRF_KEYSTORE_PASSWORD_PROMPT = "Please enter a password for the WSRF keystore";
	private static final String WSRF_KEYSTORE_PASSWORD_PROPERTY = "${package}.wsrf.keystore.password";
	private static final String WSRF_KEY_PASSWORD_PROMPT = "Please enter a password for the WSRF private key";
	private static final String WSRF_KEY_PASSWORD_PROPERTY = "${package}.wsrf.key.password";
	private static final String WSRF_KEYSTORE_FILE_NAME = "host.jks";
	private static final String WSRF_KEYSTORE_PATH_PROPERTY = "${package}.wsrf.keystore.path";
	private static final String WSRF_KEYSTORE_PATH = "${symbol_dollar}{karaf.base}/etc/" + SERVICE_DIR + "/" + WSRF_KEYSTORE_FILE_NAME;
	private static final String WSRF_TRUSTSTORE_PATH_PROPERTY = "${package}.wsrf.truststore.path";
	private static final String WSRF_TRUSTSTORE_PATH = "${symbol_dollar}{karaf.base}/etc/" + SERVICE_DIR + "/" + TRUSTSTORE_FILE_NAME;
	private static final String WSRF_TRUSTSTORE_PASSWORD_PROMPT = "Please enter a password for the WSRF truststore";
	private static final String WSRF_TRUSTSTORE_PASSWORD_PROPERTY = "${package}.wsrf.truststore.password";

	private static final String LEGACY_WSRF_HOSTNAME_PROMPT = "Please enter a legacy hostname";
	private static final String LEGACY_WSRF_HOSTNAME_PROPERTY = "${package}.legacy-wsrf.host";
	private static final String LEGACY_WSRF_URL_PATH_PROMPT = "Please enter the url path of the legacy WSRF endpoint";
	private static final String LEGACY_WSRF_URL_PATH_PROPERTY = "${package}.legacy-wsrf.url.path";
	private static final String LEGACY_WSRF_CERTIFICATE_PROMPT = "Please enter the location of the legacy host certificate";
	private static final String LEGACY_WSRF_CERTIFICATE_PROPERTY = "${package}.legacy-certificate.location";
	private static final String LEGACY_WSRF_KEY_PROMPT = "Please enter the location of the legacy private key";
	private static final String LEGACY_WSRF_KEY_PROPERTY = "${package}.legacy-key.location";
	private static final String LEGACY_WSRF_KEYSTORE_ALIAS_PROMPT = "Please enter a alias for the legacy keystore";
	private static final String LEGACY_WSRF_KEYSTORE_ALIAS_PROPERTY = "${package}.legacy-wsrf.keystore.alias";
	private static final String LEGACY_WSRF_KEYSTORE_PASSWORD_PROMPT = "Please enter a password for the legacy keystore";
	private static final String LEGACY_WSRF_KEYSTORE_PASSWORD_PROPERTY = "${package}.legacy-wsrf.keystore.password";
	private static final String LEGACY_WSRF_KEY_PASSWORD_PROMPT = "Please enter a password for the legacy private key";
	private static final String LEGACY_WSRF_KEY_PASSWORD_PROPERTY = "${package}.legacy-wsrf.key.password";
	private static final String LEGACY_WSRF_KEYSTORE_FILE_NAME = "legacy-host.jks";
	private static final String LEGACY_WSRF_KEYSTORE_PATH_PROPERTY = "${package}.legacy-wsrf.keystore.path";
	private static final String LEGACY_WSRF_KEYSTORE_PATH = "${symbol_dollar}{karaf.base}/etc/" + SERVICE_DIR + "/" + LEGACY_WSRF_KEYSTORE_FILE_NAME;
	private static final String LEGACY_WSRF_TRUSTSTORE_PATH_PROPERTY = "${package}.legacy-wsrf.truststore.path";
	private static final String LEGACY_WSRF_TRUSTSTORE_PATH = "${symbol_dollar}{karaf.base}/etc/" + SERVICE_DIR + "/" + TRUSTSTORE_FILE_NAME;
	private static final String LEGACY_WSRF_TRUSTSTORE_PASSWORD_PROPERTY = "${package}.legacy-wsrf.truststore.password";
	private static final String LEGACY_WSRF_URL_PROPERTY = "${package}.legacy-wsrf.url";
	private static final String LEGACY_WSRF_PORT_PROMPT = "Enter a port number for the legacy WSRF service";
	private static final String LEGACY_WSRF_PORT_PROPERTY = "${package}.legacy-wsrf.port";


	private String adminIdentity;
	private String truststorePassword;
	private Boolean configureLegacyWSRF;
	private File etcDir;
	private String keystorePassword;
	private String hostname;
	private String legacyHostname;
	private String keystoreAlias;
	private String keyPassword;
	private String legacyKeystorePassword;
	private String legacyKeystoreAlias;
	private String legacyKeyPassword;

	private BeanUtils dorianUtils;

	public Bootstrapper(File propertiesFile) throws Exception {
		super(propertiesFile);
	}

	public Bootstrapper(Properties properties) throws Exception {
		super(properties);
	}

	@Override
	public void execute() throws Exception {
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println("*            ${rootArtifactId} Bootstrapper             *");
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println("");
		etcDir = new File(getServiceMixEtc().getAbsolutePath() + File.separator + SERVICE_DIR);
		etcDir.mkdirs();
		configureTruststore();
		createWSRFKeystore();
		configureLegacyWSRFCredentials();
		configureService();
		configureWSRFService();

	}

	private void configureService() throws Exception {
		Properties props = new Properties();
		File config = new File(getServiceMixEtc(), SERVICE_CFG);
		props.store(new FileOutputStream(config), "${rootArtifactId} Configuration saved by bootstrapper on " + new Date());
	}

	private void configureWSRFService() throws Exception {
		Properties props = new Properties();
		props.setProperty(WSRF_HOSTNAME_PROPERTY, getHostname());
		String port = getValue(WSRF_PORT_PROMPT, WSRF_PORT_PROPERTY);
		props.setProperty(WSRF_PORT_PROPERTY, port);
		
		String url = "https://" + getHostname() + ":" + port + "/" + getValue(WSRF_URL_PATH_PROMPT, WSRF_URL_PATH_PROPERTY);
		props.setProperty(WSRF_URL_PROPERTY, url);
		props.setProperty(WSRF_TRUSTSTORE_PATH_PROPERTY, WSRF_TRUSTSTORE_PATH);
		props.setProperty(WSRF_TRUSTSTORE_PASSWORD_PROPERTY, getTruststorePassword());
		props.setProperty(WSRF_KEYSTORE_PATH_PROPERTY, WSRF_KEYSTORE_PATH);
		props.setProperty(WSRF_KEYSTORE_PASSWORD_PROPERTY, getKeystorePassword());
		props.setProperty(WSRF_KEYSTORE_ALIAS_PROPERTY, getKeystoreAlias());
		props.setProperty(WSRF_KEY_PASSWORD_PROPERTY, getKeyPassword());
        props.setProperty(WSRF_INDEXSVC_PROPERTY, getValue(WSRF_INDEXSVC_PROMPT, WSRF_INDEXSVC_PROPERTY));

		if (this.configureLegacyWSRF()) {
			props.setProperty(LEGACY_WSRF_TRUSTSTORE_PATH_PROPERTY, LEGACY_WSRF_TRUSTSTORE_PATH);
			props.setProperty(LEGACY_WSRF_TRUSTSTORE_PASSWORD_PROPERTY, getTruststorePassword());
			props.setProperty(LEGACY_WSRF_HOSTNAME_PROPERTY, getLegacyHostname());
			String legacyPort = getValue(LEGACY_WSRF_PORT_PROMPT, LEGACY_WSRF_PORT_PROPERTY);
			props.setProperty(LEGACY_WSRF_PORT_PROPERTY, legacyPort);
			String legacyURL = "https://" + getLegacyHostname() + ":" + legacyPort + "/" + getValue(LEGACY_WSRF_URL_PATH_PROMPT, LEGACY_WSRF_URL_PATH_PROPERTY);
			props.setProperty(LEGACY_WSRF_URL_PROPERTY, legacyURL);
			props.setProperty(LEGACY_WSRF_KEYSTORE_PATH_PROPERTY, LEGACY_WSRF_KEYSTORE_PATH);
			props.setProperty(LEGACY_WSRF_KEYSTORE_PASSWORD_PROPERTY, getLegacyKeystorePassword());
			props.setProperty(LEGACY_WSRF_KEYSTORE_ALIAS_PROPERTY, getLegacyKeystoreAlias());
			props.setProperty(LEGACY_WSRF_KEY_PASSWORD_PROPERTY, getLegacyKeyPassword());
		}

		File wsrfConfig = new File(getServiceMixEtc(), WSRF_CFG);
		props.store(new FileOutputStream(wsrfConfig), "${rootArtifactId} WSRF Service Configuration saved by bootstrapper on " + new Date());
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
			File hostPath = new File(this.etcDir.getAbsolutePath() + File.separator + LEGACY_WSRF_KEYSTORE_FILE_NAME);
			FileOutputStream out = new FileOutputStream(hostPath);
			hks.store(out, password.toCharArray());
			out.close();
			System.out.println("Legacy keystore created for " + cert.getSubjectDN() + " at " + hostPath.getAbsolutePath());
		}
	}

	public boolean configureLegacyWSRF() {
		if (configureLegacyWSRF == null) {
			configureLegacyWSRF = Boolean.valueOf(getValue(CONFIGURE_LEGACY_WSRF_PROMPT, CONFIGURE_LEGACY_WSRF_PROPERTY));
		}
		return configureLegacyWSRF;
	}

	public void createWSRFKeystore() throws Exception {
		String certSource = getValueWithOptions("Obtain certificates from (f) filesystem, (r) remote dorian, (l) local database", WSRF_CERTIFICATE_SOURCE, new String[] {"f", "r", "l"});
		X509Certificate cert = null;
		PrivateKey pkey = null;
		String hostPath = this.etcDir.getAbsolutePath() + File.separator + WSRF_KEYSTORE_FILE_NAME;
		if ("f".equals(certSource)) {
			String hostCertificate = getValue(WSRF_CERTIFICATE_PROMPT, WSRF_CERTIFICATE_PROPERTY);
			cert = CertUtil.loadCertificate(new File(hostCertificate));
			String key = getValue(WSRF_KEY_PROMPT, WSRF_KEY_PROPERTY);
			pkey = KeyUtil.loadPrivateKey(new File(key), null);
			java.security.cert.Certificate[] hostCertChain = { cert };
			KeyStore hks = KeyStore.getInstance("jks");
			hks.load(null);

			String alias = getKeystoreAlias();
			String keyPassword = getKeyPassword();
			String password = getKeystorePassword();
			hks.setKeyEntry(alias, pkey, keyPassword.toCharArray(), hostCertChain);
			File hostFile = new File(this.etcDir.getAbsolutePath() + File.separator + WSRF_KEYSTORE_FILE_NAME);
			FileOutputStream out = new FileOutputStream(hostPath);
			hks.store(out, password.toCharArray());
			out.close();
			System.out.println("WSRF keystore created for " + cert.getSubjectDN() + " at " + hostFile.getAbsolutePath());
		} else if ("r".equals(certSource)) {
			DorianPortType authDorian = getAuthenticatedDorianSoapClient();
			
			KeyPair pair = KeyUtil.generateRSAKeyPair(1024);

			RequestHostCertificateRequest.Req rhcrr = new RequestHostCertificateRequest.Req(); //HostCertificateRequest();
			HostCertificateRequest req = new HostCertificateRequest();
			
			req.setHostname(getHostname());
			PublicKey publicKey = new PublicKey();
			publicKey.setKeyAsString(KeyUtil.writePublicKey(pair.getPublic()));
			req.setPublicKey(publicKey);
			RequestHostCertificateRequest rhcr = new RequestHostCertificateRequest();
			rhcrr.setHostCertificateRequest(req);
			
			rhcr.setReq(rhcrr);
			System.out.println(rhcr);
			RequestHostCertificateResponse resp = authDorian.requestHostCertificate(rhcr);
			System.out.println(resp);
		} else if ("l".equals(certSource)) {
			writeHostCertificate(getHostname(), getKeystoreAlias(), hostPath, getKeystorePassword(), getKeyPassword());
		}
	}

	private DorianPortType getAuthenticatedDorianSoapClient() throws Exception {
		KeyStoreType truststore = new KeyStoreType();
		truststore.setFile(getDefaultTruststoreLocation());
		truststore.setType("JKS");
		truststore.setPassword("changeit");

		DorianPortType dorianSoapAnon = DorianSoapClientFactory
				.createSoapClient("https://localhost:4443/dorian", truststore,
						(KeyManager) null);

		BasicAuthentication basicAuthentication = new BasicAuthentication();
		basicAuthentication.setUserId("dorian");
		basicAuthentication.setPassword("DorianAdmin${symbol_dollar}1");
		Credential credential = new Credential();
		credential.setCredential(basicAuthentication);
		AuthenticateUserRequest authenticateUserRequest = new AuthenticateUserRequest();
		authenticateUserRequest.setCredential(credential);
		AuthenticateUserResponse authenticateUserResponse = dorianSoapAnon
				.authenticateUser(authenticateUserRequest);
		AssertionType assertion = authenticateUserResponse.getAssertion();
		//Assert.assertNotNull(assertion);

		KeyPair keyPair = KeyUtil.generateRSAKeyPair(2048);
		Saml saml = new Saml();
		saml.setAssertion(assertion);
		PublicKey caPublicKey = new PublicKey();
		caPublicKey.setKeyAsString(KeyUtil.writePublicKey(keyPair.getPublic()));
		RequestUserCertificateRequest userCertificateRequest = new RequestUserCertificateRequest();
		userCertificateRequest.setSaml(saml);
		Key caKey = new Key();
		caKey.setPublicKey(caPublicKey);
		userCertificateRequest.setKey(caKey);
		CertificateLifetime certificateLifetime = new CertificateLifetime();
		certificateLifetime.setHours(6);
		Lifetime lifetime = new Lifetime();
		lifetime.setCertificateLifetime(certificateLifetime);
		userCertificateRequest.setLifetime(lifetime);
		RequestUserCertificateResponse requestUserCertificateResponse = dorianSoapAnon
				.requestUserCertificate(userCertificateRequest);
		String certificateString = requestUserCertificateResponse
				.getX509Certificate().getCertificateAsString();
		X509Certificate certificate = CertUtil
				.loadCertificate(certificateString);
		//Assert.assertNotNull(certificate);

		KeyManager keyManager = new SingleEntityKeyManager("client",
				new X509Certificate[] { certificate }, keyPair.getPrivate());
		DorianPortType dorianSoapAuth = DorianSoapClientFactory
				.createSoapClient("https://localhost:7734/dorian", truststore,
						keyManager);
		return dorianSoapAuth;
	}
	
	private BeanUtils getDorianUtils() throws Exception {
		if (dorianUtils == null) {
			dorianUtils = new BeanUtils(new ClassPathResource(getValue(DORIAN_CONFIG_PROMPT, DORIAN_CONFIG_PROPERTY)), 
					new FileSystemResource(getValue(DORIAN_PROPERTIES_PROMPT, DORIAN_PROPERTIES_PROPERTY)));
		}
		return dorianUtils;
	}

	private void writeHostCertificate(String host, String hostAlias, String hostPath, String keystorePassword, String keyPassword) throws Exception {
		CertificateAuthorityProperties caProperties = getDorianUtils().getCertificateAuthorityProperties();
		KeyPair pair = KeyUtil.generateRSAKeyPair(caProperties.getIssuedCertificateKeySize());

		HostCertificateRequest req = new HostCertificateRequest();
		req.setHostname(host);
		PublicKey publicKey = new PublicKey();
		publicKey.setKeyAsString(KeyUtil.writePublicKey(pair.getPublic()));
		req.setPublicKey(publicKey);
		HostCertificateRecord record = getDorianUtils().getDorian().requestHostCertificate(this.getAdminIdentity(), req, CertificateSignatureAlgorithm.SHA2);
		X509Certificate hostCertificate = CertUtil.loadCertificate(record.getCertificate().getCertificateAsString());
		System.out.println("Successfully created the host certificate:");
		System.out.println("Subject: " + hostCertificate.getSubjectDN());
		System.out.println("Created: " + hostCertificate.getNotBefore());
		System.out.println("Expires: " + hostCertificate.getNotAfter());

		java.security.cert.Certificate[] hostCertChain = { hostCertificate };
		KeyStore hks = KeyStore.getInstance("jks");
		hks.load(null);

		hks.setKeyEntry(hostAlias, pair.getPrivate(), keyPassword.toCharArray(), hostCertChain);
		FileOutputStream out = new FileOutputStream(hostPath);
		hks.store(out, keystorePassword.toCharArray());
		out.close();
		System.out.println("Keystore created for " + hostCertificate.getSubjectDN() + " at " + hostPath);
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

	private void configureTruststore() throws Exception {
		File f = new File(this.etcDir.getAbsolutePath() + File.separator + TRUSTSTORE_FILE_NAME);
		this.copyTrustStore(f.getAbsolutePath(), getTruststorePassword());
		System.out.println("Truststore created for ${rootArtifactId} at " + f.getAbsolutePath());
	}

	public String getTruststorePassword() {
		if (truststorePassword == null) {
			truststorePassword = getValue(WSRF_TRUSTSTORE_PASSWORD_PROMPT, WSRF_TRUSTSTORE_PASSWORD_PROPERTY);
		}
		return truststorePassword;
	}
	
	public String getAdminIdentity() throws Exception {
		if (adminIdentity == null) {
			String userId = getValue(ADMIN_USER_ID_PROMPT, ADMIN_USER_ID_PROPERTY);
			X509Certificate cacert = getDorianUtils().getDorian().getCACertificate();
			String caSubject = cacert.getSubjectDN().getName();
			int index = caSubject.lastIndexOf(",");
			String subjectPrefix = caSubject.substring(0, index);
			if (getDorianUtils().getIdentityFederationProperties().getIdentityAssignmentPolicy().equals(IdentityAssignmentPolicy.NAME)) {
				adminIdentity = CertUtil.subjectToIdentity(subjectPrefix + ",OU=" + getDorianUtils().getIdentityProviderProperties().getName() + "/CN=" + userId);
			} else {
				adminIdentity = CertUtil.subjectToIdentity(subjectPrefix + ",OU=IdP [1]/CN=" + userId);
			}
			System.out.println(adminIdentity);
		}
		return adminIdentity;
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

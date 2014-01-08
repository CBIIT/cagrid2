package org.cagrid.mms.test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.ops4j.pax.exam.CoreOptions.maven;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.net.ssl.KeyManager;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileExtendOption;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileReplacementOption;
import org.cagrid.core.common.security.CredentialFactory;
import org.cagrid.core.common.security.X509Credential;
import org.cagrid.core.soapclient.SingleEntityKeyManager;
import org.cagrid.mms.service.MetadataModelService;
import org.cagrid.mms.soapclient.MMSSoapClientFactory;
import org.cagrid.mms.test.utils.MMSTestUtils;
import org.cagrid.mms.wsrf.stubs.MetadataModelServicePortType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class MMSFunctionalTest extends CaGridTestSupport {

    private static final String MMS_URL = "https://localhost:8080/mms";

    private static final String HOST = "etc/cagrid-mms/host.jks";
    private static final String TRUSTSTORE = "etc/cagrid-mms/truststore.jks";
    private static final String TRUSTSTORETYPE = "JKS";
    private static final String KEYALIAS = "tomcat";
    private static final String TRUSTSTOREPASSWORD = "inventrio";
    private static final String KEYSTOREPASSWORD = "inventrio";
    private static final String KEYPASSWORD = "inventrio";


    @Override
    @Configuration
    public Option[] config() {
        Option[] options = new Option[] {
                // Install GME feature
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresRepositories", "," + maven().groupId("org.cagrid").artifactId("cagrid-features").versionAsInProject().classifier("features").type("xml").getURL()),
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresBoot", ",cagrid-mms"),

                // Get our resource files to the "etc" area
                new KarafDistributionConfigurationFileReplacementOption("etc/cagrid.mms.wsrf.cfg", new File("src/test/resources/cagrid.mms.wsrf.cfg")),
                new KarafDistributionConfigurationFileReplacementOption(HOST, new File("src/test/resources/host.jks")),
                new KarafDistributionConfigurationFileReplacementOption(TRUSTSTORE, new File("src/test/resources/truststore.jks")),
                new KarafDistributionConfigurationFileReplacementOption(MMSTestUtils.SERVICEMETADATA, new File("src/test/resources/serviceMetadata.xml")),
                new KarafDistributionConfigurationFileReplacementOption(MMSTestUtils.SERVICESECURITYMETADATA, new File("src/test/resources/serviceSecurityMetadata.xml"))

                // seeing once in a while an spurious linkage error:
                // java.lang.LinkageError: loader constraint violation: loader (instance of <bootloader>) previously initiated loading for a different type with name "javax/xml/soap/SOAPFault"
                // adding this to get some info:
                // System.err.println(executeCommand("packages:exports | grep javax.xml.soap"));
                // System.err.println(executeCommand("osgi:list"));
                // it seems there could be a conflict between the one included with the jre and the saaj feature in
                // servicemix, both jars have this class (javax.xml.soap.SOAPFault)
                , new KarafDistributionConfigurationFileExtendOption("etc/jre.properties", "jre-1.6", ",javax.xml.soap;version=\"1.3\"")
                , new KarafDistributionConfigurationFileExtendOption("etc/jre.properties", "jre-1.7", ",javax.xml.soap;version=\"1.3\"")
        };
        return CaGridTestSupport.concatAll(super.config(), options);
    }

    @Test
    public void testFunctionalGME() {
        try {
            System.err.println(executeCommand("features:list"));
            assertBundleInstalled("cagrid-mms-api");
            assertBundleInstalled("cagrid-mms-service");
            assertBundleInstalled("cagrid-mms-wsrf");

            MetadataModelService mmsService = getOsgiService(MetadataModelService.class, 30000L);
            assertNotNull(mmsService);

            // get gme soap client
            MetadataModelServicePortType mms = getMMSSoapClient();
            assertNotNull(mms);
//
//            // publish schemas
//            //publishXMLSchemas(gme);
//
//            // get schemas
//            List<XMLSchemaNamespace> schemas = getXMLSchemaNamespaces(gme);
//            assertEquals(3, schemas.size());
//
//            // publish bad schemas test
//            publishBadXMLSchemas(gme);
//            
//            getXMLSchema(gme);
            
        } catch(Exception e) {
            fail(ExceptionUtils.getFullStackTrace(e));
        }
    }

    private MetadataModelServicePortType getMMSSoapClient() throws GeneralSecurityException, IOException {
        KeyStoreType truststore = new KeyStoreType();
        truststore.setFile(TRUSTSTORE);
        truststore.setType(TRUSTSTORETYPE);
        truststore.setPassword(TRUSTSTOREPASSWORD);

        X509Credential credential = CredentialFactory.getCredential(
                HOST,
                KEYSTOREPASSWORD,
                KEYALIAS,
                KEYPASSWORD);

        KeyManager keyManager = new SingleEntityKeyManager(KEYALIAS, credential);

        MetadataModelServicePortType mmsPort = MMSSoapClientFactory.createSoapClient(MMS_URL, truststore, keyManager);
        Client client = ClientProxy.getClient(mmsPort);
        client.getInInterceptors().add(new LoggingInInterceptor());
        client.getOutInterceptors().add(new LoggingOutInterceptor()); 
 
        return mmsPort;
    }


}

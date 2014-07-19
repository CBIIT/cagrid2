package org.cagrid.index.soapclient;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.net.ssl.KeyManager;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.configuration.Configurer;
import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.cagrid.core.common.security.SSLConfigurer;
import org.cagrid.core.common.security.X509Credential;
import org.cagrid.index.service.IndexService;
import org.cagrid.index.wsrf.stubs.BigIndexPortType;

public class IndexServiceSoapClientFactory {

    public static BigIndexPortType createSoapClient(String url) {

        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();
        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(IndexService.class);
        cf.setBus(bus);
        BigIndexPortType IndexServicePort = cf.create(BigIndexPortType.class);
        return IndexServicePort;
    }

    public static BigIndexPortType createSoapClient(String url, KeyStoreType truststore, KeyManagersType keyManager)
            throws GeneralSecurityException, IOException {

        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();
        Configurer baseConf = bus.getExtension(Configurer.class);
        SSLConfigurer sslConf = new SSLConfigurer(baseConf);
        sslConf.setTruststore(truststore);
        sslConf.setKeystore(keyManager);
        bus.setExtension(sslConf, Configurer.class);

        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(IndexService.class);
        cf.setBus(bus);
        BigIndexPortType IndexServicePort = cf.create(BigIndexPortType.class);
        return IndexServicePort;
    }

    public static BigIndexPortType createSoapClient(String url, KeyStoreType truststore, X509Credential cred)
            throws GeneralSecurityException, IOException {

        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();
        Configurer baseConf = bus.getExtension(Configurer.class);
        SSLConfigurer sslConf = new SSLConfigurer(baseConf);
        sslConf.setTruststore(truststore);
        sslConf.setCredential(cred);
        bus.setExtension(sslConf, Configurer.class);

        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(IndexService.class);
        cf.setBus(bus);
        BigIndexPortType IndexServicePort = cf.create(BigIndexPortType.class);
        return IndexServicePort;
    }

    public static BigIndexPortType createSoapClient(String url, KeyStoreType truststore, KeyManager keyManager)
            throws GeneralSecurityException, IOException {
        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();
        Configurer baseConf = bus.getExtension(Configurer.class);
        SSLConfigurer sslConf = new SSLConfigurer(baseConf);
        sslConf.setTruststore(truststore);
        sslConf.setKm(new KeyManager[] { keyManager });
        bus.setExtension(sslConf, Configurer.class);

        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(IndexService.class);
        cf.setBus(bus);
        BigIndexPortType IndexServicePort = cf.create(BigIndexPortType.class);
        return IndexServicePort;
    }

}

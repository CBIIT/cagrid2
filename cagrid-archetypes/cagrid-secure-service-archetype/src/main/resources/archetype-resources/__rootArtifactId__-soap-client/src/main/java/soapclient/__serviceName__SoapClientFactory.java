#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.soapclient;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.configuration.Configurer;
import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import ${package}.wsrf.service.${serviceName}Service;
import ${package}.wsrf.stubs.${serviceName}PortType;

import javax.net.ssl.KeyManager;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class ${serviceName}SoapClientFactory {

    public static ${serviceName}PortType createSoapClient(String url) {
        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();
        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(${serviceName}Service.class);
        cf.setBus(bus);
        ${serviceName}PortType port = cf.create(${serviceName}PortType.class);
        return port;
    }

    public static ${serviceName}PortType createSoapClient(String url, KeyStoreType truststore, KeyManagersType keyManager) throws GeneralSecurityException, IOException {
        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();
        Configurer baseConf = bus.getExtension(Configurer.class);
        ${groupId}.core.common.security.SSLConfigurer sslConf = new ${groupId}.core.common.security.SSLConfigurer(baseConf);
        sslConf.setTruststore(truststore);
        sslConf.setKeystore(keyManager);
        bus.setExtension(sslConf, Configurer.class);

        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(${serviceName}Service.class);
        cf.setBus(bus);
        ${serviceName}PortType port = cf.create(${serviceName}PortType.class);
        return port;
    }

    public static ${serviceName}PortType createSoapClient(String url, KeyStoreType truststore, KeyManager keyManager) throws GeneralSecurityException, IOException {
        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();
        Configurer baseConf = bus.getExtension(Configurer.class);
        ${groupId}.core.common.security.SSLConfigurer sslConf = new ${groupId}.core.common.security.SSLConfigurer(baseConf);
        sslConf.setTruststore(truststore);
        sslConf.setKm(new KeyManager[]{keyManager});
        bus.setExtension(sslConf, Configurer.class);

        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(${serviceName}Service.class);
        cf.setBus(bus);
        ${serviceName}PortType port = cf.create(${serviceName}PortType.class);
        return port;
    }


}

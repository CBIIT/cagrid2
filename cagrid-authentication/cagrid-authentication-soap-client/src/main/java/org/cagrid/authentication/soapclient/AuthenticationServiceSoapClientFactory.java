package org.cagrid.authentication.soapclient;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.cagrid.authenticationService.wsrf.service.AuthenticationServiceService;
import org.cagrid.gaards.authentication.authenticationservice.AuthenticationServicePortType;

public class AuthenticationServiceSoapClientFactory {

    public static AuthenticationServicePortType createSoapClient(String url) {
        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();


        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(AuthenticationServiceService.class);
        cf.setBus(bus);
        AuthenticationServicePortType gridGrouperPort = cf.create(AuthenticationServicePortType.class);
        return gridGrouperPort;
    }
}

package org.cagrid.index.wsrf.service;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import org.cagrid.index.wsrf.stubs.BigIndexEntryPortType;

/**
 * This class was generated by Apache CXF 2.6.3
 * 2014-04-11T08:38:02.673-04:00
 * Generated source version: 2.6.3
 * 
 */
@WebServiceClient(name = "BigIndexEntryService", 
                  wsdlLocation = "/schema/org/cagrid/index/big_index_entry_service.wsdl",
                  targetNamespace = "http://mds.globus.org/bigindex/2008/11/24/service") 
public class BigIndexEntryService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://mds.globus.org/bigindex/2008/11/24/service", "BigIndexEntryService");
    public final static QName BigIndexEntryPortTypePort = new QName("http://mds.globus.org/bigindex/2008/11/24/service", "BigIndexEntryPortTypePort");
    static {
        URL url = BigIndexEntryService.class.getResource("/schema/org/cagrid/index/big_index_entry_service.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(BigIndexEntryService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "/schema/org/cagrid/index/big_index_entry_service.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public BigIndexEntryService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public BigIndexEntryService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BigIndexEntryService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns BigIndexEntryPortType
     */
    @WebEndpoint(name = "BigIndexEntryPortTypePort")
    public BigIndexEntryPortType getBigIndexEntryPortTypePort() {
        return super.getPort(BigIndexEntryPortTypePort, BigIndexEntryPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BigIndexEntryPortType
     */
    @WebEndpoint(name = "BigIndexEntryPortTypePort")
    public BigIndexEntryPortType getBigIndexEntryPortTypePort(WebServiceFeature... features) {
        return super.getPort(BigIndexEntryPortTypePort, BigIndexEntryPortType.class, features);
    }

}

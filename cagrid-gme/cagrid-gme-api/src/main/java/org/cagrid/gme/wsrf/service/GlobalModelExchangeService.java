package org.cagrid.gme.wsrf.service;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;
import org.cagrid.gme.wsrf.stubs.GlobalModelExchangePortType;

/**
 * This class was generated by Apache CXF 2.6.3
 * 2013-06-12T15:14:46.550-04:00
 * Generated source version: 2.6.3
 * 
 */
@WebServiceClient(name = "GlobalModelExchangeService", 
                  wsdlLocation = "/schema/org/cagrid/gme/GlobalModelExchange_Service.wsdl",
                  targetNamespace = "http://gme.cagrid.org/GlobalModelExchange/service") 
public class GlobalModelExchangeService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://gme.cagrid.org/GlobalModelExchange/service", "GlobalModelExchangeService");
    public final static QName GlobalModelExchangePortTypePort = new QName("http://gme.cagrid.org/GlobalModelExchange/service", "GlobalModelExchangePortTypePort");
    static {
        URL url = GlobalModelExchangeService.class.getResource("/schema/org/cagrid/gme/GlobalModelExchange_Service.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(GlobalModelExchangeService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "/schema/org/cagrid/gme/GlobalModelExchange_Service.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public GlobalModelExchangeService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public GlobalModelExchangeService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GlobalModelExchangeService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns GlobalModelExchangePortType
     */
    @WebEndpoint(name = "GlobalModelExchangePortTypePort")
    public GlobalModelExchangePortType getGlobalModelExchangePortTypePort() {
        return super.getPort(GlobalModelExchangePortTypePort, GlobalModelExchangePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GlobalModelExchangePortType
     */
    @WebEndpoint(name = "GlobalModelExchangePortTypePort")
    public GlobalModelExchangePortType getGlobalModelExchangePortTypePort(WebServiceFeature... features) {
        return super.getPort(GlobalModelExchangePortTypePort, GlobalModelExchangePortType.class, features);
    }

}

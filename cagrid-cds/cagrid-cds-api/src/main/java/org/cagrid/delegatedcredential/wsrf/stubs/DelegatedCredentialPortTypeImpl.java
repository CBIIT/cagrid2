
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package org.cagrid.delegatedcredential.wsrf.stubs;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.6.3
 * 2013-05-06T17:03:48.083-04:00
 * Generated source version: 2.6.3
 * 
 */

@WebService(
                      serviceName = "DelegatedCredentialService",
                      portName = "DelegatedCredentialPortTypePort",
                      targetNamespace = "http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential/service",
                      wsdlLocation = "/schema/org/cagrid/cds/DelegatedCredential_service.wsdl",
                      endpointInterface = "org.cagrid.delegatedcredential.wsrf.stubs.DelegatedCredentialPortType")
                      
public class DelegatedCredentialPortTypeImpl implements DelegatedCredentialPortType {

    private static final Logger LOG = Logger.getLogger(DelegatedCredentialPortTypeImpl.class.getName());

    /* (non-Javadoc)
     * @see org.cagrid.delegatedcredential.wsrf.stubs.DelegatedCredentialPortType#destroy(*
     */
    public void destroy() throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceNotDestroyedFault    { 
        LOG.info("Executing operation destroy");
        try {
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault("ResourceUnknownFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceNotDestroyedFault("ResourceNotDestroyedFault...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.delegatedcredential.wsrf.stubs.DelegatedCredentialPortType#getServiceSecurityMetadata(org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataRequest  parameters )*
     */
    public org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse getServiceSecurityMetadata(org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataRequest parameters) { 
        LOG.info("Executing operation getServiceSecurityMetadata");
        System.out.println(parameters);
        try {
            org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see org.cagrid.delegatedcredential.wsrf.stubs.DelegatedCredentialPortType#getDelegatedCredential(org.cagrid.delegatedcredential.wsrf.stubs.GetDelegatedCredentialRequest  parameters )*
     */
    public GetDelegatedCredentialResponse getDelegatedCredential(GetDelegatedCredentialRequest parameters) throws DelegationFaultFaultMessage , PermissionDeniedFaultFaultMessage , CDSInternalFaultFaultMessage    {
        LOG.info("Executing operation getDelegatedCredential");
        System.out.println(parameters);
        try {
            GetDelegatedCredentialResponse _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new DelegationFaultFaultMessage("DelegationFaultFaultMessage...");
        //throw new PermissionDeniedFaultFaultMessage("PermissionDeniedFaultFaultMessage...");
        //throw new CDSInternalFaultFaultMessage("CDSInternalFaultFaultMessage...");
    }

}

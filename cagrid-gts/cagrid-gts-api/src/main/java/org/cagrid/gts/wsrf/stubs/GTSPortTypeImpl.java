
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package org.cagrid.gts.wsrf.stubs;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.3
 * 2013-05-03T15:36:59.709-04:00
 * Generated source version: 2.6.3
 * 
 */

@javax.jws.WebService(
                      serviceName = "GTSService",
                      portName = "GTSPortTypePort",
                      targetNamespace = "http://cagrid.nci.nih.gov/GTS/service",
                      wsdlLocation = "/schema/org/cagrid/gts/GTS_Service.wsdl",
                      endpointInterface = "org.cagrid.gts.wsrf.stubs.GTSPortType")
                      
public class GTSPortTypeImpl implements GTSPortType {

    private static final Logger LOG = Logger.getLogger(GTSPortTypeImpl.class.getName());

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#getAuthorities(org.cagrid.gts.wsrf.stubs.GetAuthoritiesRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.GetAuthoritiesResponse getAuthorities(GetAuthoritiesRequest parameters) throws GTSInternalFaultFaultMessage    { 
        LOG.info("Executing operation getAuthorities");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.GetAuthoritiesResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#getServiceSecurityMetadata(org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataRequest  parameters )*
     */
    public org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse getServiceSecurityMetadata(org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataRequest parameters) { 
        LOG.info("Executing operation getServiceSecurityMetadata");
        System.out.println(parameters);
        try {
            org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#addPermission(org.cagrid.gts.wsrf.stubs.AddPermissionRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.AddPermissionResponse addPermission(AddPermissionRequest parameters) throws GTSInternalFaultFaultMessage , PermissionDeniedFaultFaultMessage , IllegalPermissionFaultFaultMessage    { 
        LOG.info("Executing operation addPermission");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.AddPermissionResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
        //throw new PermissionDeniedFaultFaultMessage("PermissionDeniedFaultFaultMessage...");
        //throw new IllegalPermissionFaultFaultMessage("IllegalPermissionFaultFaultMessage...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#getMultipleResourceProperties(org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourceProperties  getMultipleResourcePropertiesRequest )*
     */
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourcePropertiesResponse getMultipleResourceProperties(org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourceProperties getMultipleResourcePropertiesRequest) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault    { 
        LOG.info("Executing operation getMultipleResourceProperties");
        System.out.println(getMultipleResourcePropertiesRequest);
        try {
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourcePropertiesResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault("ResourceUnknownFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault("InvalidResourcePropertyQNameFault...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#updateCRL(org.cagrid.gts.wsrf.stubs.UpdateCRLRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.UpdateCRLResponse updateCRL(UpdateCRLRequest parameters) throws GTSInternalFaultFaultMessage , IllegalTrustedAuthorityFaultFaultMessage , InvalidTrustedAuthorityFaultFaultMessage , PermissionDeniedFaultFaultMessage    { 
        LOG.info("Executing operation updateCRL");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.UpdateCRLResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
        //throw new IllegalTrustedAuthorityFaultFaultMessage("IllegalTrustedAuthorityFaultFaultMessage...");
        //throw new InvalidTrustedAuthorityFaultFaultMessage("InvalidTrustedAuthorityFaultFaultMessage...");
        //throw new PermissionDeniedFaultFaultMessage("PermissionDeniedFaultFaultMessage...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#queryResourceProperties(org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties  queryResourcePropertiesRequest )*
     */
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse queryResourceProperties(org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties queryResourcePropertiesRequest) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault    { 
        LOG.info("Executing operation queryResourceProperties");
        System.out.println(queryResourcePropertiesRequest);
        try {
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault("ResourceUnknownFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault("QueryEvaluationErrorFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault("UnknownQueryExpressionDialectFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault("InvalidResourcePropertyQNameFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault("InvalidQueryExpressionFault...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#updateTrustLevel(org.cagrid.gts.wsrf.stubs.UpdateTrustLevelRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.UpdateTrustLevelResponse updateTrustLevel(UpdateTrustLevelRequest parameters) throws GTSInternalFaultFaultMessage , PermissionDeniedFaultFaultMessage , IllegalTrustLevelFaultFaultMessage , InvalidTrustLevelFaultFaultMessage    { 
        LOG.info("Executing operation updateTrustLevel");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.UpdateTrustLevelResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
        //throw new PermissionDeniedFaultFaultMessage("PermissionDeniedFaultFaultMessage...");
        //throw new IllegalTrustLevelFaultFaultMessage("IllegalTrustLevelFaultFaultMessage...");
        //throw new InvalidTrustLevelFaultFaultMessage("InvalidTrustLevelFaultFaultMessage...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#updateAuthority(org.cagrid.gts.wsrf.stubs.UpdateAuthorityRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.UpdateAuthorityResponse updateAuthority(UpdateAuthorityRequest parameters) throws GTSInternalFaultFaultMessage , PermissionDeniedFaultFaultMessage , InvalidAuthorityFaultFaultMessage , IllegalAuthorityFaultFaultMessage    { 
        LOG.info("Executing operation updateAuthority");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.UpdateAuthorityResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
        //throw new PermissionDeniedFaultFaultMessage("PermissionDeniedFaultFaultMessage...");
        //throw new InvalidAuthorityFaultFaultMessage("InvalidAuthorityFaultFaultMessage...");
        //throw new IllegalAuthorityFaultFaultMessage("IllegalAuthorityFaultFaultMessage...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#addAuthority(org.cagrid.gts.wsrf.stubs.AddAuthorityRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.AddAuthorityResponse addAuthority(AddAuthorityRequest parameters) throws GTSInternalFaultFaultMessage , PermissionDeniedFaultFaultMessage , IllegalAuthorityFaultFaultMessage    { 
        LOG.info("Executing operation addAuthority");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.AddAuthorityResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
        //throw new PermissionDeniedFaultFaultMessage("PermissionDeniedFaultFaultMessage...");
        //throw new IllegalAuthorityFaultFaultMessage("IllegalAuthorityFaultFaultMessage...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#updateTrustedAuthority(org.cagrid.gts.wsrf.stubs.UpdateTrustedAuthorityRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.UpdateTrustedAuthorityResponse updateTrustedAuthority(UpdateTrustedAuthorityRequest parameters) throws GTSInternalFaultFaultMessage , IllegalTrustedAuthorityFaultFaultMessage , InvalidTrustedAuthorityFaultFaultMessage , PermissionDeniedFaultFaultMessage    { 
        LOG.info("Executing operation updateTrustedAuthority");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.UpdateTrustedAuthorityResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
        //throw new IllegalTrustedAuthorityFaultFaultMessage("IllegalTrustedAuthorityFaultFaultMessage...");
        //throw new InvalidTrustedAuthorityFaultFaultMessage("InvalidTrustedAuthorityFaultFaultMessage...");
        //throw new PermissionDeniedFaultFaultMessage("PermissionDeniedFaultFaultMessage...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#removeTrustedAuthority(org.cagrid.gts.wsrf.stubs.RemoveTrustedAuthorityRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.RemoveTrustedAuthorityResponse removeTrustedAuthority(RemoveTrustedAuthorityRequest parameters) throws GTSInternalFaultFaultMessage , InvalidTrustedAuthorityFaultFaultMessage , PermissionDeniedFaultFaultMessage    { 
        LOG.info("Executing operation removeTrustedAuthority");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.RemoveTrustedAuthorityResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
        //throw new InvalidTrustedAuthorityFaultFaultMessage("InvalidTrustedAuthorityFaultFaultMessage...");
        //throw new PermissionDeniedFaultFaultMessage("PermissionDeniedFaultFaultMessage...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#removeAuthority(org.cagrid.gts.wsrf.stubs.RemoveAuthorityRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.RemoveAuthorityResponse removeAuthority(RemoveAuthorityRequest parameters) throws GTSInternalFaultFaultMessage , InvalidAuthorityFaultFaultMessage , PermissionDeniedFaultFaultMessage    { 
        LOG.info("Executing operation removeAuthority");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.RemoveAuthorityResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
        //throw new InvalidAuthorityFaultFaultMessage("InvalidAuthorityFaultFaultMessage...");
        //throw new PermissionDeniedFaultFaultMessage("PermissionDeniedFaultFaultMessage...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#findPermissions(org.cagrid.gts.wsrf.stubs.FindPermissionsRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.FindPermissionsResponse findPermissions(FindPermissionsRequest parameters) throws GTSInternalFaultFaultMessage , PermissionDeniedFaultFaultMessage    { 
        LOG.info("Executing operation findPermissions");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.FindPermissionsResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
        //throw new PermissionDeniedFaultFaultMessage("PermissionDeniedFaultFaultMessage...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#addTrustLevel(org.cagrid.gts.wsrf.stubs.AddTrustLevelRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.AddTrustLevelResponse addTrustLevel(AddTrustLevelRequest parameters) throws GTSInternalFaultFaultMessage , IllegalTrustLevelFaultFaultMessage , PermissionDeniedFaultFaultMessage    { 
        LOG.info("Executing operation addTrustLevel");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.AddTrustLevelResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
        //throw new IllegalTrustLevelFaultFaultMessage("IllegalTrustLevelFaultFaultMessage...");
        //throw new PermissionDeniedFaultFaultMessage("PermissionDeniedFaultFaultMessage...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#addTrustedAuthority(org.cagrid.gts.wsrf.stubs.AddTrustedAuthorityRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.AddTrustedAuthorityResponse addTrustedAuthority(AddTrustedAuthorityRequest parameters) throws GTSInternalFaultFaultMessage , IllegalTrustedAuthorityFaultFaultMessage , PermissionDeniedFaultFaultMessage    { 
        LOG.info("Executing operation addTrustedAuthority");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.AddTrustedAuthorityResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
        //throw new IllegalTrustedAuthorityFaultFaultMessage("IllegalTrustedAuthorityFaultFaultMessage...");
        //throw new PermissionDeniedFaultFaultMessage("PermissionDeniedFaultFaultMessage...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#getResourceProperty(javax.xml.namespace.QName  getResourcePropertyRequest )*
     */
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse getResourceProperty(javax.xml.namespace.QName getResourcePropertyRequest) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault    { 
        LOG.info("Executing operation getResourceProperty");
        System.out.println(getResourcePropertyRequest);
        try {
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault("ResourceUnknownFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault("InvalidResourcePropertyQNameFault...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#updateAuthorityPriorities(org.cagrid.gts.wsrf.stubs.UpdateAuthorityPrioritiesRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.UpdateAuthorityPrioritiesResponse updateAuthorityPriorities(UpdateAuthorityPrioritiesRequest parameters) throws GTSInternalFaultFaultMessage , PermissionDeniedFaultFaultMessage , IllegalAuthorityFaultFaultMessage    { 
        LOG.info("Executing operation updateAuthorityPriorities");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.UpdateAuthorityPrioritiesResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
        //throw new PermissionDeniedFaultFaultMessage("PermissionDeniedFaultFaultMessage...");
        //throw new IllegalAuthorityFaultFaultMessage("IllegalAuthorityFaultFaultMessage...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#removeTrustLevel(org.cagrid.gts.wsrf.stubs.RemoveTrustLevelRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.RemoveTrustLevelResponse removeTrustLevel(RemoveTrustLevelRequest parameters) throws GTSInternalFaultFaultMessage , PermissionDeniedFaultFaultMessage , IllegalTrustLevelFaultFaultMessage , InvalidTrustLevelFaultFaultMessage    { 
        LOG.info("Executing operation removeTrustLevel");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.RemoveTrustLevelResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
        //throw new PermissionDeniedFaultFaultMessage("PermissionDeniedFaultFaultMessage...");
        //throw new IllegalTrustLevelFaultFaultMessage("IllegalTrustLevelFaultFaultMessage...");
        //throw new InvalidTrustLevelFaultFaultMessage("InvalidTrustLevelFaultFaultMessage...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#findTrustedAuthorities(org.cagrid.gts.wsrf.stubs.FindTrustedAuthoritiesRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.FindTrustedAuthoritiesResponse findTrustedAuthorities(FindTrustedAuthoritiesRequest parameters) { 
        LOG.info("Executing operation findTrustedAuthorities");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.FindTrustedAuthoritiesResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#getTrustLevels(org.cagrid.gts.wsrf.stubs.GetTrustLevelsRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.GetTrustLevelsResponse getTrustLevels(GetTrustLevelsRequest parameters) throws GTSInternalFaultFaultMessage    { 
        LOG.info("Executing operation getTrustLevels");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.GetTrustLevelsResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#validate(org.cagrid.gts.wsrf.stubs.ValidateRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.ValidateResponse validate(ValidateRequest parameters) throws GTSInternalFaultFaultMessage , CertificateValidationFaultFaultMessage    { 
        LOG.info("Executing operation validate");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.ValidateResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
        //throw new CertificateValidationFaultFaultMessage("CertificateValidationFaultFaultMessage...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.gts.wsrf.stubs.GTSPortType#revokePermission(org.cagrid.gts.wsrf.stubs.RevokePermissionRequest  parameters )*
     */
    public org.cagrid.gts.wsrf.stubs.RevokePermissionResponse revokePermission(RevokePermissionRequest parameters) throws GTSInternalFaultFaultMessage , InvalidPermissionFaultFaultMessage , PermissionDeniedFaultFaultMessage    { 
        LOG.info("Executing operation revokePermission");
        System.out.println(parameters);
        try {
            org.cagrid.gts.wsrf.stubs.RevokePermissionResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new GTSInternalFaultFaultMessage("GTSInternalFaultFaultMessage...");
        //throw new InvalidPermissionFaultFaultMessage("InvalidPermissionFaultFaultMessage...");
        //throw new PermissionDeniedFaultFaultMessage("PermissionDeniedFaultFaultMessage...");
    }

}
package org.cagrid.dorian;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.3
 * 2013-06-10T14:11:33.519-04:00
 * Generated source version: 2.6.3
 * 
 */
@WebService(targetNamespace = "http://cagrid.nci.nih.gov/Dorian", name = "DorianPortType")
@XmlSeeAlso({gov.nih.nci.cagrid.metadata.ObjectFactory.class, org.cagrid.dorian.model.exceptions.ObjectFactory.class, ObjectFactory.class, org.cagrid.gaards.authentication.types.ObjectFactory.class, org.cagrid.dorian.model.idp.ObjectFactory.class, org.cagrid.dorian.policy.ObjectFactory.class, org.cagrid.gaards.security.servicesecurity.ObjectFactory.class, org.w3._2000._09.xmldsig.ObjectFactory.class, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.ObjectFactory.class, org.xmlsoap.schemas.ws._2004._03.addressing.ObjectFactory.class, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_basefaults_1_2_draft_01.ObjectFactory.class, org.cagrid.gaards.authentication.service.ObjectFactory.class, org.oasis.names.tc.saml.assertion.ObjectFactory.class, org.cagrid.gaards.credentials.ObjectFactory.class, gov.nih.nci.cagrid.metadata.security.ObjectFactory.class, org.cagrid.gaards.authentication.ObjectFactory.class, org.cagrid.gaards.authentication.lockout.ObjectFactory.class, gov.nih.nci.cagrid.metadata.common.ObjectFactory.class, org.cagrid.dorian.model.federation.ObjectFactory.class, org.cagrid.gaards.authentication.faults.ObjectFactory.class, gov.nih.nci.cagrid.metadata.service.ObjectFactory.class, org.cagrid.dorian.common.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface DorianPortType {

    @WebResult(name = "GetMultipleResourcePropertiesResponse", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", partName = "GetMultipleResourcePropertiesResponse")
    @WebMethod(operationName = "GetMultipleResourceProperties", action = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties/GetMultipleResourceProperties")
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourcePropertiesResponse getMultipleResourceProperties(
        @WebParam(partName = "GetMultipleResourcePropertiesRequest", name = "GetMultipleResourceProperties", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd")
        org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourceProperties getMultipleResourcePropertiesRequest
    ) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault;

    @WebResult(name = "SetPublishResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/SetPublishRequest")
    public SetPublishResponse setPublish(
        @WebParam(partName = "parameters", name = "SetPublishRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        SetPublishRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage, InvalidTrustedIdPFaultFaultMessage;

    @WebResult(name = "GetResourcePropertyResponse", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", partName = "GetResourcePropertyResponse")
    @WebMethod(operationName = "GetResourceProperty", action = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties/GetResourceProperty")
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse getResourceProperty(
        @WebParam(partName = "GetResourcePropertyRequest", name = "GetResourceProperty", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd")
        javax.xml.namespace.QName getResourcePropertyRequest
    ) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault;

    @WebResult(name = "GetPublishResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/GetPublishRequest")
    public GetPublishResponse getPublish(
        @WebParam(partName = "parameters", name = "GetPublishRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        GetPublishRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage, InvalidTrustedIdPFaultFaultMessage;

    @WebResult(name = "UpdateTrustedIdPResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/UpdateTrustedIdPRequest")
    public UpdateTrustedIdPResponse updateTrustedIdP(
        @WebParam(partName = "parameters", name = "UpdateTrustedIdPRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        UpdateTrustedIdPRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage, InvalidTrustedIdPFaultFaultMessage;

    @WebResult(name = "GetTrustedIdPsResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/GetTrustedIdPsRequest")
    public GetTrustedIdPsResponse getTrustedIdPs(
        @WebParam(partName = "parameters", name = "GetTrustedIdPsRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        GetTrustedIdPsRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage;

    @WebResult(name = "PerformFederationAuditResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/PerformFederationAuditRequest")
    public PerformFederationAuditResponse performFederationAudit(
        @WebParam(partName = "parameters", name = "PerformFederationAuditRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        PerformFederationAuditRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage;

    @WebResult(name = "FindUserCertificatesResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/FindUserCertificatesRequest")
    public FindUserCertificatesResponse findUserCertificates(
        @WebParam(partName = "parameters", name = "FindUserCertificatesRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        FindUserCertificatesRequest parameters
    ) throws DorianInternalFaultFaultMessage, InvalidUserCertificateFaultFaultMessage, PermissionDeniedFaultFaultMessage;

    @WebResult(name = "FindGridUsersResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/FindGridUsersRequest")
    public FindGridUsersResponse findGridUsers(
        @WebParam(partName = "parameters", name = "FindGridUsersRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        FindGridUsersRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage;

    @WebResult(name = "AuthenticateUserResponse", targetNamespace = "http://authentication.gaards.cagrid.org/AuthenticationService", partName = "parameters")
    @WebMethod(action = "http://authentication.gaards.cagrid.org/AuthenticationService/AuthenticateUserRequest")
    public org.cagrid.gaards.authentication.AuthenticateUserResponse authenticateUser(
        @WebParam(partName = "parameters", name = "AuthenticateUserRequest", targetNamespace = "http://authentication.gaards.cagrid.org/AuthenticationService")
        org.cagrid.gaards.authentication.AuthenticateUserRequest parameters
    ) throws org.cagrid.gaards.authentication.InsufficientAttributeFaultFaultMessage, org.cagrid.gaards.authentication.AuthenticationProviderFaultFaultMessage, org.cagrid.gaards.authentication.InvalidCredentialFaultFaultMessage, org.cagrid.gaards.authentication.CredentialNotSupportedFaultFaultMessage;

    @WebResult(name = "FindLocalUsersResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/FindLocalUsersRequest")
    public FindLocalUsersResponse findLocalUsers(
        @WebParam(partName = "parameters", name = "FindLocalUsersRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        FindLocalUsersRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage;

    @WebResult(name = "RequestHostCertificateResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/RequestHostCertificateRequest")
    public RequestHostCertificateResponse requestHostCertificate(
        @WebParam(partName = "parameters", name = "RequestHostCertificateRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        RequestHostCertificateRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage, InvalidHostCertificateRequestFaultFaultMessage, InvalidHostCertificateFaultFaultMessage;

    @WebResult(name = "GetOwnedHostCertificatesResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/GetOwnedHostCertificatesRequest")
    public GetOwnedHostCertificatesResponse getOwnedHostCertificates(
        @WebParam(partName = "parameters", name = "GetOwnedHostCertificatesRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        GetOwnedHostCertificatesRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage;

    @WebResult(name = "GetAccountProfileResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/GetAccountProfileRequest")
    public GetAccountProfileResponse getAccountProfile(
        @WebParam(partName = "parameters", name = "GetAccountProfileRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        GetAccountProfileRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage;

    @WebResult(name = "GetGridUserPoliciesResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/GetGridUserPoliciesRequest")
    public GetGridUserPoliciesResponse getGridUserPolicies(
        @WebParam(partName = "parameters", name = "GetGridUserPoliciesRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        GetGridUserPoliciesRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage;

    @WebResult(name = "UserSearchResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/UserSearchRequest")
    public UserSearchResponse userSearch(
        @WebParam(partName = "parameters", name = "UserSearchRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        UserSearchRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage;

    @WebResult(name = "HostSearchResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/HostSearchRequest")
    public HostSearchResponse hostSearch(
        @WebParam(partName = "parameters", name = "HostSearchRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        HostSearchRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage;

    @WebResult(name = "AddTrustedIdPResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/AddTrustedIdPRequest")
    public AddTrustedIdPResponse addTrustedIdP(
        @WebParam(partName = "parameters", name = "AddTrustedIdPRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        AddTrustedIdPRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage, InvalidTrustedIdPFaultFaultMessage;

    @WebResult(name = "RegisterWithIdPResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/RegisterWithIdPRequest")
    public RegisterWithIdPResponse registerWithIdP(
        @WebParam(partName = "parameters", name = "RegisterWithIdPRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        RegisterWithIdPRequest parameters
    ) throws DorianInternalFaultFaultMessage, InvalidUserPropertyFaultFaultMessage;

    @WebResult(name = "UpdateGridUserResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/UpdateGridUserRequest")
    public UpdateGridUserResponse updateGridUser(
        @WebParam(partName = "parameters", name = "UpdateGridUserRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        UpdateGridUserRequest parameters
    ) throws DorianInternalFaultFaultMessage, PermissionDeniedFaultFaultMessage, InvalidUserFaultFaultMessage;

    @WebResult(name = "RemoveUserCertificateResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/RemoveUserCertificateRequest")
    public RemoveUserCertificateResponse removeUserCertificate(
        @WebParam(partName = "parameters", name = "RemoveUserCertificateRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        RemoveUserCertificateRequest parameters
    ) throws DorianInternalFaultFaultMessage, InvalidUserCertificateFaultFaultMessage, PermissionDeniedFaultFaultMessage;

    @WebResult(name = "RequestUserCertificateResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/RequestUserCertificateRequest")
    public RequestUserCertificateResponse requestUserCertificate(
        @WebParam(partName = "parameters", name = "RequestUserCertificateRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        RequestUserCertificateRequest parameters
    ) throws DorianInternalFaultFaultMessage, PermissionDeniedFaultFaultMessage, InvalidAssertionFaultFaultMessage, UserPolicyFaultFaultMessage;

    @WebResult(name = "RemoveTrustedIdPResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/RemoveTrustedIdPRequest")
    public RemoveTrustedIdPResponse removeTrustedIdP(
        @WebParam(partName = "parameters", name = "RemoveTrustedIdPRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        RemoveTrustedIdPRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage, InvalidTrustedIdPFaultFaultMessage;

    @WebResult(name = "UpdateLocalUserResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/UpdateLocalUserRequest")
    public UpdateLocalUserResponse updateLocalUser(
        @WebParam(partName = "parameters", name = "UpdateLocalUserRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        UpdateLocalUserRequest parameters
    ) throws DorianInternalFaultFaultMessage, PermissionDeniedFaultFaultMessage, NoSuchUserFaultFaultMessage;

    @WebResult(name = "ChangeLocalUserPasswordResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/ChangeLocalUserPasswordRequest")
    public ChangeLocalUserPasswordResponse changeLocalUserPassword(
        @WebParam(partName = "parameters", name = "ChangeLocalUserPasswordRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        ChangeLocalUserPasswordRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage, InvalidUserPropertyFaultFaultMessage;

    @WebResult(name = "RemoveLocalUserResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/RemoveLocalUserRequest")
    public RemoveLocalUserResponse removeLocalUser(
        @WebParam(partName = "parameters", name = "RemoveLocalUserRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        RemoveLocalUserRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage;

    @WebResult(name = "GetCACertificateResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/GetCACertificateRequest")
    public GetCACertificateResponse getCACertificate(
        @WebParam(partName = "parameters", name = "GetCACertificateRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        GetCACertificateRequest parameters
    ) throws DorianInternalFaultFaultMessage;

    @WebResult(name = "DoesLocalUserExistResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/DoesLocalUserExistRequest")
    public DoesLocalUserExistResponse doesLocalUserExist(
        @WebParam(partName = "parameters", name = "DoesLocalUserExistRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        DoesLocalUserExistRequest parameters
    ) throws DorianInternalFaultFaultMessage;

    @WebResult(name = "PerformIdentityProviderAuditResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/PerformIdentityProviderAuditRequest")
    public PerformIdentityProviderAuditResponse performIdentityProviderAudit(
        @WebParam(partName = "parameters", name = "PerformIdentityProviderAuditRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        PerformIdentityProviderAuditRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage;

    @WebResult(name = "GetAdminsResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/GetAdminsRequest")
    public GetAdminsResponse getAdmins(
        @WebParam(partName = "parameters", name = "GetAdminsRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        GetAdminsRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage;

    @WebResult(name = "ApproveHostCertificateResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/ApproveHostCertificateRequest")
    public ApproveHostCertificateResponse approveHostCertificate(
        @WebParam(partName = "parameters", name = "ApproveHostCertificateRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        ApproveHostCertificateRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage, InvalidHostCertificateFaultFaultMessage;

    @WebResult(name = "GetServiceSecurityMetadataResponse", targetNamespace = "http://security.introduce.cagrid.nci.nih.gov/ServiceSecurity", partName = "parameters")
    @WebMethod(action = "http://security.introduce.cagrid.nci.nih.gov/ServiceSecurity/GetServiceSecurityMetadataRequest")
    public org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse getServiceSecurityMetadata(
        @WebParam(partName = "parameters", name = "GetServiceSecurityMetadataRequest", targetNamespace = "http://security.introduce.cagrid.nci.nih.gov/ServiceSecurity")
        org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataRequest parameters
    );

    @WebResult(name = "QueryResourcePropertiesResponse", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", partName = "QueryResourcePropertiesResponse")
    @WebMethod(operationName = "QueryResourceProperties", action = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties/QueryResourceProperties")
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse queryResourceProperties(
        @WebParam(partName = "QueryResourcePropertiesRequest", name = "QueryResourceProperties", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd")
        org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties queryResourcePropertiesRequest
    ) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault;

    @WebResult(name = "UpdateAccountProfileResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/UpdateAccountProfileRequest")
    public UpdateAccountProfileResponse updateAccountProfile(
        @WebParam(partName = "parameters", name = "UpdateAccountProfileRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        UpdateAccountProfileRequest parameters
    ) throws DorianInternalFaultFaultMessage, PermissionDeniedFaultFaultMessage, NoSuchUserFaultFaultMessage, InvalidUserPropertyFaultFaultMessage;

    @WebResult(name = "RegisterLocalUserResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/RegisterLocalUserRequest")
    public RegisterLocalUserResponse registerLocalUser(
        @WebParam(partName = "parameters", name = "RegisterLocalUserRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        RegisterLocalUserRequest parameters
    ) throws DorianInternalFaultFaultMessage, InvalidUserPropertyFaultFaultMessage;

    @WebResult(name = "AddAdminResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/AddAdminRequest")
    public AddAdminResponse addAdmin(
        @WebParam(partName = "parameters", name = "AddAdminRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        AddAdminRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage;

    @WebResult(name = "RemoveAdminResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/RemoveAdminRequest")
    public RemoveAdminResponse removeAdmin(
        @WebParam(partName = "parameters", name = "RemoveAdminRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        RemoveAdminRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage;

    @WebResult(name = "CreateProxyResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/CreateProxyRequest")
    public CreateProxyResponse createProxy(
        @WebParam(partName = "parameters", name = "CreateProxyRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        CreateProxyRequest parameters
    ) throws DorianInternalFaultFaultMessage, PermissionDeniedFaultFaultMessage, InvalidAssertionFaultFaultMessage, UserPolicyFaultFaultMessage, InvalidProxyFaultFaultMessage;

    @WebResult(name = "UpdateHostCertificateRecordResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/UpdateHostCertificateRecordRequest")
    public UpdateHostCertificateRecordResponse updateHostCertificateRecord(
        @WebParam(partName = "parameters", name = "UpdateHostCertificateRecordRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        UpdateHostCertificateRecordRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage, InvalidHostCertificateFaultFaultMessage;

    @WebResult(name = "RemoveGridUserResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/RemoveGridUserRequest")
    public RemoveGridUserResponse removeGridUser(
        @WebParam(partName = "parameters", name = "RemoveGridUserRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        RemoveGridUserRequest parameters
    ) throws DorianInternalFaultFaultMessage, PermissionDeniedFaultFaultMessage, InvalidUserFaultFaultMessage;

    @WebResult(name = "ChangeIdPUserPasswordResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/ChangeIdPUserPasswordRequest")
    public ChangeIdPUserPasswordResponse changeIdPUserPassword(
        @WebParam(partName = "parameters", name = "ChangeIdPUserPasswordRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        ChangeIdPUserPasswordRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage, InvalidUserPropertyFaultFaultMessage;

    @WebResult(name = "UpdateUserCertificateResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/UpdateUserCertificateRequest")
    public UpdateUserCertificateResponse updateUserCertificate(
        @WebParam(partName = "parameters", name = "UpdateUserCertificateRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        UpdateUserCertificateRequest parameters
    ) throws DorianInternalFaultFaultMessage, InvalidUserCertificateFaultFaultMessage, PermissionDeniedFaultFaultMessage;

    @WebResult(name = "RenewHostCertificateResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/RenewHostCertificateRequest")
    public RenewHostCertificateResponse renewHostCertificate(
        @WebParam(partName = "parameters", name = "RenewHostCertificateRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        RenewHostCertificateRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage, InvalidHostCertificateFaultFaultMessage;

    @WebResult(name = "FindHostCertificatesResponse", targetNamespace = "http://cagrid.nci.nih.gov/Dorian", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/Dorian/FindHostCertificatesRequest")
    public FindHostCertificatesResponse findHostCertificates(
        @WebParam(partName = "parameters", name = "FindHostCertificatesRequest", targetNamespace = "http://cagrid.nci.nih.gov/Dorian")
        FindHostCertificatesRequest parameters
    ) throws PermissionDeniedFaultFaultMessage, DorianInternalFaultFaultMessage;
}

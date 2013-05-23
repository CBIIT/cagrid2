package org.cagrid.gridgrouper.wsrf.stubs;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.3
 * 2013-05-13T14:22:39.171-04:00
 * Generated source version: 2.6.3
 * 
 */
@WebService(targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", name = "GridGrouperPortType")
@XmlSeeAlso({gov.nih.nci.cagrid.metadata.ObjectFactory.class, org.cagrid.gridgrouper.types.ObjectFactory.class, gov.nih.nci.cagrid.metadata.security.ObjectFactory.class, ObjectFactory.class, gov.nih.nci.cagrid.metadata.common.ObjectFactory.class, org.cagrid.gaards.security.servicesecurity.ObjectFactory.class, org.cagrid.gridgrouper.model.ObjectFactory.class, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.ObjectFactory.class, org.xmlsoap.schemas.ws._2004._03.addressing.ObjectFactory.class, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_basefaults_1_2_draft_01.ObjectFactory.class, gov.nih.nci.cagrid.metadata.service.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface GridGrouperPortType {

    @WebResult(name = "GetMembershipsResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/GetMembershipsRequest")
    public GetMembershipsResponse getMemberships(
            @WebParam(partName = "parameters", name = "GetMembershipsRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            GetMembershipsRequest parameters
    ) throws GroupNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage;

    @WebResult(name = "GetSubjectsWithStemPrivilegeResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/GetSubjectsWithStemPrivilegeRequest")
    public GetSubjectsWithStemPrivilegeResponse getSubjectsWithStemPrivilege(
            @WebParam(partName = "parameters", name = "GetSubjectsWithStemPrivilegeRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            GetSubjectsWithStemPrivilegeRequest parameters
    ) throws GridGrouperRuntimeFaultFaultMessage, StemNotFoundFaultFaultMessage;

    @WebResult(name = "AddMemberResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/AddMemberRequest")
    public AddMemberResponse addMember(
            @WebParam(partName = "parameters", name = "AddMemberRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            AddMemberRequest parameters
    ) throws InsufficientPrivilegeFaultFaultMessage, GroupNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage, MemberAddFaultFaultMessage;

    @WebResult(name = "GetServiceSecurityMetadataResponse", targetNamespace = "http://security.introduce.cagrid.nci.nih.gov/ServiceSecurity", partName = "parameters")
    @WebMethod(action = "http://security.introduce.cagrid.nci.nih.gov/ServiceSecurity/GetServiceSecurityMetadataRequest")
    public org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse getServiceSecurityMetadata(
            @WebParam(partName = "parameters", name = "GetServiceSecurityMetadataRequest", targetNamespace = "http://security.introduce.cagrid.nci.nih.gov/ServiceSecurity")
            org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataRequest parameters
    );

    @WebResult(name = "IsMemberOfResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/IsMemberOfRequest")
    public IsMemberOfResponse isMemberOf(
            @WebParam(partName = "parameters", name = "IsMemberOfRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            IsMemberOfRequest parameters
    ) throws GroupNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage;

    @WebResult(name = "RevokeGroupPrivilegeResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/RevokeGroupPrivilegeRequest")
    public RevokeGroupPrivilegeResponse revokeGroupPrivilege(
            @WebParam(partName = "parameters", name = "RevokeGroupPrivilegeRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            RevokeGroupPrivilegeRequest parameters
    ) throws InsufficientPrivilegeFaultFaultMessage, GroupNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage, RevokePrivilegeFaultFaultMessage, SchemaFaultFaultMessage;

    @WebResult(name = "UpdateStemResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/UpdateStemRequest")
    public UpdateStemResponse updateStem(
            @WebParam(partName = "parameters", name = "UpdateStemRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            UpdateStemRequest parameters
    ) throws InsufficientPrivilegeFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage, StemModifyFaultFaultMessage;

    @WebResult(name = "GrantGroupPrivilegeResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/GrantGroupPrivilegeRequest")
    public GrantGroupPrivilegeResponse grantGroupPrivilege(
            @WebParam(partName = "parameters", name = "GrantGroupPrivilegeRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            GrantGroupPrivilegeRequest parameters
    ) throws GrantPrivilegeFaultFaultMessage, InsufficientPrivilegeFaultFaultMessage, GroupNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage;

    @WebResult(name = "GetMemberResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/GetMemberRequest")
    public GetMemberResponse getMember(
            @WebParam(partName = "parameters", name = "GetMemberRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            GetMemberRequest parameters
    ) throws InsufficientPrivilegeFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage;

    @WebResult(name = "HasStemPrivilegeResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/HasStemPrivilegeRequest")
    public HasStemPrivilegeResponse hasStemPrivilege(
            @WebParam(partName = "parameters", name = "HasStemPrivilegeRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            HasStemPrivilegeRequest parameters
    ) throws GridGrouperRuntimeFaultFaultMessage, StemNotFoundFaultFaultMessage;

    @WebResult(name = "GetMembershipRequestsResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/GetMembershipRequestsRequest")
    public GetMembershipRequestsResponse getMembershipRequests(
            @WebParam(partName = "parameters", name = "GetMembershipRequestsRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            GetMembershipRequestsRequest parameters
    );

    @WebResult(name = "IsMemberResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/IsMemberRequest")
    public IsMemberResponse isMember(
            @WebParam(partName = "parameters", name = "IsMemberRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            IsMemberRequest parameters
    ) throws GridGrouperRuntimeFaultFaultMessage;

    @WebResult(name = "GetChildStemsResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/GetChildStemsRequest")
    public GetChildStemsResponse getChildStems(
            @WebParam(partName = "parameters", name = "GetChildStemsRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            GetChildStemsRequest parameters
    ) throws GridGrouperRuntimeFaultFaultMessage, StemNotFoundFaultFaultMessage;

    @WebResult(name = "AddChildGroupResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/AddChildGroupRequest")
    public AddChildGroupResponse addChildGroup(
            @WebParam(partName = "parameters", name = "AddChildGroupRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            AddChildGroupRequest parameters
    ) throws GroupAddFaultFaultMessage, InsufficientPrivilegeFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage;

    @WebResult(name = "GetMembersResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/GetMembersRequest")
    public GetMembersResponse getMembers(
            @WebParam(partName = "parameters", name = "GetMembersRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            GetMembersRequest parameters
    ) throws GroupNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage;

    @WebResult(name = "GetStemResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/GetStemRequest")
    public GetStemResponse getStem(
            @WebParam(partName = "parameters", name = "GetStemRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            GetStemRequest parameters
    ) throws GridGrouperRuntimeFaultFaultMessage, StemNotFoundFaultFaultMessage;

    @WebResult(name = "UpdateGroupResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/UpdateGroupRequest")
    public UpdateGroupResponse updateGroup(
            @WebParam(partName = "parameters", name = "UpdateGroupRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            UpdateGroupRequest parameters
    ) throws GroupModifyFaultFaultMessage, InsufficientPrivilegeFaultFaultMessage, GroupNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage;

    @WebResult(name = "DeleteCompositeMemberResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/DeleteCompositeMemberRequest")
    public DeleteCompositeMemberResponse deleteCompositeMember(
            @WebParam(partName = "parameters", name = "DeleteCompositeMemberRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            DeleteCompositeMemberRequest parameters
    ) throws InsufficientPrivilegeFaultFaultMessage, GroupNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage, MemberDeleteFaultFaultMessage;

    @WebResult(name = "DeleteStemResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/DeleteStemRequest")
    public DeleteStemResponse deleteStem(
            @WebParam(partName = "parameters", name = "DeleteStemRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            DeleteStemRequest parameters
    ) throws InsufficientPrivilegeFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage, StemNotFoundFaultFaultMessage, StemDeleteFaultFaultMessage;

    @WebResult(name = "GetParentStemResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/GetParentStemRequest")
    public GetParentStemResponse getParentStem(
            @WebParam(partName = "parameters", name = "GetParentStemRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            GetParentStemRequest parameters
    ) throws GridGrouperRuntimeFaultFaultMessage, StemNotFoundFaultFaultMessage;

    @WebResult(name = "GetResourcePropertyResponse", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", partName = "GetResourcePropertyResponse")
    @WebMethod(operationName = "GetResourceProperty", action = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties/GetResourceProperty")
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse getResourceProperty(
            @WebParam(partName = "GetResourcePropertyRequest", name = "GetResourceProperty", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd")
            javax.xml.namespace.QName getResourcePropertyRequest
    ) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault;

    @WebResult(name = "GetStemPrivilegesResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/GetStemPrivilegesRequest")
    public GetStemPrivilegesResponse getStemPrivileges(
            @WebParam(partName = "parameters", name = "GetStemPrivilegesRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            GetStemPrivilegesRequest parameters
    ) throws GridGrouperRuntimeFaultFaultMessage, StemNotFoundFaultFaultMessage;

    @WebResult(name = "GetMembersGroupsResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/GetMembersGroupsRequest")
    public GetMembersGroupsResponse getMembersGroups(
            @WebParam(partName = "parameters", name = "GetMembersGroupsRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            GetMembersGroupsRequest parameters
    ) throws InsufficientPrivilegeFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage;

    @WebResult(name = "AddMembershipRequestResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/AddMembershipRequestRequest")
    public AddMembershipRequestResponse addMembershipRequest(
            @WebParam(partName = "parameters", name = "AddMembershipRequestRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            AddMembershipRequestRequest parameters
    );

    @WebResult(name = "GetSubjectsWithGroupPrivilegeResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/GetSubjectsWithGroupPrivilegeRequest")
    public GetSubjectsWithGroupPrivilegeResponse getSubjectsWithGroupPrivilege(
            @WebParam(partName = "parameters", name = "GetSubjectsWithGroupPrivilegeRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            GetSubjectsWithGroupPrivilegeRequest parameters
    ) throws GroupNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage;

    @WebResult(name = "GetGroupPrivilegesResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/GetGroupPrivilegesRequest")
    public GetGroupPrivilegesResponse getGroupPrivileges(
            @WebParam(partName = "parameters", name = "GetGroupPrivilegesRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            GetGroupPrivilegesRequest parameters
    ) throws GroupNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage;

    @WebResult(name = "QueryResourcePropertiesResponse", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", partName = "QueryResourcePropertiesResponse")
    @WebMethod(operationName = "QueryResourceProperties", action = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties/QueryResourceProperties")
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse queryResourceProperties(
            @WebParam(partName = "QueryResourcePropertiesRequest", name = "QueryResourceProperties", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd")
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties queryResourcePropertiesRequest
    ) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault;

    @WebResult(name = "DeleteMemberResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/DeleteMemberRequest")
    public DeleteMemberResponse deleteMember(
            @WebParam(partName = "parameters", name = "DeleteMemberRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            DeleteMemberRequest parameters
    ) throws InsufficientPrivilegeFaultFaultMessage, GroupNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage, MemberDeleteFaultFaultMessage;

    @WebResult(name = "GrantStemPrivilegeResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/GrantStemPrivilegeRequest")
    public GrantStemPrivilegeResponse grantStemPrivilege(
            @WebParam(partName = "parameters", name = "GrantStemPrivilegeRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            GrantStemPrivilegeRequest parameters
    ) throws GrantPrivilegeFaultFaultMessage, InsufficientPrivilegeFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage, StemNotFoundFaultFaultMessage, SchemaFaultFaultMessage;

    @WebResult(name = "DisableMembershipRequestsResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/DisableMembershipRequestsRequest")
    public DisableMembershipRequestsResponse disableMembershipRequests(
            @WebParam(partName = "parameters", name = "DisableMembershipRequestsRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            DisableMembershipRequestsRequest parameters
    );

    @WebResult(name = "EnableMembershipRequestsResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/EnableMembershipRequestsRequest")
    public EnableMembershipRequestsResponse enableMembershipRequests(
            @WebParam(partName = "parameters", name = "EnableMembershipRequestsRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            EnableMembershipRequestsRequest parameters
    );

    @WebResult(name = "DeleteGroupResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/DeleteGroupRequest")
    public DeleteGroupResponse deleteGroup(
            @WebParam(partName = "parameters", name = "DeleteGroupRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            DeleteGroupRequest parameters
    ) throws InsufficientPrivilegeFaultFaultMessage, GroupNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage, GroupDeleteFaultFaultMessage;

    @WebResult(name = "IsMembershipRequestEnabledResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/IsMembershipRequestEnabledRequest")
    public IsMembershipRequestEnabledResponse isMembershipRequestEnabled(
            @WebParam(partName = "parameters", name = "IsMembershipRequestEnabledRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            IsMembershipRequestEnabledRequest parameters
    );

    @WebResult(name = "RevokeStemPrivilegeResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/RevokeStemPrivilegeRequest")
    public RevokeStemPrivilegeResponse revokeStemPrivilege(
            @WebParam(partName = "parameters", name = "RevokeStemPrivilegeRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            RevokeStemPrivilegeRequest parameters
    ) throws InsufficientPrivilegeFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage, RevokePrivilegeFaultFaultMessage, StemNotFoundFaultFaultMessage, SchemaFaultFaultMessage;

    @WebResult(name = "GetMultipleResourcePropertiesResponse", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", partName = "GetMultipleResourcePropertiesResponse")
    @WebMethod(operationName = "GetMultipleResourceProperties", action = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties/GetMultipleResourceProperties")
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourcePropertiesResponse getMultipleResourceProperties(
            @WebParam(partName = "GetMultipleResourcePropertiesRequest", name = "GetMultipleResourceProperties", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd")
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourceProperties getMultipleResourcePropertiesRequest
    ) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault;

    @WebResult(name = "GetChildGroupsResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/GetChildGroupsRequest")
    public GetChildGroupsResponse getChildGroups(
            @WebParam(partName = "parameters", name = "GetChildGroupsRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            GetChildGroupsRequest parameters
    ) throws GridGrouperRuntimeFaultFaultMessage, StemNotFoundFaultFaultMessage;

    @WebResult(name = "UpdateMembershipRequestResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/UpdateMembershipRequestRequest")
    public UpdateMembershipRequestResponse updateMembershipRequest(
            @WebParam(partName = "parameters", name = "UpdateMembershipRequestRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            UpdateMembershipRequestRequest parameters
    );

    @WebResult(name = "AddChildStemResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/AddChildStemRequest")
    public AddChildStemResponse addChildStem(
            @WebParam(partName = "parameters", name = "AddChildStemRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            AddChildStemRequest parameters
    ) throws StemAddFaultFaultMessage, InsufficientPrivilegeFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage, StemNotFoundFaultFaultMessage;

    @WebResult(name = "GetGroupResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/GetGroupRequest")
    public GetGroupResponse getGroup(
            @WebParam(partName = "parameters", name = "GetGroupRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            GetGroupRequest parameters
    ) throws GroupNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage;

    @WebResult(name = "AddCompositeMemberResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/AddCompositeMemberRequest")
    public AddCompositeMemberResponse addCompositeMember(
            @WebParam(partName = "parameters", name = "AddCompositeMemberRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            AddCompositeMemberRequest parameters
    ) throws InsufficientPrivilegeFaultFaultMessage, GroupNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage, MemberAddFaultFaultMessage;

    @WebResult(name = "HasGroupPrivilegeResponse", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper", partName = "parameters")
    @WebMethod(action = "http://cagrid.nci.nih.gov/GridGrouper/HasGroupPrivilegeRequest")
    public HasGroupPrivilegeResponse hasGroupPrivilege(
            @WebParam(partName = "parameters", name = "HasGroupPrivilegeRequest", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper")
            HasGroupPrivilegeRequest parameters
    ) throws GroupNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage;
}

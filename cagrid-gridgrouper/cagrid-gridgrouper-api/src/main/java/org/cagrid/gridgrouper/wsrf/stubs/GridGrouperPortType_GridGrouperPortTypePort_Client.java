
package org.cagrid.gridgrouper.wsrf.stubs;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import org.cagrid.gridgrouper.wsrf.service.GridGrouperService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.3
 * 2013-05-13T14:22:38.962-04:00
 * Generated source version: 2.6.3
 * 
 */
public final class GridGrouperPortType_GridGrouperPortTypePort_Client {

    private static final QName SERVICE_NAME = new QName("http://cagrid.nci.nih.gov/GridGrouper/service", "GridGrouperService");

    private GridGrouperPortType_GridGrouperPortTypePort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = GridGrouperService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        GridGrouperService ss = new GridGrouperService(wsdlURL, SERVICE_NAME);
        GridGrouperPortType port = ss.getGridGrouperPortTypePort();  
        
        {
        System.out.println("Invoking getMemberships...");
        GetMembershipsRequest _getMemberships_parameters = null;
        try {
            GetMembershipsResponse _getMemberships__return = port.getMemberships(_getMemberships_parameters);
            System.out.println("getMemberships.result=" + _getMemberships__return);

        } catch (GroupNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getSubjectsWithStemPrivilege...");
        GetSubjectsWithStemPrivilegeRequest _getSubjectsWithStemPrivilege_parameters = null;
        try {
            GetSubjectsWithStemPrivilegeResponse _getSubjectsWithStemPrivilege__return = port.getSubjectsWithStemPrivilege(_getSubjectsWithStemPrivilege_parameters);
            System.out.println("getSubjectsWithStemPrivilege.result=" + _getSubjectsWithStemPrivilege__return);

        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (StemNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: StemNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking addMember...");
        AddMemberRequest _addMember_parameters = null;
        try {
            AddMemberResponse _addMember__return = port.addMember(_addMember_parameters);
            System.out.println("addMember.result=" + _addMember__return);

        } catch (InsufficientPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: InsufficientPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GroupNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (MemberAddFaultFaultMessage e) { 
            System.out.println("Expected exception: MemberAddFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getServiceSecurityMetadata...");
        org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataRequest _getServiceSecurityMetadata_parameters = null;
        org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse _getServiceSecurityMetadata__return = port.getServiceSecurityMetadata(_getServiceSecurityMetadata_parameters);
        System.out.println("getServiceSecurityMetadata.result=" + _getServiceSecurityMetadata__return);


        }
        {
        System.out.println("Invoking isMemberOf...");
        IsMemberOfRequest _isMemberOf_parameters = null;
        try {
            IsMemberOfResponse _isMemberOf__return = port.isMemberOf(_isMemberOf_parameters);
            System.out.println("isMemberOf.result=" + _isMemberOf__return);

        } catch (GroupNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking revokeGroupPrivilege...");
        RevokeGroupPrivilegeRequest _revokeGroupPrivilege_parameters = null;
        try {
            RevokeGroupPrivilegeResponse _revokeGroupPrivilege__return = port.revokeGroupPrivilege(_revokeGroupPrivilege_parameters);
            System.out.println("revokeGroupPrivilege.result=" + _revokeGroupPrivilege__return);

        } catch (InsufficientPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: InsufficientPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GroupNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (RevokePrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: RevokePrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (SchemaFaultFaultMessage e) { 
            System.out.println("Expected exception: SchemaFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking updateStem...");
        UpdateStemRequest _updateStem_parameters = null;
        try {
            UpdateStemResponse _updateStem__return = port.updateStem(_updateStem_parameters);
            System.out.println("updateStem.result=" + _updateStem__return);

        } catch (InsufficientPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: InsufficientPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (StemModifyFaultFaultMessage e) { 
            System.out.println("Expected exception: StemModifyFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking grantGroupPrivilege...");
        GrantGroupPrivilegeRequest _grantGroupPrivilege_parameters = null;
        try {
            GrantGroupPrivilegeResponse _grantGroupPrivilege__return = port.grantGroupPrivilege(_grantGroupPrivilege_parameters);
            System.out.println("grantGroupPrivilege.result=" + _grantGroupPrivilege__return);

        } catch (GrantPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: GrantPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (InsufficientPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: InsufficientPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GroupNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getMember...");
        GetMemberRequest _getMember_parameters = null;
        try {
            GetMemberResponse _getMember__return = port.getMember(_getMember_parameters);
            System.out.println("getMember.result=" + _getMember__return);

        } catch (InsufficientPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: InsufficientPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking hasStemPrivilege...");
        HasStemPrivilegeRequest _hasStemPrivilege_parameters = null;
        try {
            HasStemPrivilegeResponse _hasStemPrivilege__return = port.hasStemPrivilege(_hasStemPrivilege_parameters);
            System.out.println("hasStemPrivilege.result=" + _hasStemPrivilege__return);

        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (StemNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: StemNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getMembershipRequests...");
        GetMembershipRequestsRequest _getMembershipRequests_parameters = null;
        GetMembershipRequestsResponse _getMembershipRequests__return = port.getMembershipRequests(_getMembershipRequests_parameters);
        System.out.println("getMembershipRequests.result=" + _getMembershipRequests__return);


        }
        {
        System.out.println("Invoking isMember...");
        IsMemberRequest _isMember_parameters = null;
        try {
            IsMemberResponse _isMember__return = port.isMember(_isMember_parameters);
            System.out.println("isMember.result=" + _isMember__return);

        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getChildStems...");
        GetChildStemsRequest _getChildStems_parameters = null;
        try {
            GetChildStemsResponse _getChildStems__return = port.getChildStems(_getChildStems_parameters);
            System.out.println("getChildStems.result=" + _getChildStems__return);

        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (StemNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: StemNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking addChildGroup...");
        AddChildGroupRequest _addChildGroup_parameters = null;
        try {
            AddChildGroupResponse _addChildGroup__return = port.addChildGroup(_addChildGroup_parameters);
            System.out.println("addChildGroup.result=" + _addChildGroup__return);

        } catch (GroupAddFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupAddFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (InsufficientPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: InsufficientPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getMembers...");
        GetMembersRequest _getMembers_parameters = null;
        try {
            GetMembersResponse _getMembers__return = port.getMembers(_getMembers_parameters);
            System.out.println("getMembers.result=" + _getMembers__return);

        } catch (GroupNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getStem...");
        GetStemRequest _getStem_parameters = null;
        try {
            GetStemResponse _getStem__return = port.getStem(_getStem_parameters);
            System.out.println("getStem.result=" + _getStem__return);

        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (StemNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: StemNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking updateGroup...");
        UpdateGroupRequest _updateGroup_parameters = null;
        try {
            UpdateGroupResponse _updateGroup__return = port.updateGroup(_updateGroup_parameters);
            System.out.println("updateGroup.result=" + _updateGroup__return);

        } catch (GroupModifyFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupModifyFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (InsufficientPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: InsufficientPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GroupNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking deleteCompositeMember...");
        DeleteCompositeMemberRequest _deleteCompositeMember_parameters = null;
        try {
            DeleteCompositeMemberResponse _deleteCompositeMember__return = port.deleteCompositeMember(_deleteCompositeMember_parameters);
            System.out.println("deleteCompositeMember.result=" + _deleteCompositeMember__return);

        } catch (InsufficientPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: InsufficientPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GroupNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (MemberDeleteFaultFaultMessage e) { 
            System.out.println("Expected exception: MemberDeleteFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking deleteStem...");
        DeleteStemRequest _deleteStem_parameters = null;
        try {
            DeleteStemResponse _deleteStem__return = port.deleteStem(_deleteStem_parameters);
            System.out.println("deleteStem.result=" + _deleteStem__return);

        } catch (InsufficientPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: InsufficientPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (StemNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: StemNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (StemDeleteFaultFaultMessage e) { 
            System.out.println("Expected exception: StemDeleteFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getParentStem...");
        GetParentStemRequest _getParentStem_parameters = null;
        try {
            GetParentStemResponse _getParentStem__return = port.getParentStem(_getParentStem_parameters);
            System.out.println("getParentStem.result=" + _getParentStem__return);

        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (StemNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: StemNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getResourceProperty...");
        QName _getResourceProperty_getResourcePropertyRequest = new QName("", "");
        try {
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse _getResourceProperty__return = port.getResourceProperty(_getResourceProperty_getResourcePropertyRequest);
            System.out.println("getResourceProperty.result=" + _getResourceProperty__return);

        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault e) { 
            System.out.println("Expected exception: ResourceUnknownFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault e) { 
            System.out.println("Expected exception: InvalidResourcePropertyQNameFault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getStemPrivileges...");
        GetStemPrivilegesRequest _getStemPrivileges_parameters = null;
        try {
            GetStemPrivilegesResponse _getStemPrivileges__return = port.getStemPrivileges(_getStemPrivileges_parameters);
            System.out.println("getStemPrivileges.result=" + _getStemPrivileges__return);

        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (StemNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: StemNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getMembersGroups...");
        GetMembersGroupsRequest _getMembersGroups_parameters = null;
        try {
            GetMembersGroupsResponse _getMembersGroups__return = port.getMembersGroups(_getMembersGroups_parameters);
            System.out.println("getMembersGroups.result=" + _getMembersGroups__return);

        } catch (InsufficientPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: InsufficientPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking addMembershipRequest...");
        AddMembershipRequestRequest _addMembershipRequest_parameters = null;
        AddMembershipRequestResponse _addMembershipRequest__return = port.addMembershipRequest(_addMembershipRequest_parameters);
        System.out.println("addMembershipRequest.result=" + _addMembershipRequest__return);


        }
        {
        System.out.println("Invoking getSubjectsWithGroupPrivilege...");
        GetSubjectsWithGroupPrivilegeRequest _getSubjectsWithGroupPrivilege_parameters = null;
        try {
            GetSubjectsWithGroupPrivilegeResponse _getSubjectsWithGroupPrivilege__return = port.getSubjectsWithGroupPrivilege(_getSubjectsWithGroupPrivilege_parameters);
            System.out.println("getSubjectsWithGroupPrivilege.result=" + _getSubjectsWithGroupPrivilege__return);

        } catch (GroupNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getGroupPrivileges...");
        GetGroupPrivilegesRequest _getGroupPrivileges_parameters = null;
        try {
            GetGroupPrivilegesResponse _getGroupPrivileges__return = port.getGroupPrivileges(_getGroupPrivileges_parameters);
            System.out.println("getGroupPrivileges.result=" + _getGroupPrivileges__return);

        } catch (GroupNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking queryResourceProperties...");
        org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties _queryResourceProperties_queryResourcePropertiesRequest = null;
        try {
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse _queryResourceProperties__return = port.queryResourceProperties(_queryResourceProperties_queryResourcePropertiesRequest);
            System.out.println("queryResourceProperties.result=" + _queryResourceProperties__return);

        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault e) { 
            System.out.println("Expected exception: ResourceUnknownFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault e) { 
            System.out.println("Expected exception: InvalidQueryExpressionFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault e) { 
            System.out.println("Expected exception: QueryEvaluationErrorFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault e) { 
            System.out.println("Expected exception: InvalidResourcePropertyQNameFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault e) { 
            System.out.println("Expected exception: UnknownQueryExpressionDialectFault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking deleteMember...");
        DeleteMemberRequest _deleteMember_parameters = null;
        try {
            DeleteMemberResponse _deleteMember__return = port.deleteMember(_deleteMember_parameters);
            System.out.println("deleteMember.result=" + _deleteMember__return);

        } catch (InsufficientPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: InsufficientPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GroupNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (MemberDeleteFaultFaultMessage e) { 
            System.out.println("Expected exception: MemberDeleteFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking grantStemPrivilege...");
        GrantStemPrivilegeRequest _grantStemPrivilege_parameters = null;
        try {
            GrantStemPrivilegeResponse _grantStemPrivilege__return = port.grantStemPrivilege(_grantStemPrivilege_parameters);
            System.out.println("grantStemPrivilege.result=" + _grantStemPrivilege__return);

        } catch (GrantPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: GrantPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (InsufficientPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: InsufficientPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (StemNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: StemNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (SchemaFaultFaultMessage e) { 
            System.out.println("Expected exception: SchemaFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking disableMembershipRequests...");
        DisableMembershipRequestsRequest _disableMembershipRequests_parameters = null;
        DisableMembershipRequestsResponse _disableMembershipRequests__return = port.disableMembershipRequests(_disableMembershipRequests_parameters);
        System.out.println("disableMembershipRequests.result=" + _disableMembershipRequests__return);


        }
        {
        System.out.println("Invoking enableMembershipRequests...");
        EnableMembershipRequestsRequest _enableMembershipRequests_parameters = null;
        EnableMembershipRequestsResponse _enableMembershipRequests__return = port.enableMembershipRequests(_enableMembershipRequests_parameters);
        System.out.println("enableMembershipRequests.result=" + _enableMembershipRequests__return);


        }
        {
        System.out.println("Invoking deleteGroup...");
        DeleteGroupRequest _deleteGroup_parameters = null;
        try {
            DeleteGroupResponse _deleteGroup__return = port.deleteGroup(_deleteGroup_parameters);
            System.out.println("deleteGroup.result=" + _deleteGroup__return);

        } catch (InsufficientPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: InsufficientPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GroupNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GroupDeleteFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupDeleteFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking isMembershipRequestEnabled...");
        IsMembershipRequestEnabledRequest _isMembershipRequestEnabled_parameters = null;
        IsMembershipRequestEnabledResponse _isMembershipRequestEnabled__return = port.isMembershipRequestEnabled(_isMembershipRequestEnabled_parameters);
        System.out.println("isMembershipRequestEnabled.result=" + _isMembershipRequestEnabled__return);


        }
        {
        System.out.println("Invoking revokeStemPrivilege...");
        RevokeStemPrivilegeRequest _revokeStemPrivilege_parameters = null;
        try {
            RevokeStemPrivilegeResponse _revokeStemPrivilege__return = port.revokeStemPrivilege(_revokeStemPrivilege_parameters);
            System.out.println("revokeStemPrivilege.result=" + _revokeStemPrivilege__return);

        } catch (InsufficientPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: InsufficientPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (RevokePrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: RevokePrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (StemNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: StemNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (SchemaFaultFaultMessage e) { 
            System.out.println("Expected exception: SchemaFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getMultipleResourceProperties...");
        org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourceProperties _getMultipleResourceProperties_getMultipleResourcePropertiesRequest = null;
        try {
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourcePropertiesResponse _getMultipleResourceProperties__return = port.getMultipleResourceProperties(_getMultipleResourceProperties_getMultipleResourcePropertiesRequest);
            System.out.println("getMultipleResourceProperties.result=" + _getMultipleResourceProperties__return);

        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault e) { 
            System.out.println("Expected exception: ResourceUnknownFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault e) { 
            System.out.println("Expected exception: InvalidResourcePropertyQNameFault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getChildGroups...");
        GetChildGroupsRequest _getChildGroups_parameters = null;
        try {
            GetChildGroupsResponse _getChildGroups__return = port.getChildGroups(_getChildGroups_parameters);
            System.out.println("getChildGroups.result=" + _getChildGroups__return);

        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (StemNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: StemNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking updateMembershipRequest...");
        UpdateMembershipRequestRequest _updateMembershipRequest_parameters = null;
        UpdateMembershipRequestResponse _updateMembershipRequest__return = port.updateMembershipRequest(_updateMembershipRequest_parameters);
        System.out.println("updateMembershipRequest.result=" + _updateMembershipRequest__return);


        }
        {
        System.out.println("Invoking addChildStem...");
        AddChildStemRequest _addChildStem_parameters = null;
        try {
            AddChildStemResponse _addChildStem__return = port.addChildStem(_addChildStem_parameters);
            System.out.println("addChildStem.result=" + _addChildStem__return);

        } catch (StemAddFaultFaultMessage e) { 
            System.out.println("Expected exception: StemAddFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (InsufficientPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: InsufficientPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (StemNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: StemNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getGroup...");
        GetGroupRequest _getGroup_parameters = null;
        try {
            GetGroupResponse _getGroup__return = port.getGroup(_getGroup_parameters);
            System.out.println("getGroup.result=" + _getGroup__return);

        } catch (GroupNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking addCompositeMember...");
        AddCompositeMemberRequest _addCompositeMember_parameters = null;
        try {
            AddCompositeMemberResponse _addCompositeMember__return = port.addCompositeMember(_addCompositeMember_parameters);
            System.out.println("addCompositeMember.result=" + _addCompositeMember__return);

        } catch (InsufficientPrivilegeFaultFaultMessage e) { 
            System.out.println("Expected exception: InsufficientPrivilegeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GroupNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (MemberAddFaultFaultMessage e) { 
            System.out.println("Expected exception: MemberAddFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking hasGroupPrivilege...");
        HasGroupPrivilegeRequest _hasGroupPrivilege_parameters = null;
        try {
            HasGroupPrivilegeResponse _hasGroupPrivilege__return = port.hasGroupPrivilege(_hasGroupPrivilege_parameters);
            System.out.println("hasGroupPrivilege.result=" + _hasGroupPrivilege__return);

        } catch (GroupNotFoundFaultFaultMessage e) { 
            System.out.println("Expected exception: GroupNotFoundFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        } catch (GridGrouperRuntimeFaultFaultMessage e) { 
            System.out.println("Expected exception: GridGrouperRuntimeFaultFaultMessage has occurred.");
            System.out.println(e.toString());
        }
            }

        System.exit(0);
    }

}
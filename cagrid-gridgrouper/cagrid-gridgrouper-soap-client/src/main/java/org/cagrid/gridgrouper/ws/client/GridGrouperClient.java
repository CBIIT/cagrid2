package org.cagrid.gridgrouper.ws.client;

import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.core.soapclient.AbstractSoapClient;
import org.cagrid.gridgrouper.model.GroupDescriptor;
import org.cagrid.gridgrouper.model.GroupIdentifier;
import org.cagrid.gridgrouper.model.MemberFilter;
import org.cagrid.gridgrouper.service.exception.GridGrouperRuntimeException;
import org.cagrid.gridgrouper.service.exception.GroupNotFoundException;
import org.cagrid.gridgrouper.wsrf.service.GridGrouperService;
import org.cagrid.gridgrouper.wsrf.stubs.*;

import javax.xml.ws.BindingProvider;

/**
 * Created by langella on 2/18/14.
 */
public class GridGrouperClient extends AbstractSoapClient<GridGrouperService, GridGrouperPortType> implements org.cagrid.gridgrouper.client.GridGrouperClient{


    public GridGrouperClient(String url) {
        super(url, GridGrouperService.class, GridGrouperPortType.class);
    }

    public static void main(String[] args) {
        try {
            String url = "https://slavegts.training.cagrid.org:4443/gts";
            GridGrouperClient client = new GridGrouperClient(url);

            KeyStoreType ts = new KeyStoreType();
            ts.setFile("/Users/langella/Documents/caGrid/environments/keys/training-truststore.jks");
            ts.setPassword("changeit");

            client.setTruststore(ts);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isMemberOf(String subjectId, String groupName) throws GroupNotFoundException, GridGrouperRuntimeException {
        return isMemberOf(getGroupIdentifier(groupName), subjectId, MemberFilter.ALL);
    }

    public boolean isMemberOf(GroupIdentifier group, String member, MemberFilter filter) throws GroupNotFoundException, GridGrouperRuntimeException {
        IsMemberOfRequest params = new IsMemberOfRequest();
        IsMemberOfRequest.Group groupContainer = new IsMemberOfRequest.Group();
        groupContainer.setGroupIdentifier(group);
        params.setGroup(groupContainer);
        IsMemberOfRequest.Member memberContainer = new IsMemberOfRequest.Member();
        memberContainer.setSubjectIdentifier(member);
        params.setMember(memberContainer);
        IsMemberOfRequest.Filter filterContainer = new IsMemberOfRequest.Filter();
        filterContainer.setMemberFilter(filter);
        params.setFilter(filterContainer);
        IsMemberOfResponse boxedResult;
        try {
            boxedResult = getClient().isMemberOf(params);
        } catch (GroupNotFoundFaultFaultMessage e) {
            throw new GroupNotFoundException(e.getFaultInfo(), e.getMessage());
        } catch (GridGrouperRuntimeFaultFaultMessage e) {
            throw new GridGrouperRuntimeException(e.getFaultInfo(), e.getMessage());
        }
        return boxedResult.isResponse();
    }

    public GroupDescriptor getGroup(String name) throws GroupNotFoundException, GridGrouperRuntimeException {
        return getGroup(getGroupIdentifier(name));
    }

    public GroupDescriptor getGroup(GroupIdentifier group) throws GroupNotFoundException, GridGrouperRuntimeException {
        GetGroupRequest params = new GetGroupRequest();
        GetGroupRequest.Group groupContainer = new GetGroupRequest.Group();
        groupContainer.setGroupIdentifier(group);
        params.setGroup(groupContainer);
        GetGroupResponse boxedResult;
        try {
            boxedResult = getClient().getGroup(params);
        } catch (GroupNotFoundFaultFaultMessage e) {
            throw new GroupNotFoundException(e.getFaultInfo(), e.getMessage());
        } catch (GridGrouperRuntimeFaultFaultMessage e) {
            throw new GridGrouperRuntimeException(e.getFaultInfo(), e.getMessage());
        }
        return boxedResult.getGroupDescriptor();
    }

    private GroupIdentifier getGroupIdentifier(String groupName) {
        GroupIdentifier id = new GroupIdentifier();
        id.setGridGrouperURL(getName());
        id.setGroupName(groupName);
        return id;
    }

    private String getName() {
        BindingProvider bp = (BindingProvider) getClient();
        return (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
    }


    /*
    public gov.nih.nci.cagrid.gridgrouper.bean.StemDescriptor getStem(gov.nih.nci.cagrid.gridgrouper.bean.StemIdentifier stem) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.StemNotFoundFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getStem");
    gov.nih.nci.cagrid.gridgrouper.stubs.GetStemRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.GetStemRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.GetStemRequestStem stemContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetStemRequestStem();
    stemContainer.setStemIdentifier(stem);
    params.setStem(stemContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetStemResponse boxedResult = portType.getStem(params);
    return boxedResult.getStemDescriptor();
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.StemDescriptor[] getChildStems(gov.nih.nci.cagrid.gridgrouper.bean.StemIdentifier parentStem) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.StemNotFoundFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getChildStems");
    gov.nih.nci.cagrid.gridgrouper.stubs.GetChildStemsRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.GetChildStemsRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.GetChildStemsRequestParentStem parentStemContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetChildStemsRequestParentStem();
    parentStemContainer.setStemIdentifier(parentStem);
    params.setParentStem(parentStemContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetChildStemsResponse boxedResult = portType.getChildStems(params);
    return boxedResult.getStemDescriptor();
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.StemDescriptor getParentStem(gov.nih.nci.cagrid.gridgrouper.bean.StemIdentifier childStem) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.StemNotFoundFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getParentStem");
    gov.nih.nci.cagrid.gridgrouper.stubs.GetParentStemRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.GetParentStemRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.GetParentStemRequestChildStem childStemContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetParentStemRequestChildStem();
    childStemContainer.setStemIdentifier(childStem);
    params.setChildStem(childStemContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetParentStemResponse boxedResult = portType.getParentStem(params);
    return boxedResult.getStemDescriptor();
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.StemDescriptor updateStem(gov.nih.nci.cagrid.gridgrouper.bean.StemIdentifier stem,gov.nih.nci.cagrid.gridgrouper.bean.StemUpdate update) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.InsufficientPrivilegeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.StemModifyFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"updateStem");
    gov.nih.nci.cagrid.gridgrouper.stubs.UpdateStemRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.UpdateStemRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.UpdateStemRequestStem stemContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.UpdateStemRequestStem();
    stemContainer.setStemIdentifier(stem);
    params.setStem(stemContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.UpdateStemRequestUpdate updateContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.UpdateStemRequestUpdate();
    updateContainer.setStemUpdate(update);
    params.setUpdate(updateContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.UpdateStemResponse boxedResult = portType.updateStem(params);
    return boxedResult.getStemDescriptor();
    }
  }

  public java.lang.String[] getSubjectsWithStemPrivilege(gov.nih.nci.cagrid.gridgrouper.bean.StemIdentifier stem,gov.nih.nci.cagrid.gridgrouper.bean.StemPrivilegeType privilege) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.StemNotFoundFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getSubjectsWithStemPrivilege");
    gov.nih.nci.cagrid.gridgrouper.stubs.GetSubjectsWithStemPrivilegeRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.GetSubjectsWithStemPrivilegeRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.GetSubjectsWithStemPrivilegeRequestStem stemContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetSubjectsWithStemPrivilegeRequestStem();
    stemContainer.setStemIdentifier(stem);
    params.setStem(stemContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetSubjectsWithStemPrivilegeRequestPrivilege privilegeContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetSubjectsWithStemPrivilegeRequestPrivilege();
    privilegeContainer.setStemPrivilegeType(privilege);
    params.setPrivilege(privilegeContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetSubjectsWithStemPrivilegeResponse boxedResult = portType.getSubjectsWithStemPrivilege(params);
    return boxedResult.getSubjectIdentifier();
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.StemPrivilege[] getStemPrivileges(gov.nih.nci.cagrid.gridgrouper.bean.StemIdentifier stem,java.lang.String subject) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.StemNotFoundFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getStemPrivileges");
    gov.nih.nci.cagrid.gridgrouper.stubs.GetStemPrivilegesRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.GetStemPrivilegesRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.GetStemPrivilegesRequestStem stemContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetStemPrivilegesRequestStem();
    stemContainer.setStemIdentifier(stem);
    params.setStem(stemContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetStemPrivilegesRequestSubject subjectContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetStemPrivilegesRequestSubject();
    subjectContainer.setSubjectIdentifier(subject);
    params.setSubject(subjectContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetStemPrivilegesResponse boxedResult = portType.getStemPrivileges(params);
    return boxedResult.getStemPrivilege();
    }
  }

  public boolean hasStemPrivilege(gov.nih.nci.cagrid.gridgrouper.bean.StemIdentifier stem,java.lang.String subject,gov.nih.nci.cagrid.gridgrouper.bean.StemPrivilegeType privilege) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.StemNotFoundFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"hasStemPrivilege");
    gov.nih.nci.cagrid.gridgrouper.stubs.HasStemPrivilegeRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.HasStemPrivilegeRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.HasStemPrivilegeRequestStem stemContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.HasStemPrivilegeRequestStem();
    stemContainer.setStemIdentifier(stem);
    params.setStem(stemContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.HasStemPrivilegeRequestSubject subjectContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.HasStemPrivilegeRequestSubject();
    subjectContainer.setSubjectIdentifier(subject);
    params.setSubject(subjectContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.HasStemPrivilegeRequestPrivilege privilegeContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.HasStemPrivilegeRequestPrivilege();
    privilegeContainer.setStemPrivilegeType(privilege);
    params.setPrivilege(privilegeContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.HasStemPrivilegeResponse boxedResult = portType.hasStemPrivilege(params);
    return boxedResult.isResponse();
    }
  }

  public void grantStemPrivilege(gov.nih.nci.cagrid.gridgrouper.bean.StemIdentifier stem,java.lang.String subject,gov.nih.nci.cagrid.gridgrouper.bean.StemPrivilegeType privilege) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.StemNotFoundFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GrantPrivilegeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.InsufficientPrivilegeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.SchemaFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"grantStemPrivilege");
    gov.nih.nci.cagrid.gridgrouper.stubs.GrantStemPrivilegeRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.GrantStemPrivilegeRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.GrantStemPrivilegeRequestStem stemContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GrantStemPrivilegeRequestStem();
    stemContainer.setStemIdentifier(stem);
    params.setStem(stemContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GrantStemPrivilegeRequestSubject subjectContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GrantStemPrivilegeRequestSubject();
    subjectContainer.setSubjectIdentifier(subject);
    params.setSubject(subjectContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GrantStemPrivilegeRequestPrivilege privilegeContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GrantStemPrivilegeRequestPrivilege();
    privilegeContainer.setStemPrivilegeType(privilege);
    params.setPrivilege(privilegeContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GrantStemPrivilegeResponse boxedResult = portType.grantStemPrivilege(params);
    }
  }

  public void revokeStemPrivilege(gov.nih.nci.cagrid.gridgrouper.bean.StemIdentifier stem,java.lang.String subject,gov.nih.nci.cagrid.gridgrouper.bean.StemPrivilegeType privilege) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.StemNotFoundFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.InsufficientPrivilegeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.RevokePrivilegeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.SchemaFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"revokeStemPrivilege");
    gov.nih.nci.cagrid.gridgrouper.stubs.RevokeStemPrivilegeRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.RevokeStemPrivilegeRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.RevokeStemPrivilegeRequestStem stemContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.RevokeStemPrivilegeRequestStem();
    stemContainer.setStemIdentifier(stem);
    params.setStem(stemContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.RevokeStemPrivilegeRequestSubject subjectContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.RevokeStemPrivilegeRequestSubject();
    subjectContainer.setSubjectIdentifier(subject);
    params.setSubject(subjectContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.RevokeStemPrivilegeRequestPrivilege privilegeContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.RevokeStemPrivilegeRequestPrivilege();
    privilegeContainer.setStemPrivilegeType(privilege);
    params.setPrivilege(privilegeContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.RevokeStemPrivilegeResponse boxedResult = portType.revokeStemPrivilege(params);
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.StemDescriptor addChildStem(gov.nih.nci.cagrid.gridgrouper.bean.StemIdentifier stem,java.lang.String extension,java.lang.String displayExtension) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.InsufficientPrivilegeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.StemAddFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.StemNotFoundFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"addChildStem");
    gov.nih.nci.cagrid.gridgrouper.stubs.AddChildStemRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.AddChildStemRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.AddChildStemRequestStem stemContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.AddChildStemRequestStem();
    stemContainer.setStemIdentifier(stem);
    params.setStem(stemContainer);
    params.setExtension(extension);
    params.setDisplayExtension(displayExtension);
    gov.nih.nci.cagrid.gridgrouper.stubs.AddChildStemResponse boxedResult = portType.addChildStem(params);
    return boxedResult.getStemDescriptor();
    }
  }

  public void deleteStem(gov.nih.nci.cagrid.gridgrouper.bean.StemIdentifier stem) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.InsufficientPrivilegeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.StemDeleteFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.StemNotFoundFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"deleteStem");
    gov.nih.nci.cagrid.gridgrouper.stubs.DeleteStemRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.DeleteStemRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.DeleteStemRequestStem stemContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.DeleteStemRequestStem();
    stemContainer.setStemIdentifier(stem);
    params.setStem(stemContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.DeleteStemResponse boxedResult = portType.deleteStem(params);
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.GroupDescriptor[] getChildGroups(gov.nih.nci.cagrid.gridgrouper.bean.StemIdentifier stem) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.StemNotFoundFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getChildGroups");
    gov.nih.nci.cagrid.gridgrouper.stubs.GetChildGroupsRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.GetChildGroupsRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.GetChildGroupsRequestStem stemContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetChildGroupsRequestStem();
    stemContainer.setStemIdentifier(stem);
    params.setStem(stemContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetChildGroupsResponse boxedResult = portType.getChildGroups(params);
    return boxedResult.getGroupDescriptor();
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.GroupDescriptor addChildGroup(gov.nih.nci.cagrid.gridgrouper.bean.StemIdentifier stem,java.lang.String extension,java.lang.String displayExtension) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GroupAddFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.InsufficientPrivilegeFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"addChildGroup");
    gov.nih.nci.cagrid.gridgrouper.stubs.AddChildGroupRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.AddChildGroupRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.AddChildGroupRequestStem stemContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.AddChildGroupRequestStem();
    stemContainer.setStemIdentifier(stem);
    params.setStem(stemContainer);
    params.setExtension(extension);
    params.setDisplayExtension(displayExtension);
    gov.nih.nci.cagrid.gridgrouper.stubs.AddChildGroupResponse boxedResult = portType.addChildGroup(params);
    return boxedResult.getGroupDescriptor();
    }
  }

  public void deleteGroup(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GroupNotFoundFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GroupDeleteFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.InsufficientPrivilegeFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"deleteGroup");
    gov.nih.nci.cagrid.gridgrouper.stubs.DeleteGroupRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.DeleteGroupRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.DeleteGroupRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.DeleteGroupRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.DeleteGroupResponse boxedResult = portType.deleteGroup(params);
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.GroupDescriptor updateGroup(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group,gov.nih.nci.cagrid.gridgrouper.bean.GroupUpdate update) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GroupNotFoundFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GroupModifyFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.InsufficientPrivilegeFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"updateGroup");
    gov.nih.nci.cagrid.gridgrouper.stubs.UpdateGroupRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.UpdateGroupRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.UpdateGroupRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.UpdateGroupRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.UpdateGroupRequestUpdate updateContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.UpdateGroupRequestUpdate();
    updateContainer.setGroupUpdate(update);
    params.setUpdate(updateContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.UpdateGroupResponse boxedResult = portType.updateGroup(params);
    return boxedResult.getGroupDescriptor();
    }
  }

  public void addMember(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group,java.lang.String subject) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GroupNotFoundFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.InsufficientPrivilegeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.MemberAddFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"addMember");
    gov.nih.nci.cagrid.gridgrouper.stubs.AddMemberRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.AddMemberRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.AddMemberRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.AddMemberRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.AddMemberRequestSubject subjectContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.AddMemberRequestSubject();
    subjectContainer.setSubjectIdentifier(subject);
    params.setSubject(subjectContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.AddMemberResponse boxedResult = portType.addMember(params);
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.MemberDescriptor[] getMembers(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group,gov.nih.nci.cagrid.gridgrouper.bean.MemberFilter filter) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GroupNotFoundFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getMembers");
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMembersRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.GetMembersRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMembersRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetMembersRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMembersRequestFilter filterContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetMembersRequestFilter();
    filterContainer.setMemberFilter(filter);
    params.setFilter(filterContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMembersResponse boxedResult = portType.getMembers(params);
    return boxedResult.getMemberDescriptor();
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.MembershipDescriptor[] getMemberships(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group,gov.nih.nci.cagrid.gridgrouper.bean.MemberFilter filter) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GroupNotFoundFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getMemberships");
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMembershipsRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.GetMembershipsRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMembershipsRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetMembershipsRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMembershipsRequestFilter filterContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetMembershipsRequestFilter();
    filterContainer.setMemberFilter(filter);
    params.setFilter(filterContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMembershipsResponse boxedResult = portType.getMemberships(params);
    return boxedResult.getMembershipDescriptor();
    }
  }

  public void deleteMember(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group,java.lang.String member) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.InsufficientPrivilegeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GroupNotFoundFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.MemberDeleteFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"deleteMember");
    gov.nih.nci.cagrid.gridgrouper.stubs.DeleteMemberRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.DeleteMemberRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.DeleteMemberRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.DeleteMemberRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.DeleteMemberRequestMember memberContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.DeleteMemberRequestMember();
    memberContainer.setSubjectIdentifier(member);
    params.setMember(memberContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.DeleteMemberResponse boxedResult = portType.deleteMember(params);
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.GroupDescriptor addCompositeMember(gov.nih.nci.cagrid.gridgrouper.bean.GroupCompositeType type,gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier composite,gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier left,gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier right) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GroupNotFoundFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.MemberAddFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.InsufficientPrivilegeFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"addCompositeMember");
    gov.nih.nci.cagrid.gridgrouper.stubs.AddCompositeMemberRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.AddCompositeMemberRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.AddCompositeMemberRequestType typeContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.AddCompositeMemberRequestType();
    typeContainer.setGroupCompositeType(type);
    params.setType(typeContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.AddCompositeMemberRequestComposite compositeContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.AddCompositeMemberRequestComposite();
    compositeContainer.setGroupIdentifier(composite);
    params.setComposite(compositeContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.AddCompositeMemberRequestLeft leftContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.AddCompositeMemberRequestLeft();
    leftContainer.setGroupIdentifier(left);
    params.setLeft(leftContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.AddCompositeMemberRequestRight rightContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.AddCompositeMemberRequestRight();
    rightContainer.setGroupIdentifier(right);
    params.setRight(rightContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.AddCompositeMemberResponse boxedResult = portType.addCompositeMember(params);
    return boxedResult.getGroupDescriptor();
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.GroupDescriptor deleteCompositeMember(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GroupNotFoundFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.InsufficientPrivilegeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.MemberDeleteFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"deleteCompositeMember");
    gov.nih.nci.cagrid.gridgrouper.stubs.DeleteCompositeMemberRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.DeleteCompositeMemberRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.DeleteCompositeMemberRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.DeleteCompositeMemberRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.DeleteCompositeMemberResponse boxedResult = portType.deleteCompositeMember(params);
    return boxedResult.getGroupDescriptor();
    }
  }

  public void grantGroupPrivilege(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group,java.lang.String subject,gov.nih.nci.cagrid.gridgrouper.bean.GroupPrivilegeType privilege) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GroupNotFoundFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GrantPrivilegeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.InsufficientPrivilegeFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"grantGroupPrivilege");
    gov.nih.nci.cagrid.gridgrouper.stubs.GrantGroupPrivilegeRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.GrantGroupPrivilegeRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.GrantGroupPrivilegeRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GrantGroupPrivilegeRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GrantGroupPrivilegeRequestSubject subjectContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GrantGroupPrivilegeRequestSubject();
    subjectContainer.setSubjectIdentifier(subject);
    params.setSubject(subjectContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GrantGroupPrivilegeRequestPrivilege privilegeContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GrantGroupPrivilegeRequestPrivilege();
    privilegeContainer.setGroupPrivilegeType(privilege);
    params.setPrivilege(privilegeContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GrantGroupPrivilegeResponse boxedResult = portType.grantGroupPrivilege(params);
    }
  }

  public void revokeGroupPrivilege(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group,java.lang.String subject,gov.nih.nci.cagrid.gridgrouper.bean.GroupPrivilegeType privilege) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GroupNotFoundFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.RevokePrivilegeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.InsufficientPrivilegeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.SchemaFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"revokeGroupPrivilege");
    gov.nih.nci.cagrid.gridgrouper.stubs.RevokeGroupPrivilegeRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.RevokeGroupPrivilegeRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.RevokeGroupPrivilegeRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.RevokeGroupPrivilegeRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.RevokeGroupPrivilegeRequestSubject subjectContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.RevokeGroupPrivilegeRequestSubject();
    subjectContainer.setSubjectIdentifier(subject);
    params.setSubject(subjectContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.RevokeGroupPrivilegeRequestPrivilege privilegeContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.RevokeGroupPrivilegeRequestPrivilege();
    privilegeContainer.setGroupPrivilegeType(privilege);
    params.setPrivilege(privilegeContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.RevokeGroupPrivilegeResponse boxedResult = portType.revokeGroupPrivilege(params);
    }
  }

  public java.lang.String[] getSubjectsWithGroupPrivilege(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group,gov.nih.nci.cagrid.gridgrouper.bean.GroupPrivilegeType privilege) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GroupNotFoundFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getSubjectsWithGroupPrivilege");
    gov.nih.nci.cagrid.gridgrouper.stubs.GetSubjectsWithGroupPrivilegeRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.GetSubjectsWithGroupPrivilegeRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.GetSubjectsWithGroupPrivilegeRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetSubjectsWithGroupPrivilegeRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetSubjectsWithGroupPrivilegeRequestPrivilege privilegeContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetSubjectsWithGroupPrivilegeRequestPrivilege();
    privilegeContainer.setGroupPrivilegeType(privilege);
    params.setPrivilege(privilegeContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetSubjectsWithGroupPrivilegeResponse boxedResult = portType.getSubjectsWithGroupPrivilege(params);
    return boxedResult.getSubjectIdentifier();
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.GroupPrivilege[] getGroupPrivileges(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group,java.lang.String subject) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GroupNotFoundFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getGroupPrivileges");
    gov.nih.nci.cagrid.gridgrouper.stubs.GetGroupPrivilegesRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.GetGroupPrivilegesRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.GetGroupPrivilegesRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetGroupPrivilegesRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetGroupPrivilegesRequestSubject subjectContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetGroupPrivilegesRequestSubject();
    subjectContainer.setSubjectIdentifier(subject);
    params.setSubject(subjectContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetGroupPrivilegesResponse boxedResult = portType.getGroupPrivileges(params);
    return boxedResult.getGroupPrivilege();
    }
  }

  public boolean hasGroupPrivilege(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group,java.lang.String subject,gov.nih.nci.cagrid.gridgrouper.bean.GroupPrivilegeType privilege) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.GroupNotFoundFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"hasGroupPrivilege");
    gov.nih.nci.cagrid.gridgrouper.stubs.HasGroupPrivilegeRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.HasGroupPrivilegeRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.HasGroupPrivilegeRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.HasGroupPrivilegeRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.HasGroupPrivilegeRequestSubject subjectContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.HasGroupPrivilegeRequestSubject();
    subjectContainer.setSubjectIdentifier(subject);
    params.setSubject(subjectContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.HasGroupPrivilegeRequestPrivilege privilegeContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.HasGroupPrivilegeRequestPrivilege();
    privilegeContainer.setGroupPrivilegeType(privilege);
    params.setPrivilege(privilegeContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.HasGroupPrivilegeResponse boxedResult = portType.hasGroupPrivilege(params);
    return boxedResult.isResponse();
    }
  }

  public boolean isMember(java.lang.String member,gov.nih.nci.cagrid.gridgrouper.bean.MembershipExpression expression) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"isMember");
    gov.nih.nci.cagrid.gridgrouper.stubs.IsMemberRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.IsMemberRequest();
    params.setMember(member);
    gov.nih.nci.cagrid.gridgrouper.stubs.IsMemberRequestExpression expressionContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.IsMemberRequestExpression();
    expressionContainer.setMembershipExpression(expression);
    params.setExpression(expressionContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.IsMemberResponse boxedResult = portType.isMember(params);
    return boxedResult.isResponse();
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.MemberDescriptor getMember(java.lang.String member) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.InsufficientPrivilegeFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getMember");
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMemberRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.GetMemberRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMemberRequestMember memberContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetMemberRequestMember();
    memberContainer.setSubjectIdentifier(member);
    params.setMember(memberContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMemberResponse boxedResult = portType.getMember(params);
    return boxedResult.getMemberDescriptor();
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.GroupDescriptor[] getMembersGroups(java.lang.String member,gov.nih.nci.cagrid.gridgrouper.bean.MembershipType type) throws RemoteException, gov.nih.nci.cagrid.gridgrouper.stubs.types.GridGrouperRuntimeFault, gov.nih.nci.cagrid.gridgrouper.stubs.types.InsufficientPrivilegeFault {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getMembersGroups");
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMembersGroupsRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.GetMembersGroupsRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMembersGroupsRequestMember memberContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetMembersGroupsRequestMember();
    memberContainer.setSubjectIdentifier(member);
    params.setMember(memberContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMembersGroupsRequestType typeContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetMembersGroupsRequestType();
    typeContainer.setMembershipType(type);
    params.setType(typeContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMembersGroupsResponse boxedResult = portType.getMembersGroups(params);
    return boxedResult.getGroupDescriptor();
    }
  }

  public gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata getServiceSecurityMetadata() throws RemoteException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getServiceSecurityMetadata");
    gov.nih.nci.cagrid.introduce.security.stubs.GetServiceSecurityMetadataRequest params = new gov.nih.nci.cagrid.introduce.security.stubs.GetServiceSecurityMetadataRequest();
    gov.nih.nci.cagrid.introduce.security.stubs.GetServiceSecurityMetadataResponse boxedResult = portType.getServiceSecurityMetadata(params);
    return boxedResult.getServiceSecurityMetadata();
    }
  }

  public org.oasis.wsrf.properties.GetMultipleResourcePropertiesResponse getMultipleResourceProperties(org.oasis.wsrf.properties.GetMultipleResourceProperties_Element params) throws RemoteException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getMultipleResourceProperties");
    return portType.getMultipleResourceProperties(params);
    }
  }

  public org.oasis.wsrf.properties.GetResourcePropertyResponse getResourceProperty(javax.xml.namespace.QName params) throws RemoteException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getResourceProperty");
    return portType.getResourceProperty(params);
    }
  }

  public org.oasis.wsrf.properties.QueryResourcePropertiesResponse queryResourceProperties(org.oasis.wsrf.properties.QueryResourceProperties_Element params) throws RemoteException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"queryResourceProperties");
    return portType.queryResourceProperties(params);
    }
  }

  public void addMembershipRequest(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group) throws RemoteException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"addMembershipRequest");
    gov.nih.nci.cagrid.gridgrouper.stubs.AddMembershipRequestRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.AddMembershipRequestRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.AddMembershipRequestRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.AddMembershipRequestRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.AddMembershipRequestResponse boxedResult = portType.addMembershipRequest(params);
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.MembershipRequestDescriptor updateMembershipRequest(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group,java.lang.String subject,gov.nih.nci.cagrid.gridgrouper.bean.MembershipRequestUpdate update) throws RemoteException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"updateMembershipRequest");
    gov.nih.nci.cagrid.gridgrouper.stubs.UpdateMembershipRequestRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.UpdateMembershipRequestRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.UpdateMembershipRequestRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.UpdateMembershipRequestRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.UpdateMembershipRequestRequestSubject subjectContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.UpdateMembershipRequestRequestSubject();
    subjectContainer.setSubjectIdentifier(subject);
    params.setSubject(subjectContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.UpdateMembershipRequestRequestUpdate updateContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.UpdateMembershipRequestRequestUpdate();
    updateContainer.setMembershipRequestUpdate(update);
    params.setUpdate(updateContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.UpdateMembershipRequestResponse boxedResult = portType.updateMembershipRequest(params);
    return boxedResult.getMembershipRequestDescriptor();
    }
  }

  public gov.nih.nci.cagrid.gridgrouper.bean.MembershipRequestDescriptor[] getMembershipRequests(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group,gov.nih.nci.cagrid.gridgrouper.bean.MembershipRequestStatus status) throws RemoteException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getMembershipRequests");
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMembershipRequestsRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.GetMembershipRequestsRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMembershipRequestsRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetMembershipRequestsRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMembershipRequestsRequestStatus statusContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.GetMembershipRequestsRequestStatus();
    statusContainer.setMembershipRequestStatus(status);
    params.setStatus(statusContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.GetMembershipRequestsResponse boxedResult = portType.getMembershipRequests(params);
    return boxedResult.getMembershipRequestDescriptor();
    }
  }

  public void enableMembershipRequests(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group) throws RemoteException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"enableMembershipRequests");
    gov.nih.nci.cagrid.gridgrouper.stubs.EnableMembershipRequestsRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.EnableMembershipRequestsRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.EnableMembershipRequestsRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.EnableMembershipRequestsRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.EnableMembershipRequestsResponse boxedResult = portType.enableMembershipRequests(params);
    }
  }

  public void disableMembershipRequests(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group) throws RemoteException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"disableMembershipRequests");
    gov.nih.nci.cagrid.gridgrouper.stubs.DisableMembershipRequestsRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.DisableMembershipRequestsRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.DisableMembershipRequestsRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.DisableMembershipRequestsRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.DisableMembershipRequestsResponse boxedResult = portType.disableMembershipRequests(params);
    }
  }

  public boolean isMembershipRequestEnabled(gov.nih.nci.cagrid.gridgrouper.bean.GroupIdentifier group) throws RemoteException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"isMembershipRequestEnabled");
    gov.nih.nci.cagrid.gridgrouper.stubs.IsMembershipRequestEnabledRequest params = new gov.nih.nci.cagrid.gridgrouper.stubs.IsMembershipRequestEnabledRequest();
    gov.nih.nci.cagrid.gridgrouper.stubs.IsMembershipRequestEnabledRequestGroup groupContainer = new gov.nih.nci.cagrid.gridgrouper.stubs.IsMembershipRequestEnabledRequestGroup();
    groupContainer.setGroupIdentifier(group);
    params.setGroup(groupContainer);
    gov.nih.nci.cagrid.gridgrouper.stubs.IsMembershipRequestEnabledResponse boxedResult = portType.isMembershipRequestEnabled(params);
    return boxedResult.isResponse();
    }
  }
     */


}

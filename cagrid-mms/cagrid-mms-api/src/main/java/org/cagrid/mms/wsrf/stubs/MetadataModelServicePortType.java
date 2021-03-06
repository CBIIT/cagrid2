package org.cagrid.mms.wsrf.stubs;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.3
 * 2014-04-29T20:21:17.309-05:00
 * Generated source version: 2.6.3
 * 
 */
@WebService(targetNamespace = "http://mms.cagrid.org/MetadataModelService", name = "MetadataModelServicePortType")
@XmlSeeAlso({gov.nih.nci.cagrid.metadata.ObjectFactory.class, org.cagrid.mms.types.ObjectFactory.class, gov.nih.nci.cagrid.metadata.security.ObjectFactory.class, ObjectFactory.class, gov.nih.nci.cagrid.metadata.common.ObjectFactory.class, org.cagrid.gaards.security.servicesecurity.ObjectFactory.class, gov.nih.nci.cagrid.metadata.dataservice.ObjectFactory.class, org.xmlsoap.schemas.ws._2004._03.addressing.ObjectFactory.class, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.ObjectFactory.class, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_basefaults_1_2_draft_01.ObjectFactory.class, org.cagrid.mms.model.ObjectFactory.class, gov.nih.nci.cagrid.metadata.service.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface MetadataModelServicePortType {

    @WebResult(name = "QueryResourcePropertiesResponse", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", partName = "QueryResourcePropertiesResponse")
    @WebMethod(operationName = "QueryResourceProperties", action = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties/QueryResourceProperties")
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse queryResourceProperties(
        @WebParam(partName = "QueryResourcePropertiesRequest", name = "QueryResourceProperties", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd")
        org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties queryResourcePropertiesRequest
    ) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault;

    @WebResult(name = "GetResourcePropertyResponse", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", partName = "GetResourcePropertyResponse")
    @WebMethod(operationName = "GetResourceProperty", action = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties/GetResourceProperty")
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse getResourceProperty(
        @WebParam(partName = "GetResourcePropertyRequest", name = "GetResourceProperty", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd")
        javax.xml.namespace.QName getResourcePropertyRequest
    ) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault;

    @WebResult(name = "GenerateDomainModelForClassesWithExcludesResponse", targetNamespace = "http://mms.cagrid.org/MetadataModelService", partName = "parameters")
    @WebMethod(action = "http://mms.cagrid.org/MetadataModelService/GenerateDomainModelForClassesWithExcludesRequest")
    public GenerateDomainModelForClassesWithExcludesResponse generateDomainModelForClassesWithExcludes(
        @WebParam(partName = "parameters", name = "GenerateDomainModelForClassesWithExcludesRequest", targetNamespace = "http://mms.cagrid.org/MetadataModelService")
        GenerateDomainModelForClassesWithExcludesRequest parameters
    ) throws InvalidUMLProjectIndentifierFaultMessage;

    @WebResult(name = "AnnotateServiceMetadataResponse", targetNamespace = "http://mms.cagrid.org/MetadataModelService", partName = "parameters")
    @WebMethod(action = "http://mms.cagrid.org/MetadataModelService/AnnotateServiceMetadataRequest")
    public AnnotateServiceMetadataResponse annotateServiceMetadata(
        @WebParam(partName = "parameters", name = "AnnotateServiceMetadataRequest", targetNamespace = "http://mms.cagrid.org/MetadataModelService")
        AnnotateServiceMetadataRequest parameters
    ) throws InvalidUMLProjectIndentifierFaultMessage;

    @WebResult(name = "GenerateDomainModelForProjectResponse", targetNamespace = "http://mms.cagrid.org/MetadataModelService", partName = "parameters")
    @WebMethod(action = "http://mms.cagrid.org/MetadataModelService/GenerateDomainModelForProjectRequest")
    public GenerateDomainModelForProjectResponse generateDomainModelForProject(
        @WebParam(partName = "parameters", name = "GenerateDomainModelForProjectRequest", targetNamespace = "http://mms.cagrid.org/MetadataModelService")
        GenerateDomainModelForProjectRequest parameters
    ) throws InvalidUMLProjectIndentifierFaultMessage;

    @WebResult(name = "GetModelSourceMetadataResponse", targetNamespace = "http://mms.cagrid.org/MetadataModelService", partName = "parameters")
    @WebMethod(action = "http://mms.cagrid.org/MetadataModelService/GetModelSourceMetadataRequest")
    public GetModelSourceMetadataResponse getModelSourceMetadata(
        @WebParam(partName = "parameters", name = "GetModelSourceMetadataRequest", targetNamespace = "http://mms.cagrid.org/MetadataModelService")
        GetModelSourceMetadataRequest parameters
    );

    @WebResult(name = "GenerateDomainModelForClassesResponse", targetNamespace = "http://mms.cagrid.org/MetadataModelService", partName = "parameters")
    @WebMethod(action = "http://mms.cagrid.org/MetadataModelService/GenerateDomainModelForClassesRequest")
    public GenerateDomainModelForClassesResponse generateDomainModelForClasses(
        @WebParam(partName = "parameters", name = "GenerateDomainModelForClassesRequest", targetNamespace = "http://mms.cagrid.org/MetadataModelService")
        GenerateDomainModelForClassesRequest parameters
    ) throws InvalidUMLProjectIndentifierFaultMessage;

    @WebResult(name = "GenerateDomainModelForPackagesResponse", targetNamespace = "http://mms.cagrid.org/MetadataModelService", partName = "parameters")
    @WebMethod(action = "http://mms.cagrid.org/MetadataModelService/GenerateDomainModelForPackagesRequest")
    public GenerateDomainModelForPackagesResponse generateDomainModelForPackages(
        @WebParam(partName = "parameters", name = "GenerateDomainModelForPackagesRequest", targetNamespace = "http://mms.cagrid.org/MetadataModelService")
        GenerateDomainModelForPackagesRequest parameters
    ) throws InvalidUMLProjectIndentifierFaultMessage;

    @WebResult(name = "GetServiceSecurityMetadataResponse", targetNamespace = "http://security.introduce.cagrid.nci.nih.gov/ServiceSecurity", partName = "parameters")
    @WebMethod(action = "http://security.introduce.cagrid.nci.nih.gov/ServiceSecurity/GetServiceSecurityMetadataRequest")
    public org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse getServiceSecurityMetadata(
        @WebParam(partName = "parameters", name = "GetServiceSecurityMetadataRequest", targetNamespace = "http://security.introduce.cagrid.nci.nih.gov/ServiceSecurity")
        org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataRequest parameters
    );

    @WebResult(name = "GetMultipleResourcePropertiesResponse", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", partName = "GetMultipleResourcePropertiesResponse")
    @WebMethod(operationName = "GetMultipleResourceProperties", action = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties/GetMultipleResourceProperties")
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourcePropertiesResponse getMultipleResourceProperties(
        @WebParam(partName = "GetMultipleResourcePropertiesRequest", name = "GetMultipleResourceProperties", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd")
        org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourceProperties getMultipleResourcePropertiesRequest
    ) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault;
}

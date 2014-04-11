
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package org.cagrid.index.wsrf.stubs;

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
 * 2014-04-10T21:59:24.838-04:00
 * Generated source version: 2.6.3
 * 
 */

@javax.jws.WebService(
                      serviceName = "BigIndexService",
                      portName = "BigIndexPortTypePort",
                      targetNamespace = "http://mds.globus.org/bigindex/2008/11/24/service",
                      wsdlLocation = "/schema/org/cagrid/index/big_index_service.wsdl",
                      endpointInterface = "org.cagrid.index.wsrf.stubs.BigIndexPortType")
                      
public class BigIndexPortTypeImpl implements BigIndexPortType {

    private static final Logger LOG = Logger.getLogger(BigIndexPortTypeImpl.class.getName());

    /* (non-Javadoc)
     * @see org.cagrid.index.wsrf.stubs.BigIndexPortType#getMultipleResourceProperties(org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourceProperties  getMultipleResourcePropertiesRequest )*
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
     * @see org.cagrid.index.wsrf.stubs.BigIndexPortType#getContent(org.cagrid.index.types.BigIndexContentIDList  getContentRequest )*
     */
    public org.cagrid.index.wsrf.stubs.GetContentResponse getContent(org.cagrid.index.types.BigIndexContentIDList getContentRequest) { 
        LOG.info("Executing operation getContent");
        System.out.println(getContentRequest);
        try {
            org.cagrid.index.wsrf.stubs.GetContentResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see org.cagrid.index.wsrf.stubs.BigIndexPortType#subscribe(org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType  consumerReference ,)org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01.TopicExpressionType  topicExpression ,)java.lang.Boolean  useNotify ,)org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryExpressionType  precondition ,)org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryExpressionType  selector ,)java.lang.Object  subscriptionPolicy ,)java.util.Calendar  initialTerminationTime )*
     */
    public org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType subscribe(org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType consumerReference,org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01.TopicExpressionType topicExpression,java.lang.Boolean useNotify,org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryExpressionType precondition,org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryExpressionType selector,java.lang.Object subscriptionPolicy,java.util.Calendar initialTerminationTime) throws org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.SubscribeCreationFailedFault , org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.ResourceUnknownFault , org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.TopicPathDialectUnknownFault , org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.InvalidTopicExpressionFault , org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.TopicNotSupportedFault    { 
        LOG.info("Executing operation subscribe");
        System.out.println(consumerReference);
        System.out.println(topicExpression);
        System.out.println(useNotify);
        System.out.println(precondition);
        System.out.println(selector);
        System.out.println(subscriptionPolicy);
        System.out.println(initialTerminationTime);
        try {
            org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.SubscribeCreationFailedFault("SubscribeCreationFailedFault...");
        //throw new org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.ResourceUnknownFault("ResourceUnknownFault...");
        //throw new org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.TopicPathDialectUnknownFault("TopicPathDialectUnknownFault...");
        //throw new org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.InvalidTopicExpressionFault("InvalidTopicExpressionFault...");
        //throw new org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.TopicNotSupportedFault("TopicNotSupportedFault...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.index.wsrf.stubs.BigIndexPortType#getCurrentMessage(org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01.GetCurrentMessage  getCurrentMessageRequest )*
     */
    public org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01.GetCurrentMessageResponse getCurrentMessage(org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01.GetCurrentMessage getCurrentMessageRequest) throws org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.ResourceUnknownFault , org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.InvalidTopicExpressionFault , org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.NoCurrentMessageOnTopicFault , org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.TopicNotSupportedFault    { 
        LOG.info("Executing operation getCurrentMessage");
        System.out.println(getCurrentMessageRequest);
        try {
            org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01.GetCurrentMessageResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.ResourceUnknownFault("ResourceUnknownFault...");
        //throw new org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.InvalidTopicExpressionFault("InvalidTopicExpressionFault...");
        //throw new org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.NoCurrentMessageOnTopicFault("NoCurrentMessageOnTopicFault...");
        //throw new org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.TopicNotSupportedFault("TopicNotSupportedFault...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.index.wsrf.stubs.BigIndexPortType#setTerminationTime(java.util.Calendar  requestedTerminationTime ,)java.util.Calendar  newTerminationTime ,)java.util.Calendar  currentTime )*
     */
    public void setTerminationTime(java.util.Calendar requestedTerminationTime,javax.xml.ws.Holder<java.util.Calendar> newTerminationTime,javax.xml.ws.Holder<java.util.Calendar> currentTime) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.TerminationTimeChangeRejectedFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.UnableToSetTerminationTimeFault    { 
        LOG.info("Executing operation setTerminationTime");
        System.out.println(requestedTerminationTime);
        try {
            java.util.Calendar newTerminationTimeValue = null;
            newTerminationTime.value = newTerminationTimeValue;
            java.util.Calendar currentTimeValue = null;
            currentTime.value = currentTimeValue;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.TerminationTimeChangeRejectedFault("TerminationTimeChangeRejectedFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault("ResourceUnknownFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.UnableToSetTerminationTimeFault("UnableToSetTerminationTimeFault...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.index.wsrf.stubs.BigIndexPortType#destroy(*
     */
    public void destroy() throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceNotDestroyedFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault    { 
        LOG.info("Executing operation destroy");
        try {
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceNotDestroyedFault("ResourceNotDestroyedFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault("ResourceUnknownFault...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.index.wsrf.stubs.BigIndexPortType#add(org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.Add  addRequest )*
     */
    public org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType add(org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.Add addRequest) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.ContentCreationFailedFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.UnsupportedMemberInterfaceFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.AddRefusedFault    { 
        LOG.info("Executing operation add");
        System.out.println(addRequest);
        try {
            org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.ContentCreationFailedFault("ContentCreationFailedFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.UnsupportedMemberInterfaceFault("UnsupportedMemberInterfaceFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.AddRefusedFault("AddRefusedFault...");
    }

    /* (non-Javadoc)
     * @see org.cagrid.index.wsrf.stubs.BigIndexPortType#query(org.cagrid.index.wsrf.stubs.QueryType  queryRequest )*
     */
    public org.cagrid.index.wsrf.stubs.QueryResponse query(QueryType queryRequest) { 
        LOG.info("Executing operation query");
        System.out.println(queryRequest);
        try {
            org.cagrid.index.wsrf.stubs.QueryResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see org.cagrid.index.wsrf.stubs.BigIndexPortType#getResourceProperty(javax.xml.namespace.QName  getResourcePropertyRequest )*
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
     * @see org.cagrid.index.wsrf.stubs.BigIndexPortType#queryResourceProperties(org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties  queryResourcePropertiesRequest )*
     */
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse queryResourceProperties(org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties queryResourcePropertiesRequest) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault    { 
        LOG.info("Executing operation queryResourceProperties");
        System.out.println(queryResourcePropertiesRequest);
        try {
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault("UnknownQueryExpressionDialectFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault("InvalidQueryExpressionFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault("QueryEvaluationErrorFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault("ResourceUnknownFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault("InvalidResourcePropertyQNameFault...");
    }

}

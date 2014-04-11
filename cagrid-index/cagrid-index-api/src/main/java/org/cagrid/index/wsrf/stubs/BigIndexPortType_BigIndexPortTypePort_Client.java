
package org.cagrid.index.wsrf.stubs;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import org.cagrid.index.wsrf.service.BigIndexService;
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
 * 2014-04-10T21:59:24.668-04:00
 * Generated source version: 2.6.3
 * 
 */
public final class BigIndexPortType_BigIndexPortTypePort_Client {

    private static final QName SERVICE_NAME = new QName("http://mds.globus.org/bigindex/2008/11/24/service", "BigIndexService");

    private BigIndexPortType_BigIndexPortTypePort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = BigIndexService.WSDL_LOCATION;
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
      
        BigIndexService ss = new BigIndexService(wsdlURL, SERVICE_NAME);
        BigIndexPortType port = ss.getBigIndexPortTypePort();  
        
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
        System.out.println("Invoking getContent...");
        org.cagrid.index.types.BigIndexContentIDList _getContent_getContentRequest = null;
        org.cagrid.index.wsrf.stubs.GetContentResponse _getContent__return = port.getContent(_getContent_getContentRequest);
        System.out.println("getContent.result=" + _getContent__return);


        }
        {
        System.out.println("Invoking subscribe...");
        org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType _subscribe_consumerReference = null;
        org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01.TopicExpressionType _subscribe_topicExpression = null;
        java.lang.Boolean _subscribe_useNotify = null;
        org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryExpressionType _subscribe_precondition = null;
        org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryExpressionType _subscribe_selector = null;
        java.lang.Object _subscribe_subscriptionPolicy = new java.lang.Object();
        java.util.Calendar _subscribe_initialTerminationTime = null;
        try {
            org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType _subscribe__return = port.subscribe(_subscribe_consumerReference, _subscribe_topicExpression, _subscribe_useNotify, _subscribe_precondition, _subscribe_selector, _subscribe_subscriptionPolicy, _subscribe_initialTerminationTime);
            System.out.println("subscribe.result=" + _subscribe__return);

        } catch (org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.SubscribeCreationFailedFault e) { 
            System.out.println("Expected exception: SubscribeCreationFailedFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.ResourceUnknownFault e) { 
            System.out.println("Expected exception: ResourceUnknownFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.TopicPathDialectUnknownFault e) { 
            System.out.println("Expected exception: TopicPathDialectUnknownFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.InvalidTopicExpressionFault e) { 
            System.out.println("Expected exception: InvalidTopicExpressionFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.TopicNotSupportedFault e) { 
            System.out.println("Expected exception: TopicNotSupportedFault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getCurrentMessage...");
        org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01.GetCurrentMessage _getCurrentMessage_getCurrentMessageRequest = null;
        try {
            org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01.GetCurrentMessageResponse _getCurrentMessage__return = port.getCurrentMessage(_getCurrentMessage_getCurrentMessageRequest);
            System.out.println("getCurrentMessage.result=" + _getCurrentMessage__return);

        } catch (org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.ResourceUnknownFault e) { 
            System.out.println("Expected exception: ResourceUnknownFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.InvalidTopicExpressionFault e) { 
            System.out.println("Expected exception: InvalidTopicExpressionFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.NoCurrentMessageOnTopicFault e) { 
            System.out.println("Expected exception: NoCurrentMessageOnTopicFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01_wsdl.TopicNotSupportedFault e) { 
            System.out.println("Expected exception: TopicNotSupportedFault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking setTerminationTime...");
        java.util.Calendar _setTerminationTime_requestedTerminationTime = null;
        javax.xml.ws.Holder<java.util.Calendar> _setTerminationTime_newTerminationTime = new javax.xml.ws.Holder<java.util.Calendar>();
        javax.xml.ws.Holder<java.util.Calendar> _setTerminationTime_currentTime = new javax.xml.ws.Holder<java.util.Calendar>();
        try {
            port.setTerminationTime(_setTerminationTime_requestedTerminationTime, _setTerminationTime_newTerminationTime, _setTerminationTime_currentTime);

            System.out.println("setTerminationTime._setTerminationTime_newTerminationTime=" + _setTerminationTime_newTerminationTime.value);
            System.out.println("setTerminationTime._setTerminationTime_currentTime=" + _setTerminationTime_currentTime.value);
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.TerminationTimeChangeRejectedFault e) { 
            System.out.println("Expected exception: TerminationTimeChangeRejectedFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault e) { 
            System.out.println("Expected exception: ResourceUnknownFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.UnableToSetTerminationTimeFault e) { 
            System.out.println("Expected exception: UnableToSetTerminationTimeFault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking destroy...");
        try {
            port.destroy();

        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceNotDestroyedFault e) { 
            System.out.println("Expected exception: ResourceNotDestroyedFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault e) { 
            System.out.println("Expected exception: ResourceUnknownFault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking add...");
        org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.Add _add_addRequest = null;
        try {
            org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType _add__return = port.add(_add_addRequest);
            System.out.println("add.result=" + _add__return);

        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.ContentCreationFailedFault e) { 
            System.out.println("Expected exception: ContentCreationFailedFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.UnsupportedMemberInterfaceFault e) { 
            System.out.println("Expected exception: UnsupportedMemberInterfaceFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.AddRefusedFault e) { 
            System.out.println("Expected exception: AddRefusedFault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking query...");
        org.cagrid.index.wsrf.stubs.QueryType _query_queryRequest = null;
        org.cagrid.index.wsrf.stubs.QueryResponse _query__return = port.query(_query_queryRequest);
        System.out.println("query.result=" + _query__return);


        }
        {
        System.out.println("Invoking getResourceProperty...");
        javax.xml.namespace.QName _getResourceProperty_getResourcePropertyRequest = new javax.xml.namespace.QName("", "");
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
        System.out.println("Invoking queryResourceProperties...");
        org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties _queryResourceProperties_queryResourcePropertiesRequest = null;
        try {
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse _queryResourceProperties__return = port.queryResourceProperties(_queryResourceProperties_queryResourcePropertiesRequest);
            System.out.println("queryResourceProperties.result=" + _queryResourceProperties__return);

        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault e) { 
            System.out.println("Expected exception: UnknownQueryExpressionDialectFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault e) { 
            System.out.println("Expected exception: InvalidQueryExpressionFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault e) { 
            System.out.println("Expected exception: QueryEvaluationErrorFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault e) { 
            System.out.println("Expected exception: ResourceUnknownFault has occurred.");
            System.out.println(e.toString());
        } catch (org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault e) { 
            System.out.println("Expected exception: InvalidResourcePropertyQNameFault has occurred.");
            System.out.println(e.toString());
        }
            }

        System.exit(0);
    }

}

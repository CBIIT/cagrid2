package org.cagrid.index.service;

import java.util.List;

import javax.xml.namespace.QName;

import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryExpressionType;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault;

public interface ResourcePropertyAccess {

    public List<Object> getMultipleResourceProperties(List<QName> rps) throws ResourceUnknownFault,
            InvalidResourcePropertyQNameFault;

    public List<Object> getResourceProperty(javax.xml.namespace.QName rp) throws ResourceUnknownFault,
            InvalidResourcePropertyQNameFault;

    public List<Object> queryResourceProperties(QueryExpressionType query) throws UnknownQueryExpressionDialectFault,
            InvalidQueryExpressionFault, QueryEvaluationErrorFault, ResourceUnknownFault,
            InvalidResourcePropertyQNameFault;
}

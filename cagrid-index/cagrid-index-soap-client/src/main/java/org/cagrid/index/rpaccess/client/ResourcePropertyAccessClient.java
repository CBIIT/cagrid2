package org.cagrid.index.rpaccess.client;

import java.util.List;

import javax.xml.namespace.QName;

import org.cagrid.core.soapclient.AbstractSoapClient;
import org.cagrid.index.service.ResourcePropertyAccess;
import org.cagrid.index.wsrf.stubs.BigIndexPortType;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourceProperties;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourcePropertiesResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryExpressionType;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault;

public class ResourcePropertyAccessClient extends AbstractSoapClient<ResourcePropertyAccess, BigIndexPortType>
        implements ResourcePropertyAccess {

    public ResourcePropertyAccessClient(String url) {
        super(url, ResourcePropertyAccess.class, BigIndexPortType.class);
    }

    public static void main(String[] args) {
        try {
            String url = "https://localhost:4443/gts";
            ResourcePropertyAccessClient client = new ResourcePropertyAccessClient(url);

            // KeyStoreType ts = new KeyStoreType();
            // ts.setFile("/Users/oster/Documents/caGrid/environments/keys/training-truststore.jks");
            // ts.setPassword("changeit");

            // client.setTruststore(ts);
            // client.getResourceProperty();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Object> getMultipleResourceProperties(List<QName> rps) throws ResourceUnknownFault,
            InvalidResourcePropertyQNameFault {

        GetMultipleResourceProperties req = new GetMultipleResourceProperties();
        req.getResourceProperty().addAll(rps);
        GetMultipleResourcePropertiesResponse resp = this.getClient().getMultipleResourceProperties(req);
        return resp.getAny();
    }

    public List<Object> getResourceProperty(javax.xml.namespace.QName rp) throws ResourceUnknownFault,
            InvalidResourcePropertyQNameFault {

        GetResourcePropertyResponse resp = this.getClient().getResourceProperty(rp);
        return resp.getAny();
    }

    public List<Object> queryResourceProperties(QueryExpressionType query) throws UnknownQueryExpressionDialectFault,
            InvalidQueryExpressionFault, QueryEvaluationErrorFault, ResourceUnknownFault,
            InvalidResourcePropertyQNameFault {

        QueryResourceProperties req = new QueryResourceProperties();
        req.setQueryExpression(query);
        QueryResourcePropertiesResponse resp = this.getClient().queryResourceProperties(req);
        return resp.getContent();
    }

}

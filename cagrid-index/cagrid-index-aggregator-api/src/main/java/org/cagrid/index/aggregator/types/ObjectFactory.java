
package org.cagrid.index.aggregator.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.cagrid.index.metrics.types.Accumulator;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.index.aggregator.types package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AggregatorPollType_QNAME = new QName("http://mds.globus.org/aggregator/types", "AggregatorPollType");
    private final static QName _RegistrationCount_QNAME = new QName("http://mds.globus.org/aggregator/types", "RegistrationCount");
    private final static QName _GetMultipleResourcePropertiesPollType_QNAME = new QName("http://mds.globus.org/aggregator/types", "GetMultipleResourcePropertiesPollType");
    private final static QName _GetResourcePropertyPollType_QNAME = new QName("http://mds.globus.org/aggregator/types", "GetResourcePropertyPollType");
    private final static QName _ExecutionPollType_QNAME = new QName("http://mds.globus.org/aggregator/types", "ExecutionPollType");
    private final static QName _QueryResourcePropertiesPollType_QNAME = new QName("http://mds.globus.org/aggregator/types", "QueryResourcePropertiesPollType");
    private final static QName _AggregatorSubscriptionType_QNAME = new QName("http://mds.globus.org/aggregator/types", "AggregatorSubscriptionType");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.index.aggregator.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExecutionPollType }
     * 
     */
    public ExecutionPollType createExecutionPollType() {
        return new ExecutionPollType();
    }

    /**
     * Create an instance of {@link AggregatorSubscriptionType }
     * 
     */
    public AggregatorSubscriptionType createAggregatorSubscriptionType() {
        return new AggregatorSubscriptionType();
    }

    /**
     * Create an instance of {@link QueryResourcePropertiesPollType }
     * 
     */
    public QueryResourcePropertiesPollType createQueryResourcePropertiesPollType() {
        return new QueryResourcePropertiesPollType();
    }

    /**
     * Create an instance of {@link GetResourcePropertyPollType }
     * 
     */
    public GetResourcePropertyPollType createGetResourcePropertyPollType() {
        return new GetResourcePropertyPollType();
    }

    /**
     * Create an instance of {@link AggregatorPollType }
     * 
     */
    public AggregatorPollType createAggregatorPollType() {
        return new AggregatorPollType();
    }

    /**
     * Create an instance of {@link GetMultipleResourcePropertiesPollType }
     * 
     */
    public GetMultipleResourcePropertiesPollType createGetMultipleResourcePropertiesPollType() {
        return new GetMultipleResourcePropertiesPollType();
    }

    /**
     * Create an instance of {@link AggregatorServiceGroupRP }
     * 
     */
    public AggregatorServiceGroupRP createAggregatorServiceGroupRP() {
        return new AggregatorServiceGroupRP();
    }

    /**
     * Create an instance of {@link PairedKeyType }
     * 
     */
    public PairedKeyType createPairedKeyType() {
        return new PairedKeyType();
    }

    /**
     * Create an instance of {@link AggregatorData }
     * 
     */
    public AggregatorData createAggregatorData() {
        return new AggregatorData();
    }

    /**
     * Create an instance of {@link AggregatorConfig }
     * 
     */
    public AggregatorConfig createAggregatorConfig() {
        return new AggregatorConfig();
    }

    /**
     * Create an instance of {@link AggregatorContent }
     * 
     */
    public AggregatorContent createAggregatorContent() {
        return new AggregatorContent();
    }

    /**
     * Create an instance of {@link AggregatorError }
     * 
     */
    public AggregatorError createAggregatorError() {
        return new AggregatorError();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggregatorPollType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mds.globus.org/aggregator/types", name = "AggregatorPollType")
    public JAXBElement<AggregatorPollType> createAggregatorPollType(AggregatorPollType value) {
        return new JAXBElement<AggregatorPollType>(_AggregatorPollType_QNAME, AggregatorPollType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Accumulator }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mds.globus.org/aggregator/types", name = "RegistrationCount")
    public JAXBElement<Accumulator> createRegistrationCount(Accumulator value) {
        return new JAXBElement<Accumulator>(_RegistrationCount_QNAME, Accumulator.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMultipleResourcePropertiesPollType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mds.globus.org/aggregator/types", name = "GetMultipleResourcePropertiesPollType")
    public JAXBElement<GetMultipleResourcePropertiesPollType> createGetMultipleResourcePropertiesPollType(GetMultipleResourcePropertiesPollType value) {
        return new JAXBElement<GetMultipleResourcePropertiesPollType>(_GetMultipleResourcePropertiesPollType_QNAME, GetMultipleResourcePropertiesPollType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResourcePropertyPollType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mds.globus.org/aggregator/types", name = "GetResourcePropertyPollType")
    public JAXBElement<GetResourcePropertyPollType> createGetResourcePropertyPollType(GetResourcePropertyPollType value) {
        return new JAXBElement<GetResourcePropertyPollType>(_GetResourcePropertyPollType_QNAME, GetResourcePropertyPollType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecutionPollType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mds.globus.org/aggregator/types", name = "ExecutionPollType")
    public JAXBElement<ExecutionPollType> createExecutionPollType(ExecutionPollType value) {
        return new JAXBElement<ExecutionPollType>(_ExecutionPollType_QNAME, ExecutionPollType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryResourcePropertiesPollType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mds.globus.org/aggregator/types", name = "QueryResourcePropertiesPollType")
    public JAXBElement<QueryResourcePropertiesPollType> createQueryResourcePropertiesPollType(QueryResourcePropertiesPollType value) {
        return new JAXBElement<QueryResourcePropertiesPollType>(_QueryResourcePropertiesPollType_QNAME, QueryResourcePropertiesPollType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggregatorSubscriptionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mds.globus.org/aggregator/types", name = "AggregatorSubscriptionType")
    public JAXBElement<AggregatorSubscriptionType> createAggregatorSubscriptionType(AggregatorSubscriptionType value) {
        return new JAXBElement<AggregatorSubscriptionType>(_AggregatorSubscriptionType_QNAME, AggregatorSubscriptionType.class, null, value);
    }

}

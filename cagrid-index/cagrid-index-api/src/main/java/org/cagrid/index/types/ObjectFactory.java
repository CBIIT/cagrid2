
package org.cagrid.index.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.index.types package. 
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

    private final static QName _BigIndexContent_QNAME = new QName("http://mds.globus.org/bigindex/2008/11/24/types", "BigIndexContent");
    private final static QName _BigIndexEntry_QNAME = new QName("http://mds.globus.org/bigindex/2008/11/24/types", "BigIndexEntry");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.index.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BigIndexEntry }
     * 
     */
    public BigIndexEntry createBigIndexEntry() {
        return new BigIndexEntry();
    }

    /**
     * Create an instance of {@link BigIndexContent }
     * 
     */
    public BigIndexContent createBigIndexContent() {
        return new BigIndexContent();
    }

    /**
     * Create an instance of {@link BigIndexContentIDList }
     * 
     */
    public BigIndexContentIDList createBigIndexContentIDList() {
        return new BigIndexContentIDList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigIndexContent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mds.globus.org/bigindex/2008/11/24/types", name = "BigIndexContent")
    public JAXBElement<BigIndexContent> createBigIndexContent(BigIndexContent value) {
        return new JAXBElement<BigIndexContent>(_BigIndexContent_QNAME, BigIndexContent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigIndexEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mds.globus.org/bigindex/2008/11/24/types", name = "BigIndexEntry")
    public JAXBElement<BigIndexEntry> createBigIndexEntry(BigIndexEntry value) {
        return new JAXBElement<BigIndexEntry>(_BigIndexEntry_QNAME, BigIndexEntry.class, null, value);
    }

}

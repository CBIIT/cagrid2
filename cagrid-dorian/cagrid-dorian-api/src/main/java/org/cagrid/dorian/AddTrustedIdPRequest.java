
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.cagrid.dorian.model.federation.TrustedIdP;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idp">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}TrustedIdP"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idp"
})
@XmlRootElement(name = "AddTrustedIdPRequest")
public class AddTrustedIdPRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected AddTrustedIdPRequest.Idp idp;

    /**
     * Gets the value of the idp property.
     * 
     * @return
     *     possible object is
     *     {@link AddTrustedIdPRequest.Idp }
     *     
     */
    public AddTrustedIdPRequest.Idp getIdp() {
        return idp;
    }

    /**
     * Sets the value of the idp property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddTrustedIdPRequest.Idp }
     *     
     */
    public void setIdp(AddTrustedIdPRequest.Idp value) {
        this.idp = value;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            AddTrustedIdPRequest.Idp theIdp;
            theIdp = this.getIdp();
            strategy.appendField(locator, this, "idp", buffer, theIdp);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            AddTrustedIdPRequest.Idp theIdp;
            theIdp = this.getIdp();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "idp", theIdp), currentHashCode, theIdp);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AddTrustedIdPRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AddTrustedIdPRequest that = ((AddTrustedIdPRequest) object);
        {
            AddTrustedIdPRequest.Idp lhsIdp;
            lhsIdp = this.getIdp();
            AddTrustedIdPRequest.Idp rhsIdp;
            rhsIdp = that.getIdp();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "idp", lhsIdp), LocatorUtils.property(thatLocator, "idp", rhsIdp), lhsIdp, rhsIdp)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}TrustedIdP"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "trustedIdP"
    })
    public static class Idp
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "TrustedIdP", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected TrustedIdP trustedIdP;

        /**
         * Gets the value of the trustedIdP property.
         * 
         * @return
         *     possible object is
         *     {@link TrustedIdP }
         *     
         */
        public TrustedIdP getTrustedIdP() {
            return trustedIdP;
        }

        /**
         * Sets the value of the trustedIdP property.
         * 
         * @param value
         *     allowed object is
         *     {@link TrustedIdP }
         *     
         */
        public void setTrustedIdP(TrustedIdP value) {
            this.trustedIdP = value;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                TrustedIdP theTrustedIdP;
                theTrustedIdP = this.getTrustedIdP();
                strategy.appendField(locator, this, "trustedIdP", buffer, theTrustedIdP);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                TrustedIdP theTrustedIdP;
                theTrustedIdP = this.getTrustedIdP();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "trustedIdP", theTrustedIdP), currentHashCode, theTrustedIdP);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof AddTrustedIdPRequest.Idp)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final AddTrustedIdPRequest.Idp that = ((AddTrustedIdPRequest.Idp) object);
            {
                TrustedIdP lhsTrustedIdP;
                lhsTrustedIdP = this.getTrustedIdP();
                TrustedIdP rhsTrustedIdP;
                rhsTrustedIdP = that.getTrustedIdP();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "trustedIdP", lhsTrustedIdP), LocatorUtils.property(thatLocator, "trustedIdP", rhsTrustedIdP), lhsTrustedIdP, rhsTrustedIdP)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

    }

}

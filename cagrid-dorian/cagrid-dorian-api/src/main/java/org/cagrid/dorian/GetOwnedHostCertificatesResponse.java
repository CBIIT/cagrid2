
package org.cagrid.dorian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.cagrid.dorian.model.federation.HostCertificateRecord;
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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateRecord" maxOccurs="unbounded"/>
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
    "hostCertificateRecord"
})
@XmlRootElement(name = "GetOwnedHostCertificatesResponse")
public class GetOwnedHostCertificatesResponse
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "HostCertificateRecord", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
    protected List<HostCertificateRecord> hostCertificateRecord;

    /**
     * Gets the value of the hostCertificateRecord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hostCertificateRecord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHostCertificateRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HostCertificateRecord }
     * 
     * 
     */
    public List<HostCertificateRecord> getHostCertificateRecord() {
        if (hostCertificateRecord == null) {
            hostCertificateRecord = new ArrayList<HostCertificateRecord>();
        }
        return this.hostCertificateRecord;
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
            List<HostCertificateRecord> theHostCertificateRecord;
            theHostCertificateRecord = (((this.hostCertificateRecord!= null)&&(!this.hostCertificateRecord.isEmpty()))?this.getHostCertificateRecord():null);
            strategy.appendField(locator, this, "hostCertificateRecord", buffer, theHostCertificateRecord);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<HostCertificateRecord> theHostCertificateRecord;
            theHostCertificateRecord = (((this.hostCertificateRecord!= null)&&(!this.hostCertificateRecord.isEmpty()))?this.getHostCertificateRecord():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostCertificateRecord", theHostCertificateRecord), currentHashCode, theHostCertificateRecord);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof GetOwnedHostCertificatesResponse)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final GetOwnedHostCertificatesResponse that = ((GetOwnedHostCertificatesResponse) object);
        {
            List<HostCertificateRecord> lhsHostCertificateRecord;
            lhsHostCertificateRecord = (((this.hostCertificateRecord!= null)&&(!this.hostCertificateRecord.isEmpty()))?this.getHostCertificateRecord():null);
            List<HostCertificateRecord> rhsHostCertificateRecord;
            rhsHostCertificateRecord = (((that.hostCertificateRecord!= null)&&(!that.hostCertificateRecord.isEmpty()))?that.getHostCertificateRecord():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hostCertificateRecord", lhsHostCertificateRecord), LocatorUtils.property(thatLocator, "hostCertificateRecord", rhsHostCertificateRecord), lhsHostCertificateRecord, rhsHostCertificateRecord)) {
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

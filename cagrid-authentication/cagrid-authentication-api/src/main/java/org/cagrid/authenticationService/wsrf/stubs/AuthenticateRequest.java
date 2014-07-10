
package org.cagrid.authenticationService.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="credential">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/authentication-service}Credential"/>
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
    "credential"
})
@XmlRootElement(name = "AuthenticateRequest")
public class AuthenticateRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected AuthenticateRequest.Credential credential;

    /**
     * Gets the value of the credential property.
     * 
     * @return
     *     possible object is
     *     {@link AuthenticateRequest.Credential }
     *     
     */
    public AuthenticateRequest.Credential getCredential() {
        return credential;
    }

    /**
     * Sets the value of the credential property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticateRequest.Credential }
     *     
     */
    public void setCredential(AuthenticateRequest.Credential value) {
        this.credential = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/authentication-service}Credential"/>
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
        "credential"
    })
    public static class Credential
        implements Serializable
    {

        @XmlElement(name = "Credential", namespace = "http://cagrid.nci.nih.gov/1/authentication-service", required = true)
        protected org.cagrid.gaards.authentication.service.Credential credential;

        /**
         * Gets the value of the credential property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gaards.authentication.service.Credential }
         *     
         */
        public org.cagrid.gaards.authentication.service.Credential getCredential() {
            return credential;
        }

        /**
         * Sets the value of the credential property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gaards.authentication.service.Credential }
         *     
         */
        public void setCredential(org.cagrid.gaards.authentication.service.Credential value) {
            this.credential = value;
        }

    }

}

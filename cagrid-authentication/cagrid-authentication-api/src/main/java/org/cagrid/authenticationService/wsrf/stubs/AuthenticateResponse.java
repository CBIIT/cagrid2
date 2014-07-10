
package org.cagrid.authenticationService.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gaards.authentication.service.SAMLAssertion;


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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/authentication-service}SAMLAssertion"/>
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
    "samlAssertion"
})
@XmlRootElement(name = "AuthenticateResponse")
public class AuthenticateResponse
    implements Serializable
{

    @XmlElement(name = "SAMLAssertion", namespace = "http://cagrid.nci.nih.gov/1/authentication-service", required = true)
    protected SAMLAssertion samlAssertion;

    /**
     * Gets the value of the samlAssertion property.
     * 
     * @return
     *     possible object is
     *     {@link SAMLAssertion }
     *     
     */
    public SAMLAssertion getSAMLAssertion() {
        return samlAssertion;
    }

    /**
     * Sets the value of the samlAssertion property.
     * 
     * @param value
     *     allowed object is
     *     {@link SAMLAssertion }
     *     
     */
    public void setSAMLAssertion(SAMLAssertion value) {
        this.samlAssertion = value;
    }

}

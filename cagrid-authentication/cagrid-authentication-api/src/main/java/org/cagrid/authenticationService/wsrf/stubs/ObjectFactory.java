
package org.cagrid.authenticationService.wsrf.stubs;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.authenticationService.wsrf.stubs package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.authenticationService.wsrf.stubs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthenticateRequest }
     * 
     */
    public AuthenticateRequest createAuthenticateRequest() {
        return new AuthenticateRequest();
    }

    /**
     * Create an instance of {@link AuthenticationServiceResourceProperties }
     * 
     */
    public AuthenticationServiceResourceProperties createAuthenticationServiceResourceProperties() {
        return new AuthenticationServiceResourceProperties();
    }

    /**
     * Create an instance of {@link AuthenticateRequest.Credential }
     * 
     */
    public AuthenticateRequest.Credential createAuthenticateRequestCredential() {
        return new AuthenticateRequest.Credential();
    }

    /**
     * Create an instance of {@link AuthenticateResponse }
     * 
     */
    public AuthenticateResponse createAuthenticateResponse() {
        return new AuthenticateResponse();
    }

}

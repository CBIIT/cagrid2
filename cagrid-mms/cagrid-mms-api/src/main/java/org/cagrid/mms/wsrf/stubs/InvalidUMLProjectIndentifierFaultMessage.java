
package org.cagrid.mms.wsrf.stubs;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.3
 * 2014-02-23T21:40:51.832-05:00
 * Generated source version: 2.6.3
 */

@WebFault(name = "InvalidUMLProjectIndentifier", targetNamespace = "http://mms.cagrid.org/MetadataModelService/types")
public class InvalidUMLProjectIndentifierFaultMessage extends Exception {
    
    private org.cagrid.mms.types.InvalidUMLProjectIndentifier invalidUMLProjectIndentifier;

    public InvalidUMLProjectIndentifierFaultMessage() {
        super();
    }
    
    public InvalidUMLProjectIndentifierFaultMessage(String message) {
        super(message);
    }
    
    public InvalidUMLProjectIndentifierFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUMLProjectIndentifierFaultMessage(String message, org.cagrid.mms.types.InvalidUMLProjectIndentifier invalidUMLProjectIndentifier) {
        super(message);
        this.invalidUMLProjectIndentifier = invalidUMLProjectIndentifier;
    }

    public InvalidUMLProjectIndentifierFaultMessage(String message, org.cagrid.mms.types.InvalidUMLProjectIndentifier invalidUMLProjectIndentifier, Throwable cause) {
        super(message, cause);
        this.invalidUMLProjectIndentifier = invalidUMLProjectIndentifier;
    }

    public org.cagrid.mms.types.InvalidUMLProjectIndentifier getFaultInfo() {
        return this.invalidUMLProjectIndentifier;
    }
}

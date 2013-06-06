
package org.cagrid.gts.wsrf.stubs;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.3
 * 2013-06-04T12:08:10.850-04:00
 * Generated source version: 2.6.3
 */

@WebFault(name = "IllegalPermissionFault", targetNamespace = "http://cagrid.nci.nih.gov/GTS/types")
public class IllegalPermissionFaultFaultMessage extends Exception {
    
    private org.cagrid.gts.types.IllegalPermissionFault illegalPermissionFault;

    public IllegalPermissionFaultFaultMessage() {
        super();
    }
    
    public IllegalPermissionFaultFaultMessage(String message) {
        super(message);
    }
    
    public IllegalPermissionFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalPermissionFaultFaultMessage(String message, org.cagrid.gts.types.IllegalPermissionFault illegalPermissionFault) {
        super(message);
        this.illegalPermissionFault = illegalPermissionFault;
    }

    public IllegalPermissionFaultFaultMessage(String message, org.cagrid.gts.types.IllegalPermissionFault illegalPermissionFault, Throwable cause) {
        super(message, cause);
        this.illegalPermissionFault = illegalPermissionFault;
    }

    public org.cagrid.gts.types.IllegalPermissionFault getFaultInfo() {
        return this.illegalPermissionFault;
    }
}

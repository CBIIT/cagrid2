
package org.cagrid.gts.wsrf.stubs;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.3
 * 2013-06-04T12:08:10.913-04:00
 * Generated source version: 2.6.3
 */

@WebFault(name = "IllegalTrustLevelFault", targetNamespace = "http://cagrid.nci.nih.gov/GTS/types")
public class IllegalTrustLevelFaultFaultMessage extends Exception {
    
    private org.cagrid.gts.types.IllegalTrustLevelFault illegalTrustLevelFault;

    public IllegalTrustLevelFaultFaultMessage() {
        super();
    }
    
    public IllegalTrustLevelFaultFaultMessage(String message) {
        super(message);
    }
    
    public IllegalTrustLevelFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalTrustLevelFaultFaultMessage(String message, org.cagrid.gts.types.IllegalTrustLevelFault illegalTrustLevelFault) {
        super(message);
        this.illegalTrustLevelFault = illegalTrustLevelFault;
    }

    public IllegalTrustLevelFaultFaultMessage(String message, org.cagrid.gts.types.IllegalTrustLevelFault illegalTrustLevelFault, Throwable cause) {
        super(message, cause);
        this.illegalTrustLevelFault = illegalTrustLevelFault;
    }

    public org.cagrid.gts.types.IllegalTrustLevelFault getFaultInfo() {
        return this.illegalTrustLevelFault;
    }
}


package org.cagrid.gridgrouper.wsrf.stubs;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.3
 * 2013-05-13T14:22:39.050-04:00
 * Generated source version: 2.6.3
 */

@WebFault(name = "RevokePrivilegeFault", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper/types")
public class RevokePrivilegeFaultFaultMessage extends Exception {
    
    private org.cagrid.gridgrouper.types.RevokePrivilegeFault revokePrivilegeFault;

    public RevokePrivilegeFaultFaultMessage() {
        super();
    }
    
    public RevokePrivilegeFaultFaultMessage(String message) {
        super(message);
    }
    
    public RevokePrivilegeFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public RevokePrivilegeFaultFaultMessage(String message, org.cagrid.gridgrouper.types.RevokePrivilegeFault revokePrivilegeFault) {
        super(message);
        this.revokePrivilegeFault = revokePrivilegeFault;
    }

    public RevokePrivilegeFaultFaultMessage(String message, org.cagrid.gridgrouper.types.RevokePrivilegeFault revokePrivilegeFault, Throwable cause) {
        super(message, cause);
        this.revokePrivilegeFault = revokePrivilegeFault;
    }

    public org.cagrid.gridgrouper.types.RevokePrivilegeFault getFaultInfo() {
        return this.revokePrivilegeFault;
    }
}
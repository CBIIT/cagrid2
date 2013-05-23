
package org.cagrid.gridgrouper.wsrf.stubs;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.3
 * 2013-05-13T14:22:39.076-04:00
 * Generated source version: 2.6.3
 */

@WebFault(name = "GroupModifyFault", targetNamespace = "http://cagrid.nci.nih.gov/GridGrouper/types")
public class GroupModifyFaultFaultMessage extends Exception {
    
    private org.cagrid.gridgrouper.types.GroupModifyFault groupModifyFault;

    public GroupModifyFaultFaultMessage() {
        super();
    }
    
    public GroupModifyFaultFaultMessage(String message) {
        super(message);
    }
    
    public GroupModifyFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupModifyFaultFaultMessage(String message, org.cagrid.gridgrouper.types.GroupModifyFault groupModifyFault) {
        super(message);
        this.groupModifyFault = groupModifyFault;
    }

    public GroupModifyFaultFaultMessage(String message, org.cagrid.gridgrouper.types.GroupModifyFault groupModifyFault, Throwable cause) {
        super(message, cause);
        this.groupModifyFault = groupModifyFault;
    }

    public org.cagrid.gridgrouper.types.GroupModifyFault getFaultInfo() {
        return this.groupModifyFault;
    }
}

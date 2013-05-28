package org.cagrid.gaards.dorian.federation;

import org.cagrid.gaards.dorian.stubs.types.DorianInternalFault;
import org.cagrid.gaards.dorian.stubs.types.UserPolicyFault;


/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */

public class ManualApprovalPolicy extends AccountPolicy {
    public void applyPolicy(TrustedIdP idp, GridUser user) throws DorianInternalFault, UserPolicyFault {

    }


    public String getDisplayName() {
        return "Manual Approval";
    }
}
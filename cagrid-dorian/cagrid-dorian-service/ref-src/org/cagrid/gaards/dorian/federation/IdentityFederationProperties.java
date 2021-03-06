package org.cagrid.gaards.dorian.federation;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.cagrid.gaards.dorian.common.Lifetime;
import org.cagrid.gaards.dorian.policy.HostCertificateRenewalPolicy;
import org.cagrid.gaards.dorian.policy.SearchPolicyType;
import org.cagrid.gaards.dorian.stubs.types.DorianInternalFault;


public class IdentityFederationProperties {

    public static int MIN_IDP_NAME_LENGTH = 3;
    public static int MAX_IDP_NAME_LENGTH = 60;
    public static int DEFAULT_MIN_IDP_DISPLAY_NAME_LENGTH = 3;
    public static int DEFAULT_MAX_IDP_DISPLAY_NAME_LENGTH = 60;
    public static int DEFAULT_MIN_IDP_NAME_LENGTH = 3;
    public static int DEFAULT_MAX_IDP_NAME_LENGTH = 25;

    private String identityAssignmentPolicy;
    private int minIdPNameLength;
    private int maxIdPNameLength;
    private int minIdPDisplayNameLength;
    private int maxIdPDisplayNameLength;
    private Lifetime issuedCertificateLifetime;
    private boolean autoHostCertificateApproval;
    private Lifetime userCertificateLifetime;
    private List<AccountPolicy> accountPolicies;
    private List<String> gtsPublishCRLList;
    private SearchPolicyType hostSearchPolicy;
    private SearchPolicyType userSearchPolicy;
    private HostCertificateRenewalPolicy hostCertificateRenewalPolicy;


    public IdentityFederationProperties() {
        this.identityAssignmentPolicy = IdentityAssignmentPolicy.NAME;
        this.minIdPNameLength = DEFAULT_MIN_IDP_NAME_LENGTH;
        this.maxIdPNameLength = DEFAULT_MAX_IDP_NAME_LENGTH;
        this.minIdPDisplayNameLength = DEFAULT_MIN_IDP_DISPLAY_NAME_LENGTH;
        this.maxIdPDisplayNameLength = DEFAULT_MAX_IDP_DISPLAY_NAME_LENGTH;
        this.issuedCertificateLifetime = new Lifetime();
        this.issuedCertificateLifetime.setYears(1);
        this.autoHostCertificateApproval = false;
        this.userCertificateLifetime = new Lifetime();
        this.userCertificateLifetime.setHours(12);
        this.accountPolicies = new ArrayList<AccountPolicy>();
        this.accountPolicies.add(new ManualApprovalPolicy());
        this.gtsPublishCRLList = new ArrayList<String>();
        this.userSearchPolicy = SearchPolicyType.Admin;
        this.hostSearchPolicy = SearchPolicyType.Admin;
    }


    public int getMinIdPDisplayNameLength() {
        return minIdPDisplayNameLength;
    }


    public void setMinIdPDisplayNameLength(int minIdPDisplayNameLength) {
        this.minIdPDisplayNameLength = minIdPDisplayNameLength;
    }


    public int getMaxIdPDisplayNameLength() {
        return maxIdPDisplayNameLength;
    }


    public void setMaxIdPDisplayNameLength(int maxIdPDisplayNameLength) {
        this.maxIdPDisplayNameLength = maxIdPDisplayNameLength;
    }


    public String getIdentityAssignmentPolicy() {
        return identityAssignmentPolicy;
    }


    public void setIdentityAssignmentPolicy(String identityAssignmentPolicy) throws DorianInternalFault {
        if (IdentityAssignmentPolicy.isValidPolicy(identityAssignmentPolicy)) {
            this.identityAssignmentPolicy = identityAssignmentPolicy;
        } else {
            DorianInternalFault f = new DorianInternalFault();
            f.setFaultString("Invalid identity assigment policy specified.");
            throw f;
        }
    }


    public int getMinIdPNameLength() {
        return minIdPNameLength;
    }


    public void setMinIdPNameLength(int minIdPNameLength) throws DorianInternalFault {
        if (this.minIdPNameLength < MIN_IDP_NAME_LENGTH) {
            DorianInternalFault f = new DorianInternalFault();
            f.setFaultString("The minumum IdP name length must be at least " + MIN_IDP_NAME_LENGTH + " characters.");
            throw f;
        }
        this.minIdPNameLength = minIdPNameLength;
    }


    public int getMaxIdPNameLength() {
        return maxIdPNameLength;
    }


    public void setMaxIdPNameLength(int maxIdPNameLength) throws DorianInternalFault {
        if (this.maxIdPNameLength > MAX_IDP_NAME_LENGTH) {
            DorianInternalFault f = new DorianInternalFault();
            f.setFaultString("The maximum IdP name length must be nore more than " + MAX_IDP_NAME_LENGTH
                + " characters.");
            throw f;
        }
        this.maxIdPNameLength = maxIdPNameLength;
    }


    public Lifetime getIssuedCertificateLifetime() {
        return issuedCertificateLifetime;
    }


    public void setIssuedCertificateLifetime(Lifetime issuedCertificateLifetime) {
        this.issuedCertificateLifetime = issuedCertificateLifetime;
    }


    public boolean autoHostCertificateApproval() {
        return autoHostCertificateApproval;
    }


    public void setAutoHostCertificateApproval(boolean autoCertificateApproval) {
        this.autoHostCertificateApproval = autoCertificateApproval;
    }


    public Lifetime getUserCertificateLifetime() {
        return userCertificateLifetime;
    }


    public void setUserCertificateLifetime(Lifetime maxProxyLifetime) throws DorianInternalFault {
        if ((this.userCertificateLifetime.getYears() != 0) || (this.userCertificateLifetime.getMonths() != 0)
            || (this.userCertificateLifetime.getDays() != 0)) {
            DorianInternalFault f = new DorianInternalFault();
            f.setFaultString("The max proxy lifetime configuration cannot specify years, months, or days.");
            throw f;
        }
        this.userCertificateLifetime = maxProxyLifetime;
    }


    public List<AccountPolicy> getAccountPolicies() {
        return accountPolicies;
    }


    public void setAccountPolicies(List<AccountPolicy> accountPolicies) {
        this.accountPolicies = accountPolicies;
    }


    public List<String> getCRLPublishingList() {
        return gtsPublishCRLList;
    }


    public void setCRLPublishList(String list) {
        StringTokenizer st = new StringTokenizer(list, ",");
        while (st.hasMoreTokens()) {
            this.gtsPublishCRLList.add(st.nextToken());
        }
    }


    public String getHostSearchPolicy() {
        if (hostSearchPolicy == null) {
            this.hostSearchPolicy = SearchPolicyType.Admin;
        }
        return hostSearchPolicy.getValue();
    }


    public void setHostSearchPolicy(String searchPolicy) throws DorianInternalFault {
        if (searchPolicy.equals(SearchPolicyType.Public.getValue())
            || searchPolicy.equals(SearchPolicyType.Authenticated.getValue())
            || searchPolicy.equals(SearchPolicyType.Admin.getValue())) {
            this.hostSearchPolicy = SearchPolicyType.fromValue(searchPolicy);
        } else {
            DorianInternalFault f = new DorianInternalFault();
            f.setFaultString("The user search policy " + searchPolicy
                + ", is invalid.  Please specify a valid search policy (" + SearchPolicyType.Public.getValue() + ", "
                + SearchPolicyType.Authenticated.getValue() + ", " + SearchPolicyType.Admin.getValue() + ").");
            throw f;
        }
    }


    public String getUserSearchPolicy() {
        if (userSearchPolicy == null) {
            this.userSearchPolicy = SearchPolicyType.Admin;
        }
        return userSearchPolicy.getValue();
    }


    public void setUserSearchPolicy(String searchPolicy) throws DorianInternalFault {
        if (searchPolicy.equals(SearchPolicyType.Public.getValue())
            || searchPolicy.equals(SearchPolicyType.Authenticated.getValue())
            || searchPolicy.equals(SearchPolicyType.Admin.getValue())) {
            this.userSearchPolicy = SearchPolicyType.fromValue(searchPolicy);
        } else {
            DorianInternalFault f = new DorianInternalFault();
            f.setFaultString("The user search policy " + searchPolicy
                + ", is invalid.  Please specify a valid search policy (" + SearchPolicyType.Public.getValue() + ", "
                + SearchPolicyType.Authenticated.getValue() + ", " + SearchPolicyType.Admin.getValue() + ").");
            throw f;
        }
    }


    public String getHostCertificateRenewalPolicy() {
        if (hostCertificateRenewalPolicy == null) {
            this.hostCertificateRenewalPolicy = HostCertificateRenewalPolicy.Admin;
        }
        return hostCertificateRenewalPolicy.getValue();
    }


    public void setHostCertificateRenewalPolicy(String renewalPolicy) throws DorianInternalFault {
        if (renewalPolicy.equals(HostCertificateRenewalPolicy.Owner.getValue())
            || renewalPolicy.equals(HostCertificateRenewalPolicy.Admin.getValue())) {
            this.hostCertificateRenewalPolicy = HostCertificateRenewalPolicy.fromValue(renewalPolicy);
        } else {
            DorianInternalFault f = new DorianInternalFault();
            f.setFaultString("The host certificate renewal policy " + renewalPolicy
                + ", is invalid.  Please specify a valid renewal policy ("
                + HostCertificateRenewalPolicy.Owner.getValue() + ", " + HostCertificateRenewalPolicy.Admin.getValue()
                + ").");
            throw f;
        }
    }

}

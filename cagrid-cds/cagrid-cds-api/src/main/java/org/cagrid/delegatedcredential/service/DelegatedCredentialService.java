package org.cagrid.delegatedcredential.service;

import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
import org.cagrid.cds.model.CertificateChain;
import org.cagrid.cds.model.PublicKey;
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.DelegationException;
import org.cagrid.cds.service.exception.PermissionDeniedException;
import org.cagrid.wsrf.properties.ResourceHome;

public interface DelegatedCredentialService {

    ResourceHome getResourceHome();

    ServiceSecurityMetadata getServiceSecurityMetadata();

    CertificateChain getDelegatedCredential(String callerGridIdentity, PublicKey publicKey) throws DelegationException, PermissionDeniedException, CDSInternalException;
}
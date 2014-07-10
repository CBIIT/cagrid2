package org.cagrid.authenticationService.exceptions;

import org.cagrid.core.common.FaultException;
import org.cagrid.gaards.authentication.faults.CredentialNotSupportedFault;

public class CredentialNotSupportedException extends Exception implements
		FaultException<CredentialNotSupportedFault> {
	private static final long serialVersionUID = 821835969676264972L;

	private final CredentialNotSupportedFault fault;

	public CredentialNotSupportedException(CredentialNotSupportedFault fault,
			String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public CredentialNotSupportedFault getFault() {
		return fault;
	}
}

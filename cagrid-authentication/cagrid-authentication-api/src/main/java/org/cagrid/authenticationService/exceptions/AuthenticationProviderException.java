package org.cagrid.authenticationService.exceptions;

import org.cagrid.core.common.FaultException;
import org.cagrid.gaards.authentication.faults.AuthenticationProviderFault;

public class AuthenticationProviderException extends Exception implements
		FaultException<AuthenticationProviderFault> {
	private static final long serialVersionUID = 821835969676264972L;

	private final AuthenticationProviderFault fault;

	public AuthenticationProviderException(AuthenticationProviderFault fault,
			String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public AuthenticationProviderFault getFault() {
		return fault;
	}
}

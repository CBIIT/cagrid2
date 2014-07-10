package org.cagrid.authenticationService.exceptions;

import org.cagrid.core.common.FaultException;
import org.cagrid.gaards.authentication.faults.InvalidCredentialFault;

public class InvalidCredentialException extends Exception implements
		FaultException<InvalidCredentialFault> {
	private static final long serialVersionUID = 821835969676264972L;

	private final InvalidCredentialFault fault;

	public InvalidCredentialException(InvalidCredentialFault fault,
			String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public InvalidCredentialFault getFault() {
		return fault;
	}
}

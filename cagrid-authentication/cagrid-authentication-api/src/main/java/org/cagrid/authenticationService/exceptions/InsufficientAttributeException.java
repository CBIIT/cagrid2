package org.cagrid.authenticationService.exceptions;

import org.cagrid.core.common.FaultException;
import org.cagrid.gaards.authentication.faults.InsufficientAttributeFault;

public class InsufficientAttributeException extends Exception implements
			FaultException<InsufficientAttributeFault> {
		private static final long serialVersionUID = 821835969676264972L;

		private final InsufficientAttributeFault fault;

		public InsufficientAttributeException(InsufficientAttributeFault fault, String message) {
			super(message);
			this.fault = fault;
		}

		@Override
		public InsufficientAttributeFault getFault() {
			return fault;
		}
	
}

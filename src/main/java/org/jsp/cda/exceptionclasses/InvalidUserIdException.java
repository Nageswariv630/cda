package org.jsp.cda.exceptionclasses;

import lombok.Builder;

@Builder
public class InvalidUserIdException extends RuntimeException{
	private String message;
	public InvalidUserIdException() {
		
	}
	public InvalidUserIdException(String message) {
		this.message=message;
	}
	@Override
	public String getMessage() {
		return this.message;
	}

}

package com.remember.inhoeku.remember_test.domain.error;

public class BusinessException extends RuntimeException {
	private String errorType;
	private String message;

	public BusinessException(String key, String message){
		this.errorType = key;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public String getErrorType(){ return errorType; }
}

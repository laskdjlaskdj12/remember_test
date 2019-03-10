package com.remember.inhoeku.remember_test.domain.error;

public class BusinessException extends RuntimeException {
	private String key;
	private String message;
	public BusinessException(String message){
		this.message = message;
	}

	public BusinessException(String key, String message){
		this.key = key;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}

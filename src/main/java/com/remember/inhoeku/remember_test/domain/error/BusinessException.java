package com.remember.inhoeku.remember_test.domain.error;

import lombok.AllArgsConstructor;
import lombok.Data;

public class BusinessException extends RuntimeException {
	private String message;
	public BusinessException(String message){
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}

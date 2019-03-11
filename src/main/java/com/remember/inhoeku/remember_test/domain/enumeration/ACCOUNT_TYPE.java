package com.remember.inhoeku.remember_test.domain.enumeration;

import lombok.Getter;

@Getter
public enum ACCOUNT_TYPE {
	PASSENGER(0),
	DRIVER(1);

	private int value;

	ACCOUNT_TYPE(int value){
		this.value = value;
	}
}
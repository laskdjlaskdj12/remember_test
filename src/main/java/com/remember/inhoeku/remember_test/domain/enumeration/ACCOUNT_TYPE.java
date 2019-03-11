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

	public static ACCOUNT_TYPE fromValue(int value){
		switch(value){
			case 0:
				return ACCOUNT_TYPE.PASSENGER;
			case 1:
				return ACCOUNT_TYPE.DRIVER;
			default:
				return null;
		}
	}
}
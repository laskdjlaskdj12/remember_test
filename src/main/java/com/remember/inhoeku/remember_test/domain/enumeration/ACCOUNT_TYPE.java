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

	public int getValue(){
		return value;
	}

	public ACCOUNT_TYPE fromString(String source){
		for (ACCOUNT_TYPE e : values()){
			if (e.name().toUpperCase().equals(source.toUpperCase())) {
				return e;
			}
		}
		return null;
	}
}
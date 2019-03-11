package com.remember.inhoeku.remember_test.domain.enumeration;


import lombok.Getter;

@Getter
public enum ORDER_STATE{
	READY(0),
	RESERVE(1),
	END(2);

	private int value;

	ORDER_STATE(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}

	public static ORDER_STATE getEnum(int val) {
		switch (val) {
			case 0:
				return ORDER_STATE.READY;
			case 1:
				return ORDER_STATE.RESERVE;
			case 2:
				return ORDER_STATE.END;
			default:
				return null;
		}
	}
}

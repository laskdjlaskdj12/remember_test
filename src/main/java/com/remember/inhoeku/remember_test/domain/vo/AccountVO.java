package com.remember.inhoeku.remember_test.domain.vo;

import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import lombok.Data;

@Data
public class AccountVO {
	private int PK;
	private String email;
	private String password;
	private ACCOUNT_TYPE accountType;
}

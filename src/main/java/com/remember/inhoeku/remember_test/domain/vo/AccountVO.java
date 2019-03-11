package com.remember.inhoeku.remember_test.domain.vo;

import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccountVO {

	@NotNull
	private Integer PK;

	@NotNull
	private String email;

	@NotNull
	private String password;

	@NotNull
	private ACCOUNT_TYPE accountType;
}

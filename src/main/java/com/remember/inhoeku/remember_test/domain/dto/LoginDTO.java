package com.remember.inhoeku.remember_test.domain.dto;

import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDTO {

	@NotNull
	private String Email;

	@NotNull
	private String password;

	@NotNull
	private ACCOUNT_TYPE accountType;

}

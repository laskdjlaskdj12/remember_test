package com.remember.inhoeku.remember_test.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ListDTO implements SessionDTO {

	@NotNull
	private Integer userPK;

	@NotNull
	private Integer accountType;

	@NotNull
	private String token;
}

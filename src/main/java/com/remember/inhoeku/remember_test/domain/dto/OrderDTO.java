package com.remember.inhoeku.remember_test.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderDTO implements SessionDTO {
	@NotNull
	private Integer userPK;

	@NotNull
	private String token;

	@NotNull
	private String departure;
}

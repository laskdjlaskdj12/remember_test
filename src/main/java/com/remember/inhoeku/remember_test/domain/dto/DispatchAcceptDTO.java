package com.remember.inhoeku.remember_test.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DispatchAcceptDTO implements SessionDTO{
	@NotNull
	Integer orderPK;

	@NotNull
	Integer driverPK;

	@NotNull
	String Token;
}

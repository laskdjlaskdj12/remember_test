package com.remember.inhoeku.remember_test.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class DispatchAcceptVO {

	@NotNull
	private Integer PK;

	@NotNull
	private Integer orderPK;

	@NotNull
	private Integer driverPK;

	@NotNull
	private Date acceptTime;
}

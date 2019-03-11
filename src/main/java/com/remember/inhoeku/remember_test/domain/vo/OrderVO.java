package com.remember.inhoeku.remember_test.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderVO {
	@NotNull
	private Integer PK;

	@NotNull
	private Integer userPK;

	@NotNull
	private String departure;

	//Todo: change to LocalTimeDate
	@NotNull
	private Date orderTime;
}

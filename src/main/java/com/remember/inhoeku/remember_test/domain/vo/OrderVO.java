package com.remember.inhoeku.remember_test.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderVO {
	@NotNull
	private int PK;

	@NotNull
	private int userPK;

	@NotNull
	private String departure;

	@NotNull
	private Date orderTime;
}

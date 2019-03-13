package com.remember.inhoeku.remember_test.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date orderTime;
}

package com.remember.inhoeku.remember_test.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TokenVO {

	@NotNull
	int PK;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	Date updateDate;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	Date expireDate;

	int clientPK;

	int driverPK;

	@NotNull
	String token;
}

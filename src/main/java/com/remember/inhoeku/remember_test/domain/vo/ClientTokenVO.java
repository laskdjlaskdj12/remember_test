package com.remember.inhoeku.remember_test.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ClientTokenVO {
	@NotNull
	Integer PK;

	//Todo: change to LocalTimeDate
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	Date updateDate;

	//Todo: change to LocalTimeDate
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	Date expireDate;

	Integer clientPK;

	@NotNull
	String token;
}

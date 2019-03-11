package com.remember.inhoeku.remember_test.domain.vo;

import com.remember.inhoeku.remember_test.domain.enumeration.ORDER_STATE;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
public class DispatchRequestVO {

	@NotNull
	private Integer PK;

	@NotNull
	private Integer orderPK;

	@Nullable
	private Integer dispatchPK;

	@NotNull
	private ORDER_STATE orderState;
}

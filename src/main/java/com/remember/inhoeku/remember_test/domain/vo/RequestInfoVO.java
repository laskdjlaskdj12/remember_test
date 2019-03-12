package com.remember.inhoeku.remember_test.domain.vo;

import com.remember.inhoeku.remember_test.domain.enumeration.ORDER_STATE;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestInfoVO {

	@NotNull
	private Integer PK;

	@NotNull
	private OrderVO requestOrder;

	@NotNull
	private ORDER_STATE orderState;

	@NotNull
	private DispatchAcceptVO dispatchAcceptVO;
}

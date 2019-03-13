package com.remember.inhoeku.remember_test.controller;

import com.remember.inhoeku.remember_test.domain.dto.OrderDTO;
import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import com.remember.inhoeku.remember_test.domain.error.BusinessException;
import com.remember.inhoeku.remember_test.domain.vo.DriverTokenVO;
import com.remember.inhoeku.remember_test.domain.vo.OrderVO;
import com.remember.inhoeku.remember_test.service.AccountService;
import com.remember.inhoeku.remember_test.service.OrderService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
@CrossOrigin("*")
public class ClientController {

	@Autowired
	OrderService orderService;

	@Autowired
	AccountService accountService;

	@ApiResponses(value={@ApiResponse(code = 200, message = "승객이 택시 배차 요청 Controller", response = DriverTokenVO.class),
			@ApiResponse(code = 400, message="만약 에러가 났을경우에는 \"ErrorType\" : String \n\"Error\" : String 타입으로 리턴합니다.")})
	@PostMapping("/order")
	public OrderVO orderTaxi(@RequestBody @Valid OrderDTO orderDTO){
		if(!accountService.isStillLogin(orderDTO.getToken(), orderDTO.getUserPK(), ACCOUNT_TYPE.PASSENGER)){
			throw new BusinessException("Login", "Token is Invalid");
		}

		return orderService.order(orderDTO);
	}
}

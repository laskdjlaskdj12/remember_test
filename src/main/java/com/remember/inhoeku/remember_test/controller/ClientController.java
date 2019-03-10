package com.remember.inhoeku.remember_test.controller;

import com.remember.inhoeku.remember_test.domain.dto.OrderDTO;
import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import com.remember.inhoeku.remember_test.domain.error.BusinessException;
import com.remember.inhoeku.remember_test.domain.vo.OrderVO;
import com.remember.inhoeku.remember_test.service.AccountService;
import com.remember.inhoeku.remember_test.service.OrderService;
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

	@PostMapping("/order/")
	public OrderVO orderTaxi(@RequestBody @Valid OrderDTO orderDTO){
		if(accountService.isStillLogin(orderDTO.getToken(), orderDTO.getUserPK(), ACCOUNT_TYPE.PASSENGER)){
			throw new BusinessException("Login", "Token is Invalid");
		}

		return orderService.order(orderDTO);
	}
}

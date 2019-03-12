package com.remember.inhoeku.remember_test.controller;

import com.remember.inhoeku.remember_test.domain.dto.DispatchAcceptDTO;
import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import com.remember.inhoeku.remember_test.domain.error.BusinessException;
import com.remember.inhoeku.remember_test.service.AccountService;
import com.remember.inhoeku.remember_test.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	AccountService accountService;

	@Autowired
	DispatchService dispatchService;

	@PostMapping("/dispatch/accept")
	public void dispatchAccept(@RequestBody @Valid DispatchAcceptDTO dispatchAcceptDTO){
		boolean isLogin = accountService.isStillLogin(dispatchAcceptDTO.getToken(), dispatchAcceptDTO.getDriverPK(), ACCOUNT_TYPE.DRIVER);
		if(!isLogin){
			throw new BusinessException("Login", "Token is Invalid");
		}

		dispatchService.accept(dispatchAcceptDTO);
	}
}

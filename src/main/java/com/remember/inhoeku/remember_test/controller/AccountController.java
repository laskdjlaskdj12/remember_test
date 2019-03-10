package com.remember.inhoeku.remember_test.controller;

import com.remember.inhoeku.remember_test.domain.dto.LoginDTO;
import com.remember.inhoeku.remember_test.domain.dto.RegisterDTO;
import com.remember.inhoeku.remember_test.domain.vo.ClientTokenVO;
import com.remember.inhoeku.remember_test.domain.vo.DriverTokenVO;
import com.remember.inhoeku.remember_test.domain.vo.TokenVO;
import com.remember.inhoeku.remember_test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;

	@PostMapping("/register/")
	public int register(@RequestBody @Valid RegisterDTO registerDTO){
		return accountService.register(registerDTO);
	}

	@PostMapping("/login/client")
	public ClientTokenVO loginByClient(@RequestBody @Valid LoginDTO loginDTO){
		TokenVO tokenVO = accountService.login(loginDTO);

		ClientTokenVO clientTokenVO = new ClientTokenVO();
		clientTokenVO.setClientPK(tokenVO.getClientPK());
		clientTokenVO.setPK(tokenVO.getPK());
		clientTokenVO.setExpireDate(tokenVO.getExpireDate());
		clientTokenVO.setToken(tokenVO.getToken());
		clientTokenVO.setUpdateDate(tokenVO.getUpdateDate());
		return clientTokenVO;
	}

	@PostMapping("/login/driver")
	public DriverTokenVO loginByDriver(@RequestBody @Valid LoginDTO loginDTO){
		TokenVO tokenVO = accountService.login(loginDTO);

		DriverTokenVO driverTokenVO = new DriverTokenVO();
		driverTokenVO.setDriverPK(tokenVO.getClientPK());
		driverTokenVO.setPK(tokenVO.getPK());
		driverTokenVO.setExpireDate(tokenVO.getExpireDate());
		driverTokenVO.setToken(tokenVO.getToken());
		driverTokenVO.setUpdateDate(tokenVO.getUpdateDate());
		return driverTokenVO;
	}
}

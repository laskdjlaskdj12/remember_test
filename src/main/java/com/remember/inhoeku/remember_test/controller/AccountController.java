package com.remember.inhoeku.remember_test.controller;

import com.remember.inhoeku.remember_test.domain.dto.LoginDTO;
import com.remember.inhoeku.remember_test.domain.dto.RegisterDTO;
import com.remember.inhoeku.remember_test.domain.vo.ClientTokenVO;
import com.remember.inhoeku.remember_test.domain.vo.DriverTokenVO;
import com.remember.inhoeku.remember_test.domain.vo.TokenVO;
import com.remember.inhoeku.remember_test.service.AccountService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@CrossOrigin("*")
public class AccountController {

	@Autowired
	AccountService accountService;

	@ApiResponses(value={@ApiResponse(code = 200, message = "등록한 유저 계정의 고유값을 리턴합니다.", response = Integer.class),
			@ApiResponse(code = 400, message="만약 에러가 났을경우에는 \"ErrorType\" : String \n\"Error\" : String 타입으로 리턴합니다.")})
	@PostMapping("/register")
	public int register(@RequestBody @Valid RegisterDTO registerDTO){
		return accountService.register(registerDTO);
	}

	@ApiResponses(value={@ApiResponse(code = 200, message = "승객의 로그인 유지 토큰 정보를 리턴합니다.", response = ClientTokenVO.class),
			@ApiResponse(code = 400, message="만약 에러가 났을경우에는 \"ErrorType\" : String \n\"Error\" : String 타입으로 리턴합니다.")})
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

	@ApiResponses(value={@ApiResponse(code = 200, message = "운전자의 로그인 유지 토큰 정보", response = DriverTokenVO.class),
			@ApiResponse(code = 400, message="만약 에러가 났을경우에는 \"ErrorType\" : String \n\"Error\" : String 타입으로 리턴합니다.")})
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

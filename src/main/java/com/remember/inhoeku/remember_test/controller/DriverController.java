package com.remember.inhoeku.remember_test.controller;

import com.remember.inhoeku.remember_test.domain.dto.DispatchAcceptDTO;
import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import com.remember.inhoeku.remember_test.domain.error.BusinessException;
import com.remember.inhoeku.remember_test.domain.vo.DispatchAcceptVO;
import com.remember.inhoeku.remember_test.service.AccountService;
import com.remember.inhoeku.remember_test.service.DispatchService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

	@ApiResponses(value={@ApiResponse(code = 200, message = "운전자가 승객 탑승 요청에 배차하기", response = DispatchAcceptVO.class),
			@ApiResponse(code = 400, message="만약 에러가 났을경우에는 \"ErrorType\" : String \n\"Error\" : String 타입으로 리턴합니다.")})
	@PostMapping("/dispatch/accept")
	public DispatchAcceptVO dispatchAccept(@RequestBody @Valid DispatchAcceptDTO dispatchAcceptDTO){
		boolean isLogin = accountService.isStillLogin(dispatchAcceptDTO.getToken(), dispatchAcceptDTO.getDriverPK(), ACCOUNT_TYPE.DRIVER);
		if(!isLogin){
			throw new BusinessException("Login", "Token is Invalid");
		}

		return dispatchService.accept(dispatchAcceptDTO);
	}
}

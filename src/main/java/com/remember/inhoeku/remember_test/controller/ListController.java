package com.remember.inhoeku.remember_test.controller;

import com.remember.inhoeku.remember_test.domain.dto.ListDTO;
import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import com.remember.inhoeku.remember_test.domain.error.BusinessException;
import com.remember.inhoeku.remember_test.domain.vo.RequestInfoVO;
import com.remember.inhoeku.remember_test.service.AccountService;
import com.remember.inhoeku.remember_test.service.ListService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/list")
public class ListController {

	@Autowired
	AccountService accountService;

	@Autowired
	ListService listService;

	@ApiResponses(value={@ApiResponse(code = 200, message = "배차 요청 목록 리스트", response = RequestInfoVO[].class),
			@ApiResponse(code = 400, message="만약 에러가 났을경우에는 \"ErrorType\" : String \n\"Error\" : String 타입으로 리턴합니다.")})
	@PostMapping("/request/all")
	public List<RequestInfoVO> getAllDispatchRequest(@RequestBody @Valid ListDTO listDTO){
		ACCOUNT_TYPE accountType = ACCOUNT_TYPE.fromValue(listDTO.getAccountType());
		String token = listDTO.getToken();
		Integer userPK = listDTO.getUserPK();

		if (!accountService.isStillLogin(token, userPK, accountType)){
			throw new BusinessException("Login", "Token is Invalid");
		}

		return listService.getAllRequestInfoList();
	}
}

package com.remember.inhoeku.remember_test.controller;

import com.remember.inhoeku.remember_test.domain.dto.ListDTO;
import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import com.remember.inhoeku.remember_test.domain.error.BusinessException;
import com.remember.inhoeku.remember_test.domain.vo.RequestInfoVO;
import com.remember.inhoeku.remember_test.service.AccountService;
import com.remember.inhoeku.remember_test.service.ListService;
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

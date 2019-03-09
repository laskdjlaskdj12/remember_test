package com.remember.inhoeku.remember_test.dao;

import com.remember.inhoeku.remember_test.domain.dto.RegisterDTO;
import com.remember.inhoeku.remember_test.domain.vo.AccountVO;

public interface AccountDAOInt {
	AccountVO getAccountByEmail(String email);
	int register(RegisterDTO registerDTO);
}
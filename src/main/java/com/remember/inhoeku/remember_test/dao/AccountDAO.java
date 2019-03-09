package com.remember.inhoeku.remember_test.dao;

import com.remember.inhoeku.remember_test.domain.dto.RegisterDTO;
import com.remember.inhoeku.remember_test.domain.vo.AccountVO;

public interface AccountDAO {
	AccountVO getAccountByEmail(String email);
	int register(RegisterDTO registerDTO);
}
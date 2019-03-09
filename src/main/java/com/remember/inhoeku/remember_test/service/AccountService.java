package com.remember.inhoeku.remember_test.service;

import com.remember.inhoeku.remember_test.dao.AccountDAOInt;
import com.remember.inhoeku.remember_test.domain.dto.RegisterDTO;
import com.remember.inhoeku.remember_test.domain.error.BusinessException;
import com.remember.inhoeku.remember_test.domain.vo.AccountVO;
import com.remember.inhoeku.remember_test.service.factory.AccountDAOFactory;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	@Autowired
	AccountDAOFactory accountDAOFactory;

	public AccountVO register(RegisterDTO registerDTO) {

		AccountDAOInt accountDAO = accountDAOFactory.getDAO(registerDTO.getAccountType());

		// 이메일 체크
		if (isEmailExsist(registerDTO)) {
			throw new BusinessException("Email is Exsist");
		}

		//패스워드 change
		String passwordHash = changeToHash(registerDTO.getPassword());
		registerDTO.setPassword(passwordHash);

		//register
		if (accountDAO.register(registerDTO) == 0) {
			throw new RuntimeException("Email registerFail");
		}

		return accountDAO.getAccountByEmail(registerDTO.getEmail());
	}

	private boolean isEmailExsist(RegisterDTO registerDTO) {
		AccountDAOInt accountDAO = accountDAOFactory.getDAO(registerDTO.getAccountType());

		AccountVO accountVO;
		accountVO = accountDAO.getAccountByEmail(registerDTO.getEmail());

		return accountVO != null;
	}

	private String changeToHash(String password){
		SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
		byte[] digest = digestSHA3.digest(password.getBytes());
		return Hex.toHexString(digest);
	}
}

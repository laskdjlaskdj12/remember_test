package com.remember.inhoeku.remember_test.service;

import com.remember.inhoeku.remember_test.dao.AccountDAO;
import com.remember.inhoeku.remember_test.domain.dto.LoginDTO;
import com.remember.inhoeku.remember_test.domain.dto.RegisterDTO;
import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import com.remember.inhoeku.remember_test.domain.error.BusinessException;
import com.remember.inhoeku.remember_test.domain.vo.AccountVO;
import com.remember.inhoeku.remember_test.domain.vo.TokenVO;
import com.remember.inhoeku.remember_test.service.factory.AccountDAOFactory;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Service
public class AccountService {

	@Autowired
	AccountDAOFactory accountDAOFactory;

	@Autowired
	TokenService tokenService;

	public int register(RegisterDTO registerDTO) {

		AccountDAO accountDAO = accountDAOFactory.getDAO(registerDTO.getAccountType());

		// 이메일 체크
		if (isEmailExist(registerDTO)) {
			throw new BusinessException("Eamil", "Email is Exist");
		}

		//패스워드 change
		String passwordHash = changeToHash(registerDTO.getPassword());
		registerDTO.setPassword(passwordHash);

		//register
		if (accountDAO.register(registerDTO) == 0) {
			throw new RuntimeException("Email register Fail");
		}

		AccountVO accountVO = accountDAO.getAccountByEmail(registerDTO.getEmail());

		if(accountVO == null){
			throw new RuntimeException("Can't find Reigster Account Email");
		}

		 return accountVO.getPK();
	}

	public TokenVO login(LoginDTO loginDTO) {
		AccountDAO accountDAO = accountDAOFactory.getDAO(loginDTO.getAccountType());

		if(!isLoginElementExist(loginDTO)){
			throw new BusinessException("Wrong Parameter", "login Element is not exist");
		}

		//Account가 있는지 확인
		AccountVO accountVO = accountDAO.getAccountByEmail(loginDTO.getEmail());

		if(!isAccountExist(accountVO)){
			throw new BusinessException("Account", "No Such Account");
		}

		if(!isPasswordSame(loginDTO, accountVO)){
			throw new BusinessException("Password", "Invalid Password");
		}

		//토큰이 있는지 체크
		TokenVO tokenVO = tokenService.getTokenByAccountPK(accountVO.getPK(), accountVO.getAccountType());

		if(isTokenExist(tokenVO)){
			//토큰을 삭제함
			tokenService.removeTokenByPK(tokenVO.getPK());
		}

		//토큰을 생성함
		String token = tokenService.makeToken(accountVO);

		//토큰을 저장함
		return tokenService.saveToken(accountVO, token);
	}

	public boolean isStillLogin(@NotNull String token, @NotNull int userPK, ACCOUNT_TYPE passenger) {
		TokenVO tokenVO = tokenService.getTokenByAccountPK(userPK, passenger);

		Date currentDate = new Date();
		Date expireDate = tokenVO.getExpireDate();

		if(currentDate.compareTo(expireDate) >= 0){
			return false;
		}

		return tokenVO.getToken().equals(token);
	}

	private boolean isLoginElementExist(LoginDTO loginDTO) {
		return loginDTO.getEmail() != null
				&& loginDTO.getPassword() != null
				&& loginDTO.getAccountType() != null;
	}

	private boolean isTokenExist(TokenVO tokenVO) {
		return tokenVO != null;
	}

	private boolean isPasswordSame(LoginDTO loginDTO, AccountVO accountVO) {
		String passwordHash = changeToHash(loginDTO.getPassword());
		return accountVO.getPassword().equals(passwordHash);
	}

	private boolean isAccountExist(AccountVO accountVO) {
		return accountVO != null;
	}

	private boolean isEmailExist(RegisterDTO registerDTO) {
		AccountDAO accountDAO = accountDAOFactory.getDAO(registerDTO.getAccountType());

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

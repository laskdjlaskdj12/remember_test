package com.remember.inhoeku.remember_test.Service;

import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import com.remember.inhoeku.remember_test.domain.vo.AccountVO;
import com.remember.inhoeku.remember_test.domain.vo.TokenVO;
import com.remember.inhoeku.remember_test.service.TokenService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenServiceTest {

	@Autowired
	TokenService tokenService;

	@Test
	public void makeTokenTest(){
		AccountVO accountVO = new AccountVO();
		accountVO.setPK(5);
		accountVO.setAccountType(ACCOUNT_TYPE.PASSENGER);
		String token = tokenService.makeToken(accountVO);

		boolean is_token_length_128 = token.length() == 128;

		Assert.assertTrue(is_token_length_128);
		System.out.println(token);
	}

	@Test
	public void saveClientToken(){
		//반드시 clientAccount에 계정정보(PK 등)가 있어야 테스트가 정상작동됨

		AccountVO accountVO = new AccountVO();
		accountVO.setPK(5);
		accountVO.setAccountType(ACCOUNT_TYPE.PASSENGER);
		String token = tokenService.makeToken(accountVO);

		TokenVO tokenVO = tokenService.saveToken(accountVO, token);

		Assert.assertNotNull(tokenVO);
	}

	@Test
	public void saveDriverToken(){
		//반드시 driverAccount에 계정정보(PK 등)가 있어야 테스트가 정상작동됨

		AccountVO accountVO = new AccountVO();
		accountVO.setPK(5);
		accountVO.setAccountType(ACCOUNT_TYPE.DRIVER);
		String token = tokenService.makeToken(accountVO);

		TokenVO tokenVO = tokenService.saveToken(accountVO, token);

		Assert.assertNotNull(tokenVO);
	}
}

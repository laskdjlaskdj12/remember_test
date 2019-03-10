package com.remember.inhoeku.remember_test.Service;

import com.remember.inhoeku.remember_test.domain.dto.LoginDTO;
import com.remember.inhoeku.remember_test.domain.dto.RegisterDTO;
import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import com.remember.inhoeku.remember_test.domain.error.BusinessException;
import com.remember.inhoeku.remember_test.domain.vo.TokenVO;
import com.remember.inhoeku.remember_test.service.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

	@Autowired
	AccountService accountService;

	@Test
	public void registerClientTest() {
		RegisterDTO clientRegisterDTO = new RegisterDTO();
		clientRegisterDTO.setEmail("laskdjlaskdj1234@naver.com");
		clientRegisterDTO.setAccountType(ACCOUNT_TYPE.PASSENGER);
		clientRegisterDTO.setPassword("password1234");

		int clientAccountPK = accountService.register(clientRegisterDTO);

		Assert.assertTrue(clientAccountPK > 0);
	}

	@Test
	public void registerDriverTest() {
		RegisterDTO driverRegisterDTO = new RegisterDTO();
		driverRegisterDTO.setEmail("laskdjlaskdj1234@naver.com");
		driverRegisterDTO.setAccountType(ACCOUNT_TYPE.DRIVER);
		driverRegisterDTO.setPassword("password1234");

		int driverAccountPK = accountService.register(driverRegisterDTO);

		Assert.assertTrue(driverAccountPK > 0);
	}

	@Test(expected = BusinessException.class)
	public void registerClientExsistFailTest() throws BusinessException{
		RegisterDTO clientRegisterDTO = new RegisterDTO();
		clientRegisterDTO.setEmail("laskdjlaskdj1234@naver.com");
		clientRegisterDTO.setAccountType(ACCOUNT_TYPE.PASSENGER);
		clientRegisterDTO.setPassword("password1234");

		int clientAccountPK = accountService.register(clientRegisterDTO);

		Assert.assertTrue(clientAccountPK > 0);
	}

	@Test(expected = BusinessException.class)
	public void registerDriverExsistFailTest() throws BusinessException{
		RegisterDTO driverRegisterDTO = new RegisterDTO();
		driverRegisterDTO.setEmail("laskdjlaskdj1234@naver.com");
		driverRegisterDTO.setAccountType(ACCOUNT_TYPE.DRIVER);
		driverRegisterDTO.setPassword("password1234");

		int driverAccountPK = accountService.register(driverRegisterDTO);

		Assert.assertTrue(driverAccountPK > 0);
	}

	@Test
	public void loginSuccessTest(){
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("laskdjlaskdj1234@naver.com");
		loginDTO.setAccountType(ACCOUNT_TYPE.PASSENGER);
		loginDTO.setPassword("password1234");

		TokenVO tokenVO = accountService.login(loginDTO);

		System.out.println(tokenVO);
		Assert.assertNotNull(tokenVO);
		Assert.assertTrue(tokenVO.getClientPK() > 0);
		Assert.assertTrue(!tokenVO.getToken().isEmpty());
		Assert.assertTrue(tokenVO.getUpdateDate().getTime() > 0);
		Assert.assertTrue(tokenVO.getExpireDate().getTime() > 0);
		Assert.assertTrue(tokenVO.getPK() > 0);
	}

}


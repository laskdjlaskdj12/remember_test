package com.remember.inhoeku.remember_test.Service;

import com.remember.inhoeku.remember_test.domain.dto.LoginDTO;
import com.remember.inhoeku.remember_test.domain.dto.RegisterDTO;
import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import com.remember.inhoeku.remember_test.domain.error.BusinessException;
import com.remember.inhoeku.remember_test.domain.vo.TokenVO;
import com.remember.inhoeku.remember_test.service.AccountService;
import com.remember.inhoeku.remember_test.service.TokenService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

	@Autowired
	AccountService accountService;

	@Autowired
	TokenService tokenService;

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
	public void registerClientExistFailTest() throws BusinessException{
		RegisterDTO clientRegisterDTO = new RegisterDTO();
		clientRegisterDTO.setEmail("laskdjlaskdj1234@naver.com");
		clientRegisterDTO.setAccountType(ACCOUNT_TYPE.PASSENGER);
		clientRegisterDTO.setPassword("password1234");

		int clientAccountPK = accountService.register(clientRegisterDTO);

		Assert.assertTrue(clientAccountPK > 0);
	}

	@Test(expected = BusinessException.class)
	public void registerDriverExistFailTest() throws BusinessException{
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

	@Test
	public void isStillLoginTest(){
		TokenVO tokenVO = tokenService.getTokenByAccountPK(6, ACCOUNT_TYPE.PASSENGER);
		Assert.assertNotNull(tokenVO);
		Assert.assertNotNull(tokenVO.getToken());

		System.out.println("Token : " + tokenVO.getToken());
		boolean is_still_login_true = accountService.isStillLogin(tokenVO.getToken(), tokenVO.getClientPK(), ACCOUNT_TYPE.PASSENGER);

		Assert.assertTrue(is_still_login_true);
	}

	@Test
	public void dateTimetest() throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = df.parse("2019-03-10 12:10:37");
		Date date1 = df.parse("2019-03-11 00:10:37");

		Assert.assertTrue(date1.compareTo(date) > 0);
	}

}


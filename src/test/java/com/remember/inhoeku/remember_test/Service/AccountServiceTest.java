package com.remember.inhoeku.remember_test.Service;

import com.remember.inhoeku.remember_test.domain.dto.RegisterDTO;
import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import com.remember.inhoeku.remember_test.domain.error.BusinessException;
import com.remember.inhoeku.remember_test.domain.vo.AccountVO;
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

		AccountVO clientAccountVO = accountService.register(clientRegisterDTO);

		Assert.assertNotNull(clientAccountVO);
		Assert.assertEquals(clientAccountVO.getEmail(), clientRegisterDTO.getEmail());
		Assert.assertEquals(clientAccountVO.getPassword(), clientRegisterDTO.getPassword());
		Assert.assertEquals(clientAccountVO.getAccountType(), clientRegisterDTO.getAccountType());
		Assert.assertTrue(clientAccountVO.getPK() > 0);
	}

	@Test
	public void registerDriverTest() {
		RegisterDTO clientRegisterDTO = new RegisterDTO();
		clientRegisterDTO.setEmail("laskdjlaskdj1234@naver.com");
		clientRegisterDTO.setAccountType(ACCOUNT_TYPE.DRIVER);
		clientRegisterDTO.setPassword("password1234");

		AccountVO driverAccountVO = accountService.register(clientRegisterDTO);

		Assert.assertNotNull(driverAccountVO);

		Assert.assertEquals(driverAccountVO.getEmail(), clientRegisterDTO.getEmail());
		Assert.assertEquals(driverAccountVO.getPassword(), clientRegisterDTO.getPassword());
		Assert.assertEquals(driverAccountVO.getAccountType(), clientRegisterDTO.getAccountType());
		Assert.assertTrue(driverAccountVO.getPK() > 0);
	}

	@Test(expected = BusinessException.class)
	public void registerClientFailTest() throws BusinessException{
		RegisterDTO clientRegisterDTO = new RegisterDTO();
		clientRegisterDTO.setEmail("laskdjlaskdj1234@naver.com");
		clientRegisterDTO.setAccountType(ACCOUNT_TYPE.PASSENGER);
		clientRegisterDTO.setPassword("password1234");

		AccountVO clientAccountVO = accountService.register(clientRegisterDTO);

		Assert.assertNull(clientAccountVO);
	}

	@Test(expected = BusinessException.class)
	public void registerDriverFailTest() throws BusinessException{
		RegisterDTO clientRegisterDTO = new RegisterDTO();
		clientRegisterDTO.setEmail("laskdjlaskdj1234@naver.com");
		clientRegisterDTO.setAccountType(ACCOUNT_TYPE.DRIVER);
		clientRegisterDTO.setPassword("password1234");

		AccountVO driverAccountVO = accountService.register(clientRegisterDTO);

		Assert.assertNull(driverAccountVO);
	}

}


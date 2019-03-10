package com.remember.inhoeku.remember_test.dao;

import com.remember.inhoeku.remember_test.domain.dto.RegisterDTO;
import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import com.remember.inhoeku.remember_test.domain.vo.AccountVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverAccountDAOTest {
	@Autowired
	DriverAccountDAO driverAccountDAO;

	@Test
	public void getAccountByEmailTest(){
		AccountVO accountVO = driverAccountDAO.getAccountByEmail("laskdjlaskdj12@gmail.com");
		AccountVO accountVO1 = driverAccountDAO.getAccountByEmail("laskdjlaskdj@naver.com");
		Assert.assertNull(accountVO);
		Assert.assertNotNull(accountVO1);
	}

	@Test
	public void registerTest(){
		RegisterDTO registerDTO = new RegisterDTO();
		registerDTO.setPassword("password");
		registerDTO.setAccountType(ACCOUNT_TYPE.PASSENGER);
		registerDTO.setEmail("laskdjlaskdj123@naver.com");

		int save_result_is_1 = driverAccountDAO.register(registerDTO);
		Assert.assertEquals( 1, save_result_is_1);
	}
}

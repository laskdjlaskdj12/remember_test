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
public class ClientAccountDAOTest {
	@Autowired
	ClientAccountDAO clientAccountDAO;

	@Test
	public void getAccountByEmailTest(){
		AccountVO accountVO = clientAccountDAO.getAccountByEmail("laskdjlaskdj12@gmail.com");
		AccountVO accountVO1 = clientAccountDAO.getAccountByEmail("laskdjlaskdj@naver.com");
		Assert.assertNull(accountVO);
		Assert.assertNotNull(accountVO1);
	}

	@Test
	public void registerTest(){
		RegisterDTO registerDTO = new RegisterDTO();
		registerDTO.setPassword("password");
		registerDTO.setAccountType(ACCOUNT_TYPE.PASSENGER);
		registerDTO.setEmail("laskdjlaskdj123@naver.com");

		int save_result_is_1 = clientAccountDAO.register(registerDTO);
		Assert.assertEquals( 1, save_result_is_1);
	}

}

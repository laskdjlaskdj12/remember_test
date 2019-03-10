package com.remember.inhoeku.remember_test.domain;

import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTypeTest {

	@Test
	public void AccountTypeTest(){
		ACCOUNT_TYPE account_type = ACCOUNT_TYPE.DRIVER;

		Assert.assertTrue(account_type.getValue() == 1);
		Assert.assertTrue(ACCOUNT_TYPE.DRIVER.getValue() == 1);
	}
}

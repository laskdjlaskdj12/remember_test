package com.remember.inhoeku.remember_test.dao;

import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import com.remember.inhoeku.remember_test.domain.vo.AccountVO;
import com.remember.inhoeku.remember_test.domain.vo.TokenVO;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenDAOTest {

	@Autowired
	TokenDAO tokenDAO;

	@Test
	public void insertTokenTest(){
		//반드시 Token Table에서 지우고 테스트를 할것
		int testAccountPK = 6;
		AccountVO accountVO = new AccountVO();
		accountVO.setPK(testAccountPK);
		accountVO.setAccountType(ACCOUNT_TYPE.PASSENGER);

		String token = changeToHash("laskdjlaskdj1234");
		String startDate = "2019-03-10 10:00:00";
		String endDate = "2019-03-10 22:00:00";

		boolean return_1_true = tokenDAO.insertToken(accountVO, token, startDate, endDate) == 1;

		Assert.assertTrue(return_1_true);
	}

	@Test
	public void getTokenByAccountPK(){
		//반드시 Account PK가 clientAccount 테이블에 있어야할것
		int testAccountPK = 6;

		AccountVO accountVO = new AccountVO();
		accountVO.setPK(testAccountPK);
		accountVO.setAccountType(ACCOUNT_TYPE.PASSENGER);

		TokenVO tokenVO = tokenDAO.getTokenByAccountPK(5, accountVO.getAccountType());
		Assert.assertNotNull(tokenVO);

		boolean clinet_pk_same_true = accountVO.getPK() == tokenVO.getClientPK();
		boolean hash_is_exsist_true = !tokenVO.getToken().isEmpty();
		boolean startDate_is_exsist_true = tokenVO.getUpdateDate().getTime() > 0;
		boolean expireDate_is_exsist_true = tokenVO.getExpireDate().getTime() > 0;


		Assert.assertTrue(clinet_pk_same_true);
		Assert.assertTrue(hash_is_exsist_true);
		Assert.assertTrue(startDate_is_exsist_true);
		Assert.assertTrue(expireDate_is_exsist_true);
	}

	@Test
	public void getTokenNotExsistPK() {
		//반드시 Account PK가 clientAccount 테이블에 없어야할것

		int testAccountPK = 4;

		AccountVO accountVO = new AccountVO();
		accountVO.setPK(testAccountPK);
		accountVO.setAccountType(ACCOUNT_TYPE.PASSENGER);

		TokenVO tokenVO = tokenDAO.getTokenByAccountPK(accountVO.getPK(), accountVO.getAccountType());
		Assert.assertNull(tokenVO);
	}

	@Test
	public void deleteTokenByPK(){
		//반드시 Account PK가 clientAccount 테이블에 있어야할것
		int tokenPK = 5;

		boolean delete_success_true = tokenDAO.removeTokenByPK(tokenPK) == 1;
		Assert.assertTrue(delete_success_true);
	}

	private String changeToHash(String password){
		SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
		byte[] digest = digestSHA3.digest(password.getBytes());
		return Hex.toHexString(digest);
	}
}

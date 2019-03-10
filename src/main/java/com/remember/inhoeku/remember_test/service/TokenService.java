package com.remember.inhoeku.remember_test.service;

import com.remember.inhoeku.remember_test.dao.TokenDAO;
import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import com.remember.inhoeku.remember_test.domain.vo.AccountVO;
import com.remember.inhoeku.remember_test.domain.vo.TokenVO;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {
	@Autowired
	private TokenDAO tokenDAO;

	public String makeToken(AccountVO accountVO){
		String PK = String.valueOf(accountVO.getPK());
		String time = currentTimeString();
		String accountType = String.valueOf(accountVO.getAccountType().getValue());

		String hashBaseString = PK + time + accountType;
		return changeToHash(hashBaseString);
	}

	public TokenVO saveToken(AccountVO accountVO, String token) {
		Date date = new Date();
		Date expireDate = makeExpiredate(date);

		String currentDateString = currentTimeString();
		String expireDateString = expireTimeString(expireDate);

		tokenDAO.insertToken(accountVO, token, currentDateString, expireDateString);

		return tokenDAO.getTokenByAccountPK(accountVO.getPK(), accountVO.getAccountType());
	}

	public TokenVO getTokenByAccountPK(int pk, ACCOUNT_TYPE accountType) {
		return tokenDAO.getTokenByAccountPK(pk, accountType);
	}

	public int removeTokenByPK(int pk) {
		return tokenDAO.removeTokenByPK(pk);
	}

	private String currentTimeString(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}

	private String expireTimeString(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	private String changeToHash(String password){
		SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
		byte[] digest = digestSHA3.digest(password.getBytes());
		return Hex.toHexString(digest);
	}

	private Date makeExpiredate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, 12);
		return cal.getTime();
	}
}

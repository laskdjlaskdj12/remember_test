package com.remember.inhoeku.remember_test.dao;

import com.remember.inhoeku.remember_test.dao.Mapper.TokenMapper;
import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import com.remember.inhoeku.remember_test.domain.vo.AccountVO;
import com.remember.inhoeku.remember_test.domain.vo.TokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TokenDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insertToken(AccountVO accountVO, String token, String currentDateString, String expireDateString) {
		String sql;
		if(accountVO.getAccountType() == ACCOUNT_TYPE.PASSENGER) {
			sql = "INSERT INTO token(token, updateDate, expireDate, clientPK) VALUES (?, ?, ?, ?)";
		}else{
			sql = "INSERT INTO token(token, updateDate, expireDate, driverPK) VALUES (?, ?, ?, ?)";
		}

		return jdbcTemplate.update(sql, token, currentDateString, expireDateString, accountVO.getPK());
	}

	@Nullable
	public TokenVO getTokenByAccountPK(int accountPk, ACCOUNT_TYPE accountType) {
		String sql;
		if(accountType == ACCOUNT_TYPE.PASSENGER){
			sql = "SELECT * FROM token where clientPK = ?";
		}else{
			sql = "SELECT * FROM token where driverPK = ?";
		}

		List<TokenVO> tokenVOList = jdbcTemplate.query(sql, new TokenMapper(), accountPk);

		if(tokenVOList.isEmpty()){
			return null;
		}
		return tokenVOList.get(0);
	}
}

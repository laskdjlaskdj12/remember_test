package com.remember.inhoeku.remember_test.dao.Mapper;

import com.remember.inhoeku.remember_test.domain.enumeration.ACCOUNT_TYPE;
import com.remember.inhoeku.remember_test.domain.vo.AccountVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverAccountMapper implements RowMapper<AccountVO> {
	@Override
	public AccountVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		AccountVO accountVO = new AccountVO();
		accountVO.setPK(rs.getInt("PK"));
		accountVO.setEmail(rs.getString("email"));
		accountVO.setPassword(rs.getString("password"));
		accountVO.setAccountType(ACCOUNT_TYPE.DRIVER);
		return accountVO;
	}
}

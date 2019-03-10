package com.remember.inhoeku.remember_test.dao.Mapper;

import com.remember.inhoeku.remember_test.domain.vo.TokenVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenMapper implements RowMapper<TokenVO> {

	@Override
	public TokenVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		TokenVO tokenVO = new TokenVO();
		tokenVO.setPK(rs.getInt("PK"));
		tokenVO.setUpdateDate(rs.getTimestamp("updateDate"));
		tokenVO.setExpireDate(rs.getTimestamp("expireDate"));
		tokenVO.setToken(rs.getString("token"));
		tokenVO.setClientPK(rs.getInt("clientPK"));
		tokenVO.setDriverPK(rs.getInt("driverPK"));
		return tokenVO;
	}
}

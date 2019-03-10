package com.remember.inhoeku.remember_test.dao.Mapper;

import com.remember.inhoeku.remember_test.domain.vo.OrderVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<OrderVO> {
	@Override
	public OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderVO orderVO = new OrderVO();
		orderVO.setPK(rs.getInt("PK"));
		orderVO.setUserPK(rs.getInt("userPK"));
		orderVO.setDeparture(rs.getString("departure"));
		orderVO.setOrderTime(rs.getTimestamp("orderTime"));
		return orderVO;
	}
}

package com.remember.inhoeku.remember_test.dao.Mapper;

import com.remember.inhoeku.remember_test.domain.vo.DispatchAcceptVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DispatchAcceptMapper implements RowMapper<DispatchAcceptVO> {

	@Override
	public DispatchAcceptVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DispatchAcceptVO dispatchAcceptVO = new DispatchAcceptVO();
		dispatchAcceptVO.setPK(rs.getInt("PK"));
		dispatchAcceptVO.setAcceptTime(rs.getTimestamp("acceptTime"));
		dispatchAcceptVO.setDriverPK(rs.getInt("driverPK"));
		dispatchAcceptVO.setOrderPK(rs.getInt("orderPK"));
		return dispatchAcceptVO;
	}
}

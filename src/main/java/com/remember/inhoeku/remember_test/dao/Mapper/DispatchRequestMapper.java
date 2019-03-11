package com.remember.inhoeku.remember_test.dao.Mapper;

import com.remember.inhoeku.remember_test.domain.enumeration.ORDER_STATE;
import com.remember.inhoeku.remember_test.domain.vo.DispatchRequestVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DispatchRequestMapper implements RowMapper<DispatchRequestVO>{
	@Override
	public DispatchRequestVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DispatchRequestVO dispatchRequestVO = new DispatchRequestVO();

		dispatchRequestVO.setPK(rs.getInt("PK"));
		dispatchRequestVO.setOrderPK(rs.getInt("requestOrderPK"));
		dispatchRequestVO.setDispatchPK(rs.getInt("dispatchPK"));
		ORDER_STATE orderState = ORDER_STATE.getEnum(rs.getInt("orderState"));
		dispatchRequestVO.setOrderState(orderState);
		return dispatchRequestVO;
	}
}

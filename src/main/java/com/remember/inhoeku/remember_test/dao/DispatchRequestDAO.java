package com.remember.inhoeku.remember_test.dao;

import com.remember.inhoeku.remember_test.dao.Mapper.DispatchRequestMapper;
import com.remember.inhoeku.remember_test.domain.vo.DispatchRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DispatchRequestDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insertDispathRequest(DispatchRequestVO dispatchReqeustVO){
		String sql = "INSERT INTO dispatchRequest(requestOrderPK, orderState) VALUES (?,?)";
		return jdbcTemplate.update(sql, dispatchReqeustVO.getOrderPK(), dispatchReqeustVO.getOrderState().getValue());
	}

	public int updateDispatchRequest(DispatchRequestVO dispatchReqeustVO){
		String sql = "UPDATE dispatchRequest SET requestOrderPK = ?, orderState = ?, dispatchPK = ? WHERE PK = ?";
		return jdbcTemplate.update(sql, dispatchReqeustVO.getOrderPK(), dispatchReqeustVO.getOrderState().getValue(), dispatchReqeustVO.getDispatchPK(), dispatchReqeustVO.getPK());
	}

	@Nullable
	public DispatchRequestVO getRequestOrderByOrderPK(int orderPK) {
		String sql = "SELECT * FROM dispatchRequest WHERE requestOrderPK = ?";
		List<DispatchRequestVO> dispatchRequestVOS = jdbcTemplate.query(sql, new DispatchRequestMapper(), orderPK);

		if(dispatchRequestVOS.isEmpty()){
			return null;
		}
		return dispatchRequestVOS.get(0);
	}
}

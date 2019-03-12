package com.remember.inhoeku.remember_test.dao;

import com.remember.inhoeku.remember_test.dao.Mapper.DispatchAcceptMapper;
import com.remember.inhoeku.remember_test.domain.dto.DispatchAcceptDTO;
import com.remember.inhoeku.remember_test.domain.vo.DispatchAcceptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DispatchAcceptDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insertDispatchAccept(DispatchAcceptDTO dispatchAcceptDTO, String dateString) {
		String sql = "INSERT INTO dispatchAccept(driverPK, orderPK, acceptTime) VALUES (?, ?, ?)";
		return jdbcTemplate.update(sql, dispatchAcceptDTO.getDriverPK(), dispatchAcceptDTO.getOrderPK(), dateString);
	}

	@Nullable
	public DispatchAcceptVO getDispatchAcceptByOrderPK(Integer orderPK) {
		String sql = "SELECT * FROM dispatchAccept WHERE orderPK = ?";
		List<DispatchAcceptVO> dispatchAcceptVOList = jdbcTemplate.query(sql, new DispatchAcceptMapper(), orderPK);

		if(dispatchAcceptVOList.isEmpty()){
			return null;
		}

		return dispatchAcceptVOList.get(0);
	}
}

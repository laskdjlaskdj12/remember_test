package com.remember.inhoeku.remember_test.dao;

import com.remember.inhoeku.remember_test.dao.Mapper.OrderMapper;
import com.remember.inhoeku.remember_test.domain.dto.OrderDTO;
import com.remember.inhoeku.remember_test.domain.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insertOrder(OrderDTO orderDTO, String date){
		String sql = "INSERT INTO requestOrder(departure, orderTime, userPK) VALUES (?, ?, ?)";
		return jdbcTemplate.update(sql, orderDTO.getDeparture(), date, orderDTO.getUserPK());
	}

	public OrderVO getOrderByPK(int PK){
		String sql = "SELECT * FROM requestOrder where PK = ?";
		List<OrderVO> orderVOS = jdbcTemplate.query(sql, new OrderMapper(), PK);

		if(orderVOS.isEmpty()){
			return null;
		}

		return orderVOS.get(0);
	}

	public List<OrderVO> getOrderListByUserPK(int userPK) {
		String sql = "SELECT * FROM requestOrder where userPK = ? ORDER by PK DESC";
		return jdbcTemplate.query(sql, new OrderMapper(), userPK);
	}
}

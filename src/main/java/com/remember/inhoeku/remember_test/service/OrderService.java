package com.remember.inhoeku.remember_test.service;

import com.remember.inhoeku.remember_test.dao.OrderDAO;
import com.remember.inhoeku.remember_test.domain.dto.OrderDTO;
import com.remember.inhoeku.remember_test.domain.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

	@Autowired
	OrderDAO orderDAO;

	public OrderVO order(OrderDTO orderDTO) {
		Date date = new Date();
		String currentDateString = dateToString(date);

		if(orderDAO.insertOrder(orderDTO, currentDateString) != 1){
			throw new RuntimeException("insert Order Fail");
		}

		List<OrderVO> orderVOList = orderDAO.getOrderListByUserPK(orderDTO.getUserPK());

		if(orderVOList.isEmpty()){
			return null;
		}

		return orderVOList.get(0);
	}

	private String dateToString(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
}
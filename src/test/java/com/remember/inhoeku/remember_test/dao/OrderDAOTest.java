package com.remember.inhoeku.remember_test.dao;

import com.remember.inhoeku.remember_test.domain.dto.OrderDTO;
import com.remember.inhoeku.remember_test.domain.vo.OrderVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDAOTest {
	@Autowired
	OrderDAO orderDAO;

	@Test
	public void makeOrderVOList(){
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setDeparture("서울시 노원구 공릉동");
		orderDTO.setUserPK(6);

		String dateFormat = getDate(new Date());
		int result = orderDAO.insertOrder(orderDTO, dateFormat);

		boolean result_is_one_true = result == 1;

		Assert.assertTrue(result_is_one_true);
	}

	@Test
	public void getOrderVOList(){
		int userPK = 6;
		List<OrderVO> orderVOList = orderDAO.getOrderListByUserPK(userPK);

		boolean orderVOList_is_not_empty_true = orderVOList.size() > 0;
		Assert.assertTrue(orderVOList_is_not_empty_true);
	}

	private String getDate(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
}

package com.remember.inhoeku.remember_test.Service;

import com.remember.inhoeku.remember_test.domain.dto.OrderDTO;
import com.remember.inhoeku.remember_test.domain.error.BusinessException;
import com.remember.inhoeku.remember_test.domain.vo.OrderVO;
import com.remember.inhoeku.remember_test.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
	@Autowired
	OrderService orderService;

	@Test
	public void orderTest(){
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setDeparture("서울시 노원구 공릉동 134");
		orderDTO.setUserPK(6);
		OrderVO orderVO = orderService.order(orderDTO);

		Assert.assertNotNull(orderVO);
		Assert.assertEquals(4, orderVO.getPK().intValue());
	}

	@Test(expected = BusinessException.class)
	public void overOrderDepartureTextTest() throws BusinessException {
		String orderString = "서울시 노원구 공릉동 서울시 노원구 공릉동 서울시 노원구 공릉동 서울시 노원구 공릉동 서울시 노원구 공릉동 서울시 노원구 공릉동 서울시 노원구 공릉동 서울시 노원구 공릉동 서울시 노원구 공릉동 서울시 노원구 공릉동 서울시 노원구 공릉동 서울시 노원구 공릉동";
		System.out.println("orderStringSize : " + orderString.length());

		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setDeparture(orderString);

		orderService.order(orderDTO);
	}
}
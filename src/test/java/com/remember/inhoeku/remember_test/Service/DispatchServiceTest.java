package com.remember.inhoeku.remember_test.Service;

import com.remember.inhoeku.remember_test.dao.OrderDAO;
import com.remember.inhoeku.remember_test.domain.dto.DispatchAcceptDTO;
import com.remember.inhoeku.remember_test.domain.vo.DispatchAcceptVO;
import com.remember.inhoeku.remember_test.domain.vo.OrderVO;
import com.remember.inhoeku.remember_test.service.DispatchService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DispatchServiceTest {

	@Autowired
	OrderDAO orderDAO;

	@Autowired
	DispatchService dispatchService;

	@Test
	public void orderDispatchTest(){
		OrderVO orderVOMock = makeOrderVOMock();
		OrderVO orderVO = dispatchService.request(orderVOMock);
		Assert.assertNotNull(orderVO);
	}

	@Test
	public void dispatchAcceptTest(){
		DispatchAcceptDTO dispatchAcceptDTO = makeDispatchAcceptDTOMock();

		DispatchAcceptVO dispatchAcceptVO = dispatchService.accept(dispatchAcceptDTO);

		boolean is_dispatchAccept_not_null_true = dispatchAcceptVO != null;

		Assert.assertTrue(is_dispatchAccept_not_null_true);
	}

	private DispatchAcceptDTO makeDispatchAcceptDTOMock() {
		DispatchAcceptDTO dispatchAcceptDTO = new DispatchAcceptDTO();
		dispatchAcceptDTO.setOrderPK(9);
		dispatchAcceptDTO.setDriverPK(6);
		return dispatchAcceptDTO;
	}

	private OrderVO makeOrderVOMock(){
		return orderDAO.getOrderByPK(9);
	}
}

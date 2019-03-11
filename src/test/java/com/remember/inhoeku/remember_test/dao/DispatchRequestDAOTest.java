package com.remember.inhoeku.remember_test.dao;

import com.remember.inhoeku.remember_test.domain.enumeration.ORDER_STATE;
import com.remember.inhoeku.remember_test.domain.vo.DispatchRequestVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DispatchRequestDAOTest {

	@Autowired
	private DispatchRequestDAO dispatchRequestDAO;

	@Test
	public void insertDispatchRequestTest(){
		//반드시 DispatchRequest 테이블에 orderPK 가 겹치지 않게 할것
		int orderPK = 7;
		DispatchRequestVO dispatchRequestVO = makeReadyOrderMock(orderPK);
		int insertDispatchRequest = dispatchRequestDAO.insertDispathRequest(dispatchRequestVO);

		boolean is_insertDispatchRequest_true = insertDispatchRequest > 0;

		Assert.assertTrue(is_insertDispatchRequest_true);
	}

	@Test
	public void updateDispatchRequestTest(){
		int dispatchRequestPK = 1;
		int orderPK = 6;
		int dispatchPK = 4;

		DispatchRequestVO dispatchRequestVO = makeUpdateDispatchRequestMock(dispatchRequestPK, orderPK, dispatchPK);
		int updateDispatchRequest = dispatchRequestDAO.updateDispatchRequest(dispatchRequestVO);

		boolean is_update_dispatch_request_true = updateDispatchRequest > 0;

		Assert.assertTrue(is_update_dispatch_request_true);
	}

	@Test
	public void getDispatchRequestByRequestOrderPKTest(){
		int orderPK = 6;

		DispatchRequestVO dispatchRequestVO = dispatchRequestDAO.getRequestOrderByOrderPK(orderPK);
		boolean is_dispatch_requestVO_not_null_true = dispatchRequestVO != null;
		boolean is_dispatch_request_PK_is_same = dispatchRequestVO.getPK() == 1;
		boolean is_dispatch_request_orderPK_is_same_true = dispatchRequestVO.getOrderPK() == 6;
		boolean is_dispatch_request_state_is_same_true = dispatchRequestVO.getOrderState() == ORDER_STATE.RESERVE;
		boolean is_dispatch_request_dispatch_pk_is_same_true = dispatchRequestVO.getDispatchPK() == 4;

		Assert.assertTrue(is_dispatch_requestVO_not_null_true);
		Assert.assertTrue(is_dispatch_request_PK_is_same);
		Assert.assertTrue(is_dispatch_request_orderPK_is_same_true);
		Assert.assertTrue(is_dispatch_request_state_is_same_true);
		Assert.assertTrue(is_dispatch_request_dispatch_pk_is_same_true);
	}

	private DispatchRequestVO makeUpdateDispatchRequestMock(int dispatchRequestPK, int orderPK, int dispatchPK) {
		DispatchRequestVO dispatchRequestVO = new DispatchRequestVO();
		dispatchRequestVO.setPK(dispatchRequestPK);
		dispatchRequestVO.setOrderPK(orderPK);
		dispatchRequestVO.setOrderState(ORDER_STATE.RESERVE);
		dispatchRequestVO.setDispatchPK(dispatchPK);
		return dispatchRequestVO;
	}

	private DispatchRequestVO makeReadyOrderMock(int orderPK){
		DispatchRequestVO dispatchRequestVO = new DispatchRequestVO();
		dispatchRequestVO.setOrderState(ORDER_STATE.READY);
		dispatchRequestVO.setOrderPK(orderPK);

		return dispatchRequestVO;
	}
}

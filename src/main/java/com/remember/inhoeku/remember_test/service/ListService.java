package com.remember.inhoeku.remember_test.service;

import com.remember.inhoeku.remember_test.dao.DispatchAcceptDAO;
import com.remember.inhoeku.remember_test.dao.DispatchRequestDAO;
import com.remember.inhoeku.remember_test.dao.OrderDAO;
import com.remember.inhoeku.remember_test.domain.enumeration.ORDER_STATE;
import com.remember.inhoeku.remember_test.domain.vo.DispatchAcceptVO;
import com.remember.inhoeku.remember_test.domain.vo.DispatchRequestVO;
import com.remember.inhoeku.remember_test.domain.vo.OrderVO;
import com.remember.inhoeku.remember_test.domain.vo.RequestInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListService {

	@Autowired
	DispatchRequestDAO dispatchRequestDAO;

	@Autowired
	DispatchAcceptDAO dispatchAcceptDAO;

	@Autowired
	OrderDAO orderDAO;

	@Autowired
	OrderService orderService;

	public List<DispatchRequestVO> getAllDispatchRequestList() {
		return dispatchRequestDAO.getAllDispatchRequest();
	}

	public List<RequestInfoVO> getAllRequestInfoList(){
		List<DispatchRequestVO> dispatchRequestList = this.getAllDispatchRequestList();
		return dispatchRequestList.stream().map(this::toRequestInfo)
				.collect(Collectors.toList());
	}

	private RequestInfoVO toRequestInfo(DispatchRequestVO dispatchRequestVO) {
		RequestInfoVO requestInfoVO = new RequestInfoVO();

		Integer PK = dispatchRequestVO.getPK();
		ORDER_STATE orderState = dispatchRequestVO.getOrderState();
		OrderVO orderVO = loadOrderVO(dispatchRequestVO);
		DispatchAcceptVO dispatchAcceptVO = loadDispatchAcceptVO(dispatchRequestVO);

		requestInfoVO.setOrderState(orderState);
		requestInfoVO.setPK(PK);
		requestInfoVO.setRequestOrder(orderVO);
		requestInfoVO.setDispatchAcceptVO(dispatchAcceptVO);

		return requestInfoVO;
	}

	@Nullable
	private DispatchAcceptVO loadDispatchAcceptVO(DispatchRequestVO dispatchRequestVO) {
		Integer dispatchAcceptPK = dispatchRequestVO.getDispatchPK();
		DispatchAcceptVO dispatchAcceptVO = null;

		if(dispatchAcceptPK == -1){
			return dispatchAcceptVO;
		}

		dispatchAcceptVO = dispatchAcceptDAO.getDispatchAcceptByPK(dispatchAcceptPK);

		if(dispatchAcceptVO == null){
			throw new RuntimeException("Can't find Dispatch dispatchAccept VO");
		}

		return dispatchAcceptVO;
	}

	private OrderVO loadOrderVO(DispatchRequestVO dispatchRequestVO) {
		Integer orderPK = dispatchRequestVO.getOrderPK();
		return orderDAO.getOrderByPK(orderPK);
	}
}

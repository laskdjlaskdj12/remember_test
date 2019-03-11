package com.remember.inhoeku.remember_test.service;

import com.remember.inhoeku.remember_test.dao.DispatchRequestDAO;
import com.remember.inhoeku.remember_test.dao.OrderDAO;
import com.remember.inhoeku.remember_test.domain.dto.OrderDTO;
import com.remember.inhoeku.remember_test.domain.enumeration.ORDER_STATE;
import com.remember.inhoeku.remember_test.domain.error.BusinessException;
import com.remember.inhoeku.remember_test.domain.vo.DispatchRequestVO;
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

	@Autowired
	DispatchRequestDAO dispatchRequestDAO;

	public OrderVO order(OrderDTO orderDTO) {
		String depatureString = orderDTO.getDeparture();

		if(depatureString.length() > 100) {
			throw new BusinessException("departure", "departure letter is over 100");
		}

		Date date = new Date();
		String currentDateString = dateToString(date);

		if(orderDAO.insertOrder(orderDTO, currentDateString) != 1){
			throw new RuntimeException("insert Order Fail");
		}

		List<OrderVO> orderVOList = orderDAO.getOrderListByUserPK(orderDTO.getUserPK());

		if(orderVOList.isEmpty()){
			return null;
		}

		OrderVO orderVO = orderVOList.get(0);

		//DispatchRequstList에 넣음
		DispatchRequestVO dispatchRequestVO = makeDispatchRequest(orderVO);
		int insertComplete = dispatchRequestDAO.insertDispathRequest(dispatchRequestVO);

		if(isDispatchRequestInsertSuccess(insertComplete)){
			throw new RuntimeException("insert dispatch Request Fail");
		}

		return orderVO;
	}

	private boolean isDispatchRequestInsertSuccess(int insertComplete) {
		return insertComplete > 0;
	}

	private DispatchRequestVO makeDispatchRequest(OrderVO orderVO) {
		DispatchRequestVO dispatchRequestVO = new DispatchRequestVO();
		dispatchRequestVO.setOrderPK(orderVO.getPK());
		dispatchRequestVO.setOrderState(ORDER_STATE.READY);
		return dispatchRequestVO;
	}

	private String dateToString(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
}
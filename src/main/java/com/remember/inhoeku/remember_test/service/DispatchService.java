package com.remember.inhoeku.remember_test.service;

import com.remember.inhoeku.remember_test.dao.DispatchAcceptDAO;
import com.remember.inhoeku.remember_test.dao.DispatchRequestDAO;
import com.remember.inhoeku.remember_test.domain.dto.DispatchAcceptDTO;
import com.remember.inhoeku.remember_test.domain.enumeration.ORDER_STATE;
import com.remember.inhoeku.remember_test.domain.vo.DispatchAcceptVO;
import com.remember.inhoeku.remember_test.domain.vo.DispatchRequestVO;
import com.remember.inhoeku.remember_test.domain.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DispatchService {

	@Autowired
	DispatchAcceptDAO dispatchAcceptDAO;

	@Autowired
	DispatchRequestDAO dispatchRequestDAO;

	public OrderVO request(OrderVO orderVO){
		//DispatchRequstList에 넣음
		DispatchRequestVO dispatchRequestVO = makeDispatchRequest(orderVO);
		int insertComplete = dispatchRequestDAO.insertDispathRequest(dispatchRequestVO);

		if(!isDispatchRequestInsertSuccess(insertComplete)){
			throw new RuntimeException("insert dispatch Request Fail");
		}

		return orderVO;
	}

	public int accept(DispatchAcceptDTO dispatchAcceptDTO) {
		String dateString = makeDateString(new Date());
		int insertResult = dispatchAcceptDAO.insertDispatchAccept(dispatchAcceptDTO, dateString);

		if(insertResult == 0){
			throw new RuntimeException("Fail to insert DispatchAccepct");
		}

		DispatchAcceptVO dispatchAcceptVO = dispatchAcceptDAO.getDispatchAcceptByOrderPK(dispatchAcceptDTO.getOrderPK());
		DispatchRequestVO dispatchRequestVO = matchToDispatchRequest(dispatchAcceptVO);
		return dispatchRequestDAO.updateDispatchRequest(dispatchRequestVO);
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

	private DispatchRequestVO matchToDispatchRequest(DispatchAcceptVO dispatchAcceptVO) {
		DispatchRequestVO dispatchRequestVO = dispatchRequestDAO.getDispatchRequestByOrderPK(dispatchAcceptVO.getOrderPK());
		dispatchRequestVO.setDispatchPK(dispatchAcceptVO.getPK());
		dispatchRequestVO.setOrderState(ORDER_STATE.RESERVE);
		return dispatchRequestVO;
	}

	private String makeDateString(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
}

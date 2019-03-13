package com.remember.inhoeku.remember_test.service;

import com.remember.inhoeku.remember_test.dao.DispatchAcceptDAO;
import com.remember.inhoeku.remember_test.dao.DispatchRequestDAO;
import com.remember.inhoeku.remember_test.domain.dto.DispatchAcceptDTO;
import com.remember.inhoeku.remember_test.domain.enumeration.ORDER_STATE;
import com.remember.inhoeku.remember_test.domain.error.BusinessException;
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
		//DispatchRequst에 넣음
		DispatchRequestVO dispatchRequestVO = makeDispatchRequest(orderVO);
		int insertComplete = dispatchRequestDAO.insertDispathRequest(dispatchRequestVO);

		if(!isDispatchRequestInsertSuccess(insertComplete)){
			throw new RuntimeException("insert dispatch Request Fail");
		}

		return orderVO;
	}

	public DispatchAcceptVO accept(DispatchAcceptDTO dispatchAcceptDTO) {
		String dateString = makeDateString(new Date());

		Integer orderPK = dispatchAcceptDTO.getOrderPK();
		DispatchRequestVO dispatchRequestVO = dispatchRequestDAO.getDispatchRequestByOrderPK(orderPK);

		if(dispatchRequestVO == null){
			throw new BusinessException("Order", "Accept Order is not Valid");
		}

		int insertResult = dispatchAcceptDAO.insertDispatchAccept(dispatchAcceptDTO, dateString);

		if(insertResult == 0){
			throw new RuntimeException("Fail to insert DispatchAccepct");
		}

		DispatchAcceptVO dispatchAcceptVO = dispatchAcceptDAO.getDispatchAcceptByOrderPK(dispatchAcceptDTO.getOrderPK());

		if(dispatchAcceptVO == null){
			throw new RuntimeException("Can not find inserted dispatchAccept");
		}

		matchToDispatchRequest(dispatchRequestVO, dispatchAcceptVO);
		int updateResult = dispatchRequestDAO.updateDispatchRequest(dispatchRequestVO);

		if(updateResult == 0){
			throw new RuntimeException("Can't update dispatchAcceptVO To DB");
		}

		return dispatchAcceptVO;
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

	private void matchToDispatchRequest(DispatchRequestVO dispatchRequestVO, DispatchAcceptVO dispatchAcceptVO) {
		dispatchRequestVO.setDispatchPK(dispatchAcceptVO.getPK());
		dispatchRequestVO.setOrderState(ORDER_STATE.RESERVE);
	}

	private String makeDateString(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
}

package com.remember.inhoeku.remember_test.dao;

import com.remember.inhoeku.remember_test.domain.dto.DispatchAcceptDTO;
import com.remember.inhoeku.remember_test.domain.vo.DispatchAcceptVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DispatchAcceptDAOTest {

	@Autowired
	DispatchAcceptDAO dispatchAcceptDAO;

	@Test
	public void insertDispatchAcceptTest(){
		DispatchAcceptDTO dispatchAcceptDTO = makeDispatchAcceptDTOMock();
		String dateString = makeDateString();

		boolean is_insert_success_true = dispatchAcceptDAO.insertDispatchAccept(dispatchAcceptDTO, dateString) == 1;

		Assert.assertTrue(is_insert_success_true);
	}

	@Test
	public void getDispatchAcceptByOrderPKTest(){
		DispatchAcceptDTO dispatchAcceptDTO = makeDispatchAcceptDTOMock();
		DispatchAcceptVO dispatchAcceptVO = dispatchAcceptDAO.getDispatchAcceptByOrderPK(dispatchAcceptDTO.getOrderPK());

		boolean is_dispatchAcceptVO_not_null = dispatchAcceptVO != null;
		boolean is_orderPK_same_true = dispatchAcceptVO.getOrderPK().equals(dispatchAcceptDTO.getOrderPK());
		boolean is_driverPK_same_true = dispatchAcceptVO.getDriverPK().equals(dispatchAcceptDTO.getDriverPK());
		boolean is_pk_over_0_true = dispatchAcceptVO.getPK() > 0;

		Assert.assertTrue(is_dispatchAcceptVO_not_null);
		Assert.assertTrue(is_orderPK_same_true);
		Assert.assertTrue(is_driverPK_same_true);
		Assert.assertTrue(is_pk_over_0_true);
	}

	@Test
	public void getDispatchAcceptByPKTest(){
		DispatchAcceptVO dispatchAcceptVO = dispatchAcceptDAO.getDispatchAcceptByPK(1);

		boolean is_dispatchAccept_not_null_true = dispatchAcceptVO != null;

		Assert.assertTrue(is_dispatchAccept_not_null_true);
	}

	private String makeDateString() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}

	private DispatchAcceptDTO makeDispatchAcceptDTOMock() {
		DispatchAcceptDTO dispatchAcceptDTO = new DispatchAcceptDTO();
		dispatchAcceptDTO.setDriverPK(6);
		dispatchAcceptDTO.setOrderPK(5);
		return dispatchAcceptDTO;
	}
}

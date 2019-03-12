package com.remember.inhoeku.remember_test.Service;

import com.google.gson.Gson;
import com.remember.inhoeku.remember_test.domain.vo.DispatchRequestVO;
import com.remember.inhoeku.remember_test.domain.vo.RequestInfoVO;
import com.remember.inhoeku.remember_test.service.ListService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ListServiceTest {

	@Autowired
	private ListService listService;

	@Test
	public void getAllDispatchRequestListTest() {
		List<DispatchRequestVO> dispatchRequestVOS = listService.getAllDispatchRequestList();

		boolean is_Dispatch_Request_list_not_empty = !dispatchRequestVOS.isEmpty();

		Assert.assertTrue(is_Dispatch_Request_list_not_empty);
	}
	@Test
	public void getAllDispatchRequestInfoListTest(){
		List<RequestInfoVO> requestInfoVOList = listService.getAllRequestInfoList();
		String requestInfoVOListJson = new Gson().toJson(requestInfoVOList);

		boolean is_RequestInfoVo_list_not_empty = !requestInfoVOList.isEmpty();
		boolean is_RequestInfoVO_list_Json_not_Empty = !requestInfoVOListJson.isEmpty();

		Assert.assertTrue(is_RequestInfoVo_list_not_empty);
		Assert.assertTrue(is_RequestInfoVO_list_Json_not_Empty);

		System.out.println(requestInfoVOListJson);
	}
}

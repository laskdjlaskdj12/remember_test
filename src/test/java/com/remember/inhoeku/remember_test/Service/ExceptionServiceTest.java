package com.remember.inhoeku.remember_test.Service;

import com.remember.inhoeku.remember_test.controller.handler.CustomExceptionHandler;
import com.remember.inhoeku.remember_test.domain.error.BusinessException;
import com.remember.inhoeku.remember_test.service.ExceptionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExceptionServiceTest {
	@Autowired
	ExceptionService exceptionService;

	@Autowired
	CustomExceptionHandler customExceptionHandler;

	@Value("${exceptiondirectory}")
	private String exceptionPath;

	@Test
	public void makeReportFileNameTest(){
		String fileName = exceptionService.makeReportFileName();

		Assert.assertNotNull(fileName);
		System.out.println(fileName);
	}

	@Test
	public void exceptionServiceTest(){
		String fileName = exceptionService.makeReportFileName();

		exceptionService.saveFile("This is Error", fileName);
		File exceptionFileList = new File(exceptionPath);
		int fileListSize = exceptionFileList.listFiles().length;

		Assert.assertTrue(fileListSize > 0);
	}
}

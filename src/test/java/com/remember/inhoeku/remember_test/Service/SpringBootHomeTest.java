package com.remember.inhoeku.remember_test.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootHomeTest {
	@Value("${exceptiondirectory}")
	private String saveLoation;

	@Test
	public void printLocationPathTest() {
		System.out.println(saveLoation);
	}
}

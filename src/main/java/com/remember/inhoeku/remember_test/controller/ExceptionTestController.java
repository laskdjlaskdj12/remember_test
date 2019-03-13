package com.remember.inhoeku.remember_test.controller;

import com.remember.inhoeku.remember_test.domain.error.BusinessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin("*")
@RequestMapping("/exception")
public class ExceptionTestController {

	@GetMapping("/business")
	public void emitBusinessException(){
		throw new BusinessException("Test", "This is Test Exception");
	}

	@GetMapping("/runtime")
	public void runtimeException(){
		throw new RuntimeException("This is Runtime Exception");
	}

	@GetMapping("/makefile")
	public void makeFile() throws IOException {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);

		File file = new File(s + "/log/test.txt");
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write("This is Test File");
		fileWriter.close();
	}
}

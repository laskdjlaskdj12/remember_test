package com.remember.inhoeku.remember_test.service;

import com.google.gson.Gson;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ExceptionService {

	@Value("${exceptiondirectory}")
	private String saveLoation;

	public void saveReportFile(HttpServletRequest request, Throwable throwable) {
		Map<String, String> headers = getHeaders(request);
		Map<String, String> cookies = getCookies(request);
		String method = request.getMethod();
		StringBuffer URL = request.getRequestURL();
		String URI = request.getRequestURI();
		String remoteHost = request.getRemoteHost();
		String queryString = request.getQueryString();

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("URL : " + URL);
		stringBuilder.append("URI : " + URI);
		stringBuilder.append("remoteHost : " + remoteHost);
		stringBuilder.append("Cookie : " + new Gson().toJson(cookies));
		stringBuilder.append("method : " + method);
		stringBuilder.append("queryString : " + queryString);
		stringBuilder.append("headers : " + new Gson().toJson(headers));
		stringBuilder.append("error message : " + throwable.getMessage());
		stringBuilder.append("stack Tracke : " + getExceptionStackTrace(throwable, 3));

		String reportFileName = makeReportFileName();

		saveFile(stringBuilder.toString(), reportFileName);
	}

	public String makeReportFileName(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());

		return saveLoation + "/" + time + ".txt";
	}

	public void saveFile(String errorString, String reportFileName) {
		File file = new File(String.format(reportFileName));
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(errorString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	Map<String, String> getCookies(HttpServletRequest r) {
		Map<String, String> map = new HashMap<>();
		Cookie[] cookies = r.getCookies();
		if (cookies != null) {
			Arrays.stream(cookies).forEach(cookie -> map.put(cookie.getName(), cookie.getValue()));
		}
		return map;
	}

	Map<String, String> getHeaders(HttpServletRequest r) {
		Map<String, String> map = new HashMap<>();
		Enumeration<String> headerNames = r.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			map.put(name, r.getHeader(name));
		}
		return map;
	}

	private String getExceptionStackTrace(Throwable e, int line) {
		String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(e);
		StringBuilder st = new StringBuilder();
		for (int i = 0; i < stackTrace.length; i++) {
			if (i > line) {
				break;
			}
			st.append(String.format("%s\n", stackTrace[i]));
		}
		return st.toString();
	}
}

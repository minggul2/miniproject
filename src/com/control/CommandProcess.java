package com.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandProcess {
	//jsp파일 '이름'을 넘겨주어야함
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}

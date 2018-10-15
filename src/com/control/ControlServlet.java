package com.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, Object> map = new HashMap<String, Object>();
	
	public void init(ServletConfig config) {
		String propertyConfig = config.getInitParameter("propertyConfig");
		System.out.println("propertyConfig = "+propertyConfig+"\n");
		
		FileInputStream fin = null;
		Properties properties = new Properties();
		
		try {
			fin = new FileInputStream(propertyConfig);
			properties.load(fin);	//properties객체에 설정정보 load 이용해서 저장 
			System.out.println("properties = "+properties);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fin.close();	//쓰고 닫아줌
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		
		Iterator it = properties.keySet().iterator();
		while(it.hasNext()) {
			String key = (String)it.next();		//인덱스가아니라 key값
			System.out.println("key = "+key);	
			
			String className = properties.getProperty(key);		//key에 해당하는 값 즉 클래스이름 받아옴
			System.out.println("className = "+className);
			
			try {
				Class classType = Class.forName(className);
				Object ob = classType.newInstance();
				System.out.println("ob = "+ob);
				
				map.put(key, ob);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
			System.out.println();
		}//while
		
	}
       
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		execute(request,response);
 	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request,response);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println();
		
		//한글처리
		if(request.getMethod().equals("POST")){	//form태그의 method 형태, 대문자로
			request.setCharacterEncoding("UTF-8");
		}
		
		//http://localhost:8080/miniproject/main/index.do 요청
		String category = request.getServletPath();
		System.out.println("category = "+category);
		
		CommandProcess commandProcess = (CommandProcess)map.get(category);
		System.out.println("commandProcess = "+commandProcess);
		
		String view = null;
		try {
			view = commandProcess.requestPro(request, response);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		//forward	
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);//상대번지
		dispatcher.forward(request, response);//제어권 넘기기
	}

}












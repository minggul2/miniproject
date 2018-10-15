package main.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

public class IndexAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if(request.getSession().getAttribute("memId") == null) {
			request.setAttribute("left_display", "/member/loginForm.jsp");
		}
		request.setAttribute("display", "/template/body.jsp");
		
		return "/main/index.jsp";
	}
	
}

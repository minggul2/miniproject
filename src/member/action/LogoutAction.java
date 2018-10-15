package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

public class LogoutAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		session.invalidate();
		
		System.out.println("·Î±×¾Æ¿ô");
		request.setAttribute("display", "/member/logout.jsp");
		return "/main/index.jsp";
	}
	
}

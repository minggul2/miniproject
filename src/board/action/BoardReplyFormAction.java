package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

public class BoardReplyFormAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String pseq = request.getParameter("pseq");
		String pg = request.getParameter("pg");
		
		request.setAttribute("display", "/board/boardReplyForm.jsp");
		request.setAttribute("pg", Integer.parseInt(request.getParameter("pg")));
		request.setAttribute("pseq", Integer.parseInt(request.getParameter("pseq")));
		System.out.println(pseq);
		
		return "/main/index.jsp";
	}

}

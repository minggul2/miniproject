package board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.dao.BoardDAO;

public class BoardModifyAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.boardModify(subject, content, seq);
		
		
		request.setAttribute("display", "/board/boardModify.jsp");
		
		return "/main/index.jsp";
	}
	
}

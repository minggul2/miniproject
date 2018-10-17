package board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.dao.BoardDAO;

public class BoardReplyAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String pseq = request.getParameter("pseq");
		int pg = Integer.parseInt(request.getParameter("pg"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String id = (String)request.getSession().getAttribute("memId");
		String email = (String)request.getSession().getAttribute("memEmail");
		String name = (String)request.getSession().getAttribute("memName");
		
		System.out.println(pseq);
		
		System.out.println(pseq + " + " + pg + " + " + subject + " + " + content + " + " + id);
		
//		request.setAttribute("display", "/template/body.jsp");
		//request.setAttribute("display", "/template/body.jsp");
		
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		map.put("email", email);
		map.put("subject", subject);
		map.put("content", content);
		map.put("pseq", pseq);
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.boardReply(map);
		
		request.setAttribute("display", "/board/boardReply.jsp");
		request.setAttribute("pg", pg);
				
		return "/board/boardList.do?pg="+pg;
	}
}

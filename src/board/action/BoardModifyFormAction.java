package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class BoardModifyFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int seq = Integer.parseInt(request.getParameter("seq"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		BoardDTO boardDTO = boardDAO.boardView(seq);
		
		String id = (String)request.getSession().getAttribute("memId");
		
		request.setAttribute("display", "/board/boardModifyForm.jsp?pg="+pg+"&seq="+seq);
		request.setAttribute("seq", seq);
		request.setAttribute("pg", pg);
		request.setAttribute("subject", boardDTO.getSubject());
		request.setAttribute("content", boardDTO.getContent());
		
		
		return "/main/index.jsp";
	}

}

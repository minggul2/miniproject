package board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class BoardSearchAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String keyword = request.getParameter("keyword");
		String searchOption = request.getParameter("searchOption");
		
		System.out.println("keyword : " + keyword + "searchOption : " + searchOption);
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		List<BoardDTO> list = boardDAO.boardSearch();
		
		
		request.setAttribute("display", "/board/boardList.jsp");
		return "/main/index.jsp";
	}

}

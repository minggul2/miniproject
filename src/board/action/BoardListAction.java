package board.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.dao.BoardDAO;

public class BoardListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*int pg;
		
		if(request.getParameter("pg") != null) {
			pg = Integer.parseInt(request.getParameter("pg"));
		}else {
			pg = 1;
		}*/
		
		int pg = request.getParameter("pg") != null ? Integer.parseInt(request.getParameter("pg")) : 1; 
		
		
		BoardDAO boardDAO = BoardDAO.getInstance();

		//1�������� 5����
		int endNum = pg * 5;
		int startNum = endNum - 4;
		
		List<BoardDTO> list = boardDAO.getList(startNum, endNum);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		
		
		int totalA = boardDAO.getBoardTotalA();
		
		BoardPaging boardPaging = new BoardPaging();
		boardPaging.setCurrentPage(pg);
		
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		
		request.setAttribute("list", list);
		request.setAttribute("boardPaging", boardPaging);
		request.setAttribute("pg", pg);
		
		request.setAttribute("display", "/board/boardList.jsp");
		return "/main/index.jsp";
	}

}

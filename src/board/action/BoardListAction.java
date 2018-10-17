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
		
		int list_num = 5;
		if(request.getParameter("list_num") != null) {
			list_num = Integer.parseInt(request.getParameter("list_num"));
		}
		 
		
		
		BoardDAO boardDAO = BoardDAO.getInstance();

		//1ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ 5ï¿½ï¿½ï¿½ï¿½
		/*int endNum = pg * 5;
		int startNum = endNum - 4;*/
		
		//int endNum = pg * 15;
		//int startNum = endNum - 14;
		
		int endNum = pg * list_num;
		int startNum = endNum - list_num-1;
		
		//15¸¦ request.getParameter(list_num);
		
		
		
		
		//getList
		//makePagingHTML
		
		List<BoardDTO> list = boardDAO.getList(startNum, endNum);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		
		
		int totalA = boardDAO.getBoardTotalA();
		
		BoardPaging boardPaging = new BoardPaging();
		boardPaging.setCurrentPage(pg);
		
		boardPaging.setPageBlock(3);
//		boardPaging.setPageSize(5);
//		boardPaging.setPageSize(15);
		boardPaging.setPageSize(list_num);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		
		request.setAttribute("list", list);
		request.setAttribute("boardPaging", boardPaging);
		request.setAttribute("pg", pg);
		
		request.setAttribute("display", "/board/boardList.jsp");
		return "/main/index.jsp";
	}

}

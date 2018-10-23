package board.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.dao.BoardDAO;

public class BoardSearchAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int pg = request.getParameter("pg") != null ? Integer.parseInt(request.getParameter("pg")) : 1; 
		
		int list_num = Integer.parseInt(request.getParameter("list_num"));
		
		String keyword = request.getParameter("keyword");
		String searchOption = request.getParameter("searchOption");
		
		if(searchOption.equals("제목")) {
			searchOption = "subject";
		}else if(searchOption.equals("아이디")) {
			searchOption = "id";
		}
	
		
		System.out.println("keyword : " + keyword + "searchOption : " + searchOption);
		int endNum = pg * list_num;
		int startNum = endNum - (list_num-1);
		Map<String, String> map = new HashMap<>();
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		map.put("keyword", keyword);
		map.put("searchOption", searchOption);
		
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		List<BoardDTO> list = boardDAO.boardSearch(map);
		
		System.out.println("변수" + endNum + ", " + startNum);
		System.out.println("리스트사이즈"  + list.size());
		
		int totalA = boardDAO.getBoardSearchTotalA(map);
		System.out.println("totalA : " + totalA);
		BoardPaging boardPaging = new BoardPaging();
		boardPaging.setCurrentPage(pg);
		
		boardPaging.setPageBlock(3);
		//boardPaging.setPageSize(5);
		boardPaging.setPageSize(list_num);
		boardPaging.setTotalA(totalA);
		boardPaging.makeSearchPagingHTML();
		
		request.setAttribute("list", list);
		request.setAttribute("boardPaging", boardPaging);
		request.setAttribute("pg", pg);
		
		if(list_num == 5) {
			request.setAttribute("display", request.getParameter("display"));
			return "/board/boardSearchList.jsp";
			//return "/main/index.jsp";
		}else {
			request.setAttribute("display", request.getParameter("display"));
			return "/board/boardSearchList.jsp";
			//return "/main/index.jsp";
		}
		//return "/main/index.jsp";
	}

}

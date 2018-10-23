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
		
		int list_num;
		if(request.getParameter("list_num") != null) {
			list_num = Integer.parseInt(request.getParameter("list_num"));
			System.out.println("¸®½ºÆ® ³Ñ¹ö = " + list_num);
		}else {
			list_num = 5;
		}
		/*
		int list_num = 5;
		
		if(request.getParameter("list_num") != null) {
			list_num = Integer.parseInt(request.getParameter("list_num"));
		System.out.println("¸®½ºÆ® ³Ñ¹ö = " + list_num);
		}
		*/
		BoardDAO boardDAO = BoardDAO.getInstance();

		//1ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ 5ï¿½ï¿½ï¿½ï¿½
		int endNum = pg * list_num;
		int startNum = endNum - (list_num-1);
		System.out.println("º¯¼ö" + endNum + ", " + startNum);
		Map<String, Integer> map = new HashMap<>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		List<BoardDTO> list = boardDAO.getList(map);
		System.out.println("¸®½ºÆ®»çÀÌÁî"  + list.size());
		
		
		
		int totalA = boardDAO.getBoardTotalA();
		
		BoardPaging boardPaging = new BoardPaging();
		boardPaging.setCurrentPage(pg);
		
		boardPaging.setPageBlock(3);
		//boardPaging.setPageSize(5);
		boardPaging.setPageSize(list_num);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		
		request.setAttribute("list", list);
		request.setAttribute("boardPaging", boardPaging);
		request.setAttribute("pg", pg);
		
		if(list_num == 5) {
			request.setAttribute("display", request.getParameter("display"));
			return "/board/boardList.jsp";
		}else {
			request.setAttribute("display", request.getParameter("display"));
			return "/board/boardList.jsp";
		}
//		return "/main/index.jsp";
		
		/*return "/board/boardList.jsp";*/
	}

}

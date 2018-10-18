package imageboard.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.dao.BoardDAO;
import imageboard.bean.ImageboardDTO;
import imageboard.dao.ImageboardDAO;

public class ImageboardListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int pg = request.getParameter("pg") != null ? Integer.parseInt(request.getParameter("pg")) : 1; 
		
		System.out.println(request.getParameter("list_num"));
			
		int img_list_num = 3;
		
		if(request.getParameter("list_num") != null) {
			img_list_num = Integer.parseInt(request.getParameter("img_list_num"));
			System.out.println("¸®½ºÆ® ³Ñ¹ö = " + img_list_num);
		}
		
		ImageboardDAO imageboardDAO = ImageboardDAO.getInstance();

		//1ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ 5ï¿½ï¿½ï¿½ï¿½
		int endNum = pg * img_list_num;
		int startNum = endNum - (img_list_num-1);
		System.out.println("º¯¼ö" + endNum + ", " + startNum);
		List<ImageboardDTO> list = imageboardDAO.getList(startNum, endNum);
		System.out.println("¸®½ºÆ®»çÀÌÁî"  + list.size());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		
		
		int totalA = imageboardDAO.getBoardTotalA();
		
		BoardPaging boardPaging = new BoardPaging();
		boardPaging.setCurrentPage(pg);
		
		boardPaging.setPageBlock(3);
		//boardPaging.setPageSize(5);
		boardPaging.setPageSize(img_list_num);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		
		request.setAttribute("list", list);
		request.setAttribute("boardPaging", boardPaging);
		request.setAttribute("pg", pg);
		
		request.setAttribute("display", request.getParameter("display"));
		
		return "/imageboard/imageboardList.jsp";
	}

}

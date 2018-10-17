package imageboard.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.dao.BoardDAO;
import imageboard.dao.ImageboardDAO;

public class ImageboardWriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("memId");
		
		String imageId  = request.getParameter("imageId");
		String imageName = request.getParameter("imageName");
		String imagePrice  = request.getParameter("imagePrice");
		String imageQty = request.getParameter("imageQty");
		String imageContent = request.getParameter("imageContent");
		String image1 = request.getParameter("image1");
		
		
		Map<String, String> map = new HashMap<>();
		map.put("imageId", imageId);
		map.put("imageName", imageName);
		map.put("imagePrice", imagePrice);
		map.put("imageQty", imageQty);
		map.put("imageContent", imageContent);
		map.put("image1", image1);
		
		ImageboardDAO imageboardDAO = ImageboardDAO.getInstance();
		imageboardDAO.writeImageboard(map);
		
		
		
		
		request.setAttribute("display", "/imageboard/imageboardWrite.jsp");
		
		return "/main/index.jsp";
	}

}

package imageboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import imageboard.bean.ImageboardDTO;
import imageboard.dao.ImageboardDAO;

public class ImageboardViewAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		ImageboardDAO imageboardDAO = ImageboardDAO.getInstance();
		ImageboardDTO imageboardDTO = imageboardDAO.imageboardView(seq);
		
		
		
		System.out.println(seq);
		request.setAttribute("display", "/imageboard/imageboardView.jsp?seq="+seq);
		request.setAttribute("seq", seq);
		request.setAttribute("imageboardDTO", imageboardDTO);
		return "/main/index.jsp";
	}

}

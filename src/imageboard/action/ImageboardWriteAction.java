package imageboard.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.dao.BoardDAO;
import imageboard.dao.ImageboardDAO;

public class ImageboardWriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		//업로드는submit과 동시에 생성되는것이고
		//multi 객체로는 데이터만 가져오는것임
		/*String savePath = "C:\\Users\\minggul\\git\\miniproject\\WebContent\\upload";*/
		String savePath = request.getServletContext().getRealPath("upload");
		int sizeLimit = 10*1024*1024;
		System.out.println(savePath);
		
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8",new DefaultFileRenamePolicy());
		String file_name = multi.getFilesystemName("image1");
		String file_path = savePath + "\\" + file_name;
		
		
		
		String id = (String)session.getAttribute("memId");
		
		String imageId  = multi.getParameter("imageId");
		String imageName = multi.getParameter("imageName");
		String imagePrice  = multi.getParameter("imagePrice");
		String imageQty = multi.getParameter("imageQty");
		String imageContent = multi.getParameter("imageContent");
		String image1 = file_path;
		
		
		System.out.println(imageId);
		System.out.println(imageName);
		System.out.println(image1);
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

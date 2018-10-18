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
import imageboard.bean.ImageboardDTO;
import imageboard.dao.ImageboardDAO;

public class ImageboardWriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		//업로드는submit과 동시에 생성되는것이고
		//multi 객체로는 데이터만 가져오는것임
		/*String savePath = "C:\\Users\\minggul\\git\\miniproject\\WebContent\\upload";*/
		//String savePath = request.getServletContext().getRealPath("upload");
		String savePath = request.getServletContext().getRealPath("/upload");
		int sizeLimit = 10*1024*1024;
		System.out.println(savePath);
		
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8",new DefaultFileRenamePolicy());
		String file_name = multi.getFilesystemName("image1");
		//String file_path = savePath + "\\" + file_name;
		
		
		
		String id = (String)session.getAttribute("memId");
		
		String imageId  = multi.getParameter("imageId");
		String imageName = multi.getParameter("imageName");
		int imagePrice  = Integer.parseInt(multi.getParameter("imagePrice"));
		int imageQty = Integer.parseInt(multi.getParameter("imageQty"));
		String imageContent = multi.getParameter("imageContent");
		//String image1 = file_path;
		String image1 = multi.getOriginalFileName("image1");
		
		System.out.println(imageId);
		System.out.println(imageName);
		System.out.println(image1);
		
		ImageboardDTO imageboardDTO = new ImageboardDTO();
		imageboardDTO.setImageId(imageId);
		imageboardDTO.setImageName(imageName);
		imageboardDTO.setImagePrice(imagePrice);
		imageboardDTO.setImageQty(imageQty);
		imageboardDTO.setImageContent(imageContent);
		imageboardDTO.setImage1(image1);
		
		
		ImageboardDAO imageboardDAO = ImageboardDAO.getInstance();
		imageboardDAO.writeImageboard(imageboardDTO);
		
		
		request.setAttribute("display", "/imageboard/imageboardWrite.jsp");
		
		return "/main/index.jsp";
	}

}

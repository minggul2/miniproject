package member.action;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.control.CommandProcess;

import member.dao.MemberDAO;
import memberjsp.bean.MemberDTO;

public class ModifyFormAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		System.out.println(request.getSession().getAttribute("memId"));
		String id = (String) request.getSession().getAttribute("memId");
		System.out.println(id);
		
		//db
		MemberDAO memberDAO = MemberDAO.getInstance();
		//MemberDTO memberDTO = memberDAO.getMember(id);
		JSONObject json = memberDAO.getMemberJson(id); 
		
		//응답
		//request.setAttribute("memberDTO", memberDTO);
		try {
			FileWriter file = new FileWriter("D:/java_ee/workspace/miniproject/WebContent/json/modify.json");
			file.write(json.toString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("display", "/member/modifyForm.jsp");
		
		//에이작스 줘야함
		return "/main/index.jsp";
	}
	

}

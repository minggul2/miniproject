package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.dao.MemberDAO;

public class CheckIdAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String id = request.getParameter("id");
		
		
		//DB
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		boolean check = memberDAO.isExitsId(id);
		
		
		
		//����
		request.setAttribute("id", id);
		if(check) {
			return "/member/checkIdFail.jsp";
		}else {
			return "/member/checkIdOk.jsp";
		}
	}
}

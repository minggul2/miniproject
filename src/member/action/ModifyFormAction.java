package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		MemberDTO memberDTO = memberDAO.getMember(id);
		
		//응답
		request.setAttribute("memberDTO", memberDTO);
		
		request.setAttribute("display", "/member/modifyForm.jsp");
		
		return "/main/index.jsp";
	}

}

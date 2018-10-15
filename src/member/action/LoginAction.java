package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.dao.MemberDAO;
import memberjsp.bean.MemberDTO;

public class LoginAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//DB贸府
		
		//单捞磐
		String id = request.getParameter("login_id");
		String password = request.getParameter("login_password");
		
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberDTO memberDTO = memberDAO.login(id, password);
		
		request.setAttribute("display", "/template/body.jsp");
		//览翠	
		String loginResult = null;
		if(memberDTO == null) {
			loginResult = "fail";
			request.setAttribute("loginResult", loginResult);
			String fail = "fail";
			request.setAttribute("fail", fail);
			
			//request.setAttribute("display", "/member/loginFail.jsp");
			//request.setAttribute("fail", "角菩11");
			System.out.println("甸绢咳");
		}else {
			//技记
			HttpSession session = request.getSession(); //技记 积己
			session.setAttribute("memName", memberDTO.getName());
			session.setAttribute("memId", id);
			session.setAttribute("memEmail", memberDTO.getEmail1() + "@" + memberDTO.getEmail2());
			
		}	
		return "/main/index.jsp";
	}
}

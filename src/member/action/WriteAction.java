package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.dao.MemberDAO;
import memberjsp.bean.MemberDTO;

public class WriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//�뜲�씠�꽣 諛쏆븘�샂
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName(request.getParameter("name"));
		memberDTO.setId(request.getParameter("id"));
		memberDTO.setPassword(request.getParameter("password"));
		memberDTO.setGender(request.getParameter("gender"));
		memberDTO.setEmail1(request.getParameter("email1"));
		memberDTO.setEmail2(request.getParameter("email2"));
		memberDTO.setTel1(request.getParameter("tel1"));
		memberDTO.setTel2(request.getParameter("tel2"));
		memberDTO.setTel3(request.getParameter("tel3"));
		memberDTO.setZipcode(request.getParameter("zipcode"));
		memberDTO.setAddr1(request.getParameter("addr1"));
		memberDTO.setAddr2(request.getParameter("addr2"));
		
		System.out.println(request.getParameter("id"));
		
		//DB泥섎━
		MemberDAO memberDAO = MemberDAO.getInstance();
		int check = memberDAO.write(memberDTO);
		
		//�슂泥�泥섎━
		
		return "/main/index.do";
		
		
	}

}

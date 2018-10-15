package member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.dao.MemberDAO;
import memberjsp.bean.ZipcodeDTO;

public class CheckPostAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//������
		String sido = request.getParameter("sido");
		String sigungu = request.getParameter("sigungu");
		String roadname = request.getParameter("roadname");
		ArrayList<ZipcodeDTO> list = null; 
		ArrayList<String> address = null; 
		//DB
		
		if(sido != null && sigungu != null && roadname != null){
			//DB�ΰ��� �޼ҵ�� : getZipcodeList
			MemberDAO memberDAO = MemberDAO.getInstance();
			list = memberDAO.getZipcodeList(sido, sigungu, roadname);
			for(ZipcodeDTO zipcodeDTO : list) {
				address = new ArrayList<>();
				address.add(zipcodeDTO.getSido() + " "
	 				       + zipcodeDTO.getSigungu() + " "
					   	   + zipcodeDTO.getYubmyundong()+ " "
					   	   + zipcodeDTO.getRoadname() + " "
					 	   + zipcodeDTO.getBuildingname());
			}
		}

		//����
		request.setAttribute("list", list);
		request.setAttribute("address", address);
		
		return "/member/checkPost.jsp";
	}
	
}

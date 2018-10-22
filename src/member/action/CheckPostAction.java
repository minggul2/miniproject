package member.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.bean.ZipcodeDTO;
import member.dao.MemberDAO;
import member.dao.MemberDAO_test;

public class CheckPostAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		String sido = request.getParameter("sido");
		String sigungu = request.getParameter("sigungu");
		String roadname = request.getParameter("roadname");
		List<ZipcodeDTO> list = null; 
		ArrayList<String> address = null; 
		//DB
		
		if(sido != null && sigungu != null && roadname != null){
			//DB로가라 메소드명 : getZipcodeList
			MemberDAO memberDAO = MemberDAO.getInstance();
			Map<String, String> map = new HashMap<>();
			map.put("sido", sido);
			map.put("sigungu", sigungu);
			map.put("roadname", roadname);
			
			//list = memberDAO.getZipcodeList(sido, sigungu, roadname);
			list = memberDAO.getZipcodeList(map);
			
			for(ZipcodeDTO zipcodeDTO : list) {
				address = new ArrayList<>();
				address.add(zipcodeDTO.getSido() + " "
	 				       + zipcodeDTO.getSigungu() + " "
					   	   + zipcodeDTO.getYubmyundong()+ " "
					   	   + zipcodeDTO.getRoadname() + " "
					 	   + zipcodeDTO.getBuildingname());
			}
		}

		//응답
		request.setAttribute("list", list);
		request.setAttribute("address", address);
		
		return "/member/checkPost.jsp";
	}
	
}

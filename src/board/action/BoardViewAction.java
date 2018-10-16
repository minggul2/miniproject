package board.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class BoardViewAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		
		String id = null;
		if(session.getAttribute("memId") != null) {
			id = (String)session.getAttribute("memId");
		}
		
		if(id == null){
			request.setAttribute("display", "/board/boardFail.jsp");
			return "/main/index.jsp";
		}

		int seq = Integer.parseInt(request.getParameter("seq"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		Cookie[] ar = request.getCookies();		//占쏙옙키占쌨아울옙
		Cookie cookie = null;							//占쏙옙키占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
		
		if(ar != null){							//占쌨아울옙 占쏙옙키占쏙옙 占쌍다몌옙 (占싱뱄옙 占쏙옙占쏙옙 占쌉시깍옙占쏙옙 占쌕쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占�)
			for(int i = 0; i < ar.length; i++){		//占쌨아울옙 占쏙옙키占썼열 for占쏙옙
				//if(cookies[i].getValue().equals(seq+"") && cookies[i].getComment().equals(id)){	//占싹놂옙占싹놂옙 占쏙옙占싹다곤옙 占쏙옙키占싱몌옙占쏙옙 占쌜뱄옙호seq 占싹곤옙占�
				//}
				if(ar[i].getName().equals(id+seq+"")){
					cookie = ar[i];					//占쏙옙키 占쏙옙占쏙옙 占쏙옙占쏙옙
					System.out.println(cookie.getName());
					break;
				}
			}
		}
		
		if(cookie == null){								//占쌨다울옙 占쏙옙키占쏙옙 占쏙옙占쌕몌옙 (처占쏙옙 占쏙옙회占쏙옙 占쏙옙占�)
			//cookie = new Cookie("hit", seq+"");			//
			cookie = new Cookie(id+seq+"", id);
			cookie.setMaxAge(60*60);
			response.addCookie(cookie);
			boardDAO.hitUpdate(seq);
		}
		
		BoardDTO boardDTO = boardDAO.boardView(seq);
		
		request.setAttribute("boardDTO", boardDTO);
		request.setAttribute("pg", pg);
		
		request.setAttribute("display", "/board/boardView.jsp");
		
		return "/main/index.jsp";
	}

}

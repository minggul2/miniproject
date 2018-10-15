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
		
		String id = "";
		if(session.getAttribute("memId") != null) {
			id = (String)session.getAttribute("memId");
		}
		System.out.println("�ۺ��� ���̵�� : "  + id);
		
		

		int seq = Integer.parseInt(request.getParameter("seq"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		Cookie[] ar = request.getCookies();		//��Ű�޾ƿ�
		Cookie cookie = null;							//��Ű�������� ����
		
		if(ar != null){							//�޾ƿ� ��Ű�� �ִٸ� (�̹� ���� �Խñ��� �ٽ� ���������)
			for(int i = 0; i < ar.length; i++){		//�޾ƿ� ��Ű�迭 for��
				//if(cookies[i].getValue().equals(seq+"") && cookies[i].getComment().equals(id)){	//�ϳ��ϳ� ���ϴٰ� ��Ű�̸��� �۹�ȣseq �ϰ��
				//}
				if(ar[i].getName().equals(id+seq+"")){
					cookie = ar[i];					//��Ű ���� ����
					System.out.println(cookie.getName());
					break;
				}
			}
		}
		
		if(cookie == null){								//�޴ٿ� ��Ű�� ���ٸ� (ó�� ��ȸ�� ���)
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

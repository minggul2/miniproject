package board.bean;

import lombok.Data;

@Data
public class BoardPaging {
	//����¡ ó�� ���� Ŭ����
	//������ ó���ϰ������ �� Ŭ���� new �ϸ��
	
	private int currentPage;	//����������
	private int pageBlock;		//[����][1][2][3][����]
	private int pageSize;		//1�������� 5����
	private int totalA;			//�ѱۼ�
	private StringBuffer pagingHTML;

	public void makePagingHTML() {
		pagingHTML = new StringBuffer();
		
		
		int totalP = (totalA+pageSize-1)/pageSize;	//�� ��������
		
		int startPage = (currentPage-1)/pageBlock*pageBlock+1;
		int endPage = startPage+pageBlock-1;
		if(endPage > totalP) endPage = totalP;
		
		if(startPage > pageBlock) {
//			pagingHTML.append("[<a id = 'paging' href = '/miniproject/board/boardList.do?pg="+(startPage-1)+"'>����</a>]");
			pagingHTML.append("[<a id = 'paging' class = "+"'"+(startPage-1)+"_page' href = '#'>����</a>]");
		}
		
		for(int i = startPage; i <= endPage; i++) {
			if(currentPage == i) {
//				pagingHTML.append("[<a id = 'currentPage' href = '/miniproject/board/boardList.do?pg="+i+"'>"+i+"</a>]");
				pagingHTML.append("[<a id = 'currentPage' class = 'page_"+i+"' href = '#'>"+i+"</a>]");	//pg�� currentPage.val()�� ����
			}else {
//				pagingHTML.append("[<a id = 'paging' href = '/miniproject/board/boardList.do?pg="+i+"'>"+i+"</a>]");
				pagingHTML.append("[<a id = 'paging' class = 'page_"+i+"' href = '#'>"+i+"</a>]");
			}
		}
		
		if(endPage < totalP) {
//			pagingHTML.append("[<a id = 'paging' href = '/miniproject/board/boardList.do?pg="+(endPage+1)+"'>����</a>]");
			pagingHTML.append("[<a id = 'paging' class = "+"'"+(endPage+1)+"_page' href = '#'>����</a>]");
		}
		
		
		
		
	}
}

//		 [1][2][3][����]
//	[����][4][5][6][����]

//  pagingHTML �� ������ ��

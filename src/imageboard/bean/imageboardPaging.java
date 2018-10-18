package imageboard.bean;

import lombok.Data;

@Data
public class imageboardPaging {
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
		//span ���ڼ���ŭ�� div�� ���ڿʹ� ������� ��������
		if(startPage > pageBlock) {
			pagingHTML.append("[<span id = 'paging' class = "+"'"+(startPage-1)+"_page' onclick = 'imageboadrPaging("+(startPage+1)+")'>����</span>]");
		}
		
		for(int i = startPage; i <= endPage; i++) {
			if(currentPage == i) {
				pagingHTML.append("[<span id = 'currentPage' class = 'page_"+i+"' onclick = 'imageboadrPaging("+i+")'>"+i+"</span>]");	//pg�� currentPage.val()�� ����
			}else {
				pagingHTML.append("[<span id = 'paging' class = 'page_"+i+"' onclick = 'imageboadrPaging("+i+")'>"+i+"</span>]");
			}
		}
		
		if(endPage < totalP) {
			pagingHTML.append("[<span id = 'paging' class = "+"'"+(endPage+1)+"_page' onclick = 'imageboardPaging("+(endPage-1)+")'>����</span>]");
		}
		
		
		
		
	}
}

//		 [1][2][3][����]
//	[����][4][5][6][����]

//  pagingHTML �� ������ ��

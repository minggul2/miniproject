package board.bean;

import lombok.Data;

@Data
public class BoardPaging {
	//페이징 처리 전용 클래스
	//페이지 처리하고싶으면 이 클래스 new 하면됨
	
	private int currentPage;	//현재페이지
	private int pageBlock;		//[이전][1][2][3][다음]
	private int pageSize;		//1페이지당 5개씩
	private int totalA;			//총글수
	private StringBuffer pagingHTML;

	public void makePagingHTML() {
		pagingHTML = new StringBuffer();
		
		
		int totalP = (totalA+pageSize-1)/pageSize;	//총 페이지수
		
		int startPage = (currentPage-1)/pageBlock*pageBlock+1;
		int endPage = startPage+pageBlock-1;
		if(endPage > totalP) endPage = totalP;
		
		if(startPage > pageBlock) {
//			pagingHTML.append("[<a id = 'paging' href = '/miniproject/board/boardList.do?pg="+(startPage-1)+"'>이전</a>]");
			pagingHTML.append("[<a id = 'paging' class = "+"'"+(startPage-1)+"_page' href = '#'>이전</a>]");
		}
		
		for(int i = startPage; i <= endPage; i++) {
			if(currentPage == i) {
//				pagingHTML.append("[<a id = 'currentPage' href = '/miniproject/board/boardList.do?pg="+i+"'>"+i+"</a>]");
				pagingHTML.append("[<a id = 'currentPage' class = 'page_"+i+"' href = '#'>"+i+"</a>]");	//pg값 currentPage.val()로 전송
			}else {
//				pagingHTML.append("[<a id = 'paging' href = '/miniproject/board/boardList.do?pg="+i+"'>"+i+"</a>]");
				pagingHTML.append("[<a id = 'paging' class = 'page_"+i+"' href = '#'>"+i+"</a>]");
			}
		}
		
		if(endPage < totalP) {
//			pagingHTML.append("[<a id = 'paging' href = '/miniproject/board/boardList.do?pg="+(endPage+1)+"'>다음</a>]");
			pagingHTML.append("[<a id = 'paging' class = "+"'"+(endPage+1)+"_page' href = '#'>다음</a>]");
		}
		
		
		
		
	}
}

//		 [1][2][3][다음]
//	[이전][4][5][6][다음]

//  pagingHTML 이 만들어다 줌

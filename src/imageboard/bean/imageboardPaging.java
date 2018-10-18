package imageboard.bean;

import lombok.Data;

@Data
public class imageboardPaging {
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
		//span 글자수만큼만 div는 글자와는 상관없이 영역잡음
		if(startPage > pageBlock) {
			pagingHTML.append("[<span id = 'paging' class = "+"'"+(startPage-1)+"_page' onclick = 'imageboadrPaging("+(startPage+1)+")'>이전</span>]");
		}
		
		for(int i = startPage; i <= endPage; i++) {
			if(currentPage == i) {
				pagingHTML.append("[<span id = 'currentPage' class = 'page_"+i+"' onclick = 'imageboadrPaging("+i+")'>"+i+"</span>]");	//pg값 currentPage.val()로 전송
			}else {
				pagingHTML.append("[<span id = 'paging' class = 'page_"+i+"' onclick = 'imageboadrPaging("+i+")'>"+i+"</span>]");
			}
		}
		
		if(endPage < totalP) {
			pagingHTML.append("[<span id = 'paging' class = "+"'"+(endPage+1)+"_page' onclick = 'imageboardPaging("+(endPage-1)+")'>다음</span>]");
		}
		
		
		
		
	}
}

//		 [1][2][3][다음]
//	[이전][4][5][6][다음]

//  pagingHTML 이 만들어다 줌

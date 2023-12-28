package com.w2.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Paging {
 	

	private static final int VIEW_POST_NUM = 20;	// 한 페이지당 보여 줄 게시글의 수
	private static final int VIEW_PAGE_NUM = 5;		// 한 페이지당 보여줄 페이지의 수
	
	private Integer totalPosts;		// 총 게시글의 수
	private Integer totalPage;		//게시판의 전체 페이지 수
	

	private Integer currentPage;	// 현재 페이지 번호
	private Integer startPage;		// 현재 페이지의 시작 번호
	private Integer endPage;		// 현재 페이지의 끝 번호
	
	private boolean prev;		// 이전 버튼 활성화 여부
	private boolean next;		// 다음 버튼 활성화 여부
	
	public Paging(Integer totalPosts, Integer currentPage, Object object) {
		if( currentPage != null) {
			this.currentPage = currentPage;
		} else { 
			this.currentPage = 1;
		}
		
		this.totalPosts = totalPosts;
		System.err.println("3. [ Paging ] totalPosts : " + totalPosts);
		
		// 생성자에서 메소드를 호출하여 페이지 번호를 부여하는 기능을 추가합니다.
		pagingMaker();
	}
	
	// 페이지 번호를 만들어주는 메소드
	public void pagingMaker() {
		// 한 페이지당 보여줄 글의 개수로 총 페이지를 나누어 총 페이지 수를 지정합니다.
		totalPage = (totalPosts-1) / VIEW_POST_NUM + 1;
		
		System.err.println("4. [ Paging ] totalPage : " + totalPage);
		
		if(currentPage < 1 || currentPage > totalPage) {
			currentPage = 1;
		}
		
		endPage = ((currentPage - 1) / VIEW_PAGE_NUM + 1) * VIEW_PAGE_NUM;
		
		if(endPage >= totalPage) {
			endPage = totalPage;
		}
		
		startPage = ((currentPage-1) / VIEW_PAGE_NUM) * VIEW_PAGE_NUM + 1;
		
		// 시작 페이지가 1과 같으면 이전 버튼을 비활성화하고 다르면 이전버튼을 활성화합니다.
		prev = (startPage == 1) ? false : true;
	
		// 끝 페이지가 총 페이지 수와 같다면 다음 버튼을 비활성화 하고 다르다면 다음 버튼을 활성화합니다.
		next = (endPage == totalPage) ? false : true;
	}
}

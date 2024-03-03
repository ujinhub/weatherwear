package com.w2.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchOrderby extends Pagination {
	private String searchType;		// 조회타입
	private String keyword;			// 검색어
	private String orderby;			// 정렬방식
}

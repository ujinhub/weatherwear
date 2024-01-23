package com.w2.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchOrderby extends Pagination {
	private String searchType;
	private String keyword;
	private String orderby;
}

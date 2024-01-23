package com.w2.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OptionVO {
	private String productId;  	// 상품번호
	private String optionColor; // 색상
	private String optionSize;  // 사이즈
	private int stockCnt;  		// 재고
	private String optionId;  	// 옵션번호
}

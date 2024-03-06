package com.w2.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductPriceVO {
	private String productId;  		// 상품번호
	private int productPrimeCost;  	// 공급가
	private int productCost;  		// 소비자가
	private int productPrice;  		// 판매가(실제적용)
	private Double productTax;  	// 과세율 0.1
	private Double productMargin;  	// 마진율 1.5
	private int productAddCost;  	// 추가금액 0
}

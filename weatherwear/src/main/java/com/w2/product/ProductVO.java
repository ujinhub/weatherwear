package com.w2.product;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductVO {
	private String productId;  		// 상품번호
	private String productCate;  	// 카테고리
	private String productName;  	// 상품이름
	private String productContent;  // 상세내용
	private int productView;  		// 조회수 0
	private int productLike;  		// 찜개수 0
	private Date productRegDate;  	// 등록일자 CURRENT_TIMESTAMP()
	private String productSell;		// 판매상태 (Y/N)
	private int productCnt;			// 판매누적 0
	
	private String mainImage;		// 메인이미지
	private int productPrice;		// 판매가
}

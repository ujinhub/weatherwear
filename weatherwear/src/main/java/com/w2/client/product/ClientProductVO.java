package com.w2.client.product;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientProductVO {
	 
	// product
	private String proSell;  	// 판매상태
	private String proId;  		// 상품번호
	private String proCate;  	// 카테고리
	private String proName;  	// 상품명
	private String proContent;  // 상세설명
	
	private int proView;  		// 조회수
	private int proLike;  		// 좋아요수
	private Date proRegDate;  	// 등록일
	private int proCnt;  		// 판매량
	
	// product_price
	private int proPrimeCost;  	// 공급가
	private int proCost;  		// 소비자가
	private int proPrice;  		// 판매가
	private Double proTax;  	// 과세율
	private Double proMargin;  	// 마진율
	private int proAddCost;  	// 추가금액
	
	// category
	private String cateName;	// 카테고리
	
	// option_sample
	private List<String> opColorList;	// 컬러 옵션
	private List<String> opSizeList;	// 사이즈 옵션
	private List<Integer> stCntList;	// 재고 수량
	
	private String imageDir;	// 메인 이미지 경로
	private String imageName;	// 메인 이미지 이름
}
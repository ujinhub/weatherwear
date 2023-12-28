package com.w2.client.cart;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientCartDTO {
	// 장바구니 정보
	private int caId;			// 장바구니 ID
	private String proId;		// 상품 ID
	private String opId;		// 옵션 ID
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp caDate;	// 장바구니 등록 일시
	private int caCnt;			// 상품 수량
	
	// 상품 정보
	private String proName;  	// 상품명
	private int proPrice;  		// 판매가
	private int stCnt;			// 재고 수량
	private String opColor;		// 옵션 색상
	private String opSize;		// 옵션 사이즈
	
	// 상품 이미지 정보
	private String imageDir;	// 메인 이미지 경로
	private String imageName;	// 메인 이미지 이름
}

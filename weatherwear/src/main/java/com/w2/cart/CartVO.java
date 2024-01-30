package com.w2.cart;

import java.util.Date;

import com.w2.product.OptionVO;
import com.w2.product.ProductVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CartVO {
	private String cartId;			// 장바구니 번호
	private String productId;	// 상품 번호
	private String optionId;	// 옵션 번호
	private String clientId;	// 회원 번호
	private Date cartDate;		// 장바구니 추가 일자
	private int cartCnt;		// 수량
	private String cookieId;	// 쿠키 번호
	private String cookieLimit;	// 쿠키 만료 일자

	// join용
	private ProductVO product;	// 상품 정보, 이미지, 상품 가격
	private OptionVO option;		// 옵션 정보
}

package com.w2.order;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderVO {
	private String orderId;			// 주문번호
	private String clientId;		// 회원번호
	private String addressId;		// 배송지번호
	private String optionIdList;	// 주문상품리스트
	private String deliverId;		// 배송번호
	private Date orderDate;			// 주문일자
	private int orderPrice;			// 주문금액
	private String orderStatus;		// 주문상태
	private int usedPoint;			// 적용포인트
	private String couponId;		// 적용쿠폰
	private String cookieId;		// 쿠키번호
	private String cookiePwd;		// 비회원비밀번호
	private String orderEmail;		// 이메일주소
	
	// join용
	private List<OrderInfoVO> orderInfoList;
}

package com.w2.coupon;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponVO {
	private String couponId;		// 쿠폰 번호
	private String couponName;		// 쿠폰 이름
	private int couponPrice;		// 쿠폰 적용 금액
	private Timestamp couponStDate;	// 쿠폰 시작 일자
	private Timestamp couponEndDate;	// 쿠폰 만료 일자
	private int minPrice;			// 최소 적용 금액
	private String gradeList;		// 회원 등급

	// couponList
	private String clientId;		// 회원 번호
	private String couponStatus;	// 쿠폰 상태(사용가능, 사용완료, 기간만료)
}

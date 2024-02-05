package com.w2.coupon;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponVO {
	private String couponId;
	private String couponName;
	private int couponPrice;
	private Date couponStDate;
	private Date couponEndDate;
	private int minPrice;
	private String gradeList;

	// couponList
	private String clientId;
	private String couponStatus;
}

package com.w2.coupon.service;

import java.util.List;

import com.w2.coupon.CouponVO;

public interface CouponService {

	List<CouponVO> getCouponList(String clientId);
}

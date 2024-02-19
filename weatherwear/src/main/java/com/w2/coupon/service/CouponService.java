package com.w2.coupon.service;

import java.util.HashMap;
import java.util.List;

import com.w2.coupon.CouponVO;
import com.w2.util.Search;

public interface CouponService {

	List<CouponVO> getCouponList(String clientId);
	
	/**
	 * 나의 쿠폰 내역 가져오기 (페이징) - 권유진 추가
	 */
	List<CouponVO> getMyCouponList(HashMap<String, Object> param);
	int getMyCouponListCnt(String ClientId);
	
	CouponVO getCouponInfo(String couponId);
	int myCouponReg(CouponVO vo);
}

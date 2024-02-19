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
	
	/** 
	 * 관리자 쿠폰 관리 - 이지형 추가 
	 */
	List<CouponVO> getCouponListAll(Search search);		// 쿠폰 목록 가져오기
	int getCouponListCnt(Search search);				// 쿠폰 목록 개수 가져오기
	int insertCoupon(CouponVO coupon);					// 쿠폰 등록
	int updateCoupon(CouponVO coupon);					// 쿠폰 수정
	int deleteCoupon(String couponId);					// 쿠폰 삭제
	int setCoupon();									// 쿠폰 배부
	void setCouponLimit();								// 쿠폰 기간 만료 체크
}

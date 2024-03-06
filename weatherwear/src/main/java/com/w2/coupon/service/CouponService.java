package com.w2.coupon.service;

import java.util.HashMap;
import java.util.List;

import com.w2.client.ClientVO;
import com.w2.coupon.CouponVO;
import com.w2.util.Search;

public interface CouponService {
	// 관리자
	List<CouponVO> getCouponListAll(Search search);		// 쿠폰 목록 가져오기
	int getCouponListCnt(Search search);				// 쿠폰 목록 개수 가져오기
	int insertCoupon(CouponVO coupon);					// 쿠폰 등록
	int updateCoupon(CouponVO coupon);					// 쿠폰 수정
	int deleteCoupon(String couponId);					// 쿠폰 삭제
	int setCoupon();									// 쿠폰 배부
	void setCouponLimit();								// 쿠폰 기간 만료 체크
	
	// 사용자
	List<CouponVO> getCouponList(String clientId);		// 회원 쿠폰 조회
	List<CouponVO> getMyCouponList(HashMap<String, Object> param);	// 마이페이지 쿠폰 리스트 조회
	int getMyCouponListCnt(String ClientId);			// 마이페이지 쿠폰 리스트 개수 조회
	CouponVO getCouponInfo(String couponId);			// 쿠폰 정보 조회
	int myCouponReg(CouponVO vo);						// 쿠폰 등록_사용자
	void insertCoupon(ClientVO client);					// 신규 회원 쿠폰 배부
}

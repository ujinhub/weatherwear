package com.w2.coupon.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.client.ClientVO;
import com.w2.coupon.CouponDAO;
import com.w2.coupon.CouponVO;
import com.w2.util.Search;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	CouponDAO couponDAO;

	@Override	// 쿠폰 목록 가져오기_관리자
	public List<CouponVO> getCouponListAll(Search search) {
		return couponDAO.getCouponListAll(search);
	}

	@Override	// 쿠폰 목록 개수 가져오기
	public int getCouponListCnt(Search search) {
		return couponDAO.getCouponListCnt(search);
	}

	@Override	// 쿠폰 등록
	public int insertCoupon(CouponVO coupon) {
		return couponDAO.insertCoupon(coupon);
	}

	@Override	// 쿠폰 수정
	public int updateCoupon(CouponVO coupon) {
		return couponDAO.updateCoupon(coupon);
	}

	@Override	// 쿠폰 삭제
	public int deleteCoupon(String couponId) {
		couponDAO.deleteCouponList(couponId);
		return couponDAO.deleteCouponInfo(couponId);
	}

	@Override	// 쿠폰 배부
	public int setCoupon() {
		return couponDAO.setCoupon();
	}

	@Override	// 쿠폰 기간 만료 체크
	public void setCouponLimit() {
		couponDAO.setCouponLimit();
	}

	@Override	// 회원 쿠폰 조회
	public List<CouponVO> getCouponList(String clientId) {
		return couponDAO.getCouponList(clientId);
	}
	
	@Override	// 마이페이지 쿠폰 리스트 조회
	public List<CouponVO> getMyCouponList(HashMap<String, Object> param) {
		return couponDAO.getMyCouponList(param);
	}
	@Override	// 마이페이지 쿠폰 리스트 개수 조회
	public int getMyCouponListCnt(String ClientId) {
		return couponDAO.getMyCouponListCnt(ClientId);
	}

	@Override	// 쿠폰 정보 조회
	public CouponVO getCouponInfo(String couponId) {
		return couponDAO.getCouponInfo(couponId);
	}

	@Override	// 쿠폰 등록_사용자
	public int myCouponReg(CouponVO vo) {
		return couponDAO.myCouponReg(vo);
	}

	@Override
	public void insertCoupon(ClientVO client) {
		couponDAO.insertCoupon(client);
	}
}

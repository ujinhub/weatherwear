package com.w2.coupon.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.coupon.CouponDAO;
import com.w2.coupon.CouponVO;
import com.w2.util.Search;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	CouponDAO couponDAO;

	@Override
	public List<CouponVO> getCouponList(String clientId) {
		return couponDAO.getCouponList(clientId);
	}

	
	@Override
	public List<CouponVO> getMyCouponList(HashMap<String, Object> param) {
		return couponDAO.getMyCouponList(param);
	}
	@Override
	public int getMyCouponListCnt(String ClientId) {
		return couponDAO.getMyCouponListCnt(ClientId);
	}

	@Override
	public CouponVO getCouponInfo(String couponId) {
		return couponDAO.getCouponInfo(couponId);
	}

	@Override
	public int myCouponReg(CouponVO vo) {
		return couponDAO.myCouponReg(vo);
	}

	@Override
	public List<CouponVO> getCouponListAll(Search search) {
		return couponDAO.getCouponListAll(search);
	}

	@Override
	public int getCouponListCnt(Search search) {
		return couponDAO.getCouponListCnt(search);
	}

	@Override
	public int insertCoupon(CouponVO coupon) {
		return couponDAO.insertCoupon(coupon);
	}

	@Override
	public int updateCoupon(CouponVO coupon) {
		return couponDAO.updateCoupon(coupon);
	}

	@Override
	public int deleteCoupon(String couponId) {
		couponDAO.deleteCouponList(couponId);
		return couponDAO.deleteCouponInfo(couponId);
	}

	@Override
	public int setCoupon() {
		return couponDAO.setCoupon();
	}

	@Override
	public void setCouponLimit() {
		couponDAO.setCouponLimit();
	}
}

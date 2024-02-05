package com.w2.coupon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.coupon.CouponDAO;
import com.w2.coupon.CouponVO;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	CouponDAO couponDAO;

	@Override
	public List<CouponVO> getCouponList(String clientId) {
		return couponDAO.getCouponList(clientId);
	}
}

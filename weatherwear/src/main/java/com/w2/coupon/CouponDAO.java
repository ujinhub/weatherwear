package com.w2.coupon;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CouponDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public List<CouponVO> getCouponList(String clientId) {
		return sqlSessionTemplate.selectList("CouponDAO.getCouponList", clientId);
	}
	
	/**
	 * 나의 쿠폰 내역 가져오기 (페이징) - 권유진 추가
	 */
	public List<CouponVO> getMyCouponList(HashMap<String, Object> param) {
		return sqlSessionTemplate.selectList("CouponDAO.getMyCouponList", param);
	}
	public int getMyCouponListCnt(String clientId) {
		return sqlSessionTemplate.selectOne("CouponDAO.getMyCouponListCnt", clientId);
	}
	
	public CouponVO getCouponInfo(String couponId) {
		return sqlSessionTemplate.selectOne("CouponDAO.getCouponInfo", couponId);
	}
	public int myCouponReg(CouponVO vo) {
		return sqlSessionTemplate.insert("CouponDAO.myCouponReg", vo);
	}
}

package com.w2.coupon;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.w2.util.Search;

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

	/** 관리자 쿠폰 관리 추가 - 이지형 추가 */
	public List<CouponVO> getCouponListAll(Search search) {
		return sqlSessionTemplate.selectList("CouponDAO.getCouponListAll", search);
	}

	public int getCouponListCnt(Search search) {
		return sqlSessionTemplate.selectOne("CouponDAO.getCouponListCnt", search);
	}

	public int insertCoupon(CouponVO coupon) {
		return sqlSessionTemplate.insert("CouponDAO.insertCoupon", coupon);
	}

	public int updateCoupon(CouponVO coupon) {
		return sqlSessionTemplate.update("CouponDAO.updateCoupon", coupon);
	}

	public int deleteCouponInfo(String couponId) {
		return sqlSessionTemplate.delete("CouponDAO.deleteCouponInfo", couponId);
	}

	public int deleteCouponList(String couponId) {
		return sqlSessionTemplate.delete("CouponDAO.deleteCouponList", couponId);
	}

	public int setCoupon() {
		return sqlSessionTemplate.insert("CouponDAO.setCoupon");
	}

	public void setCouponLimit() {
		sqlSessionTemplate.update("CouponDAO.setCouponLimit");
	}
}

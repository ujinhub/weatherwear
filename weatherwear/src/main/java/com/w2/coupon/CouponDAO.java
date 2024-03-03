package com.w2.coupon;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.w2.client.ClientVO;
import com.w2.util.Search;

@Repository
public class CouponDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	/** 쿠폰 리스트 조회_사용자 */
	public List<CouponVO> getCouponList(String clientId) {
		return sqlSessionTemplate.selectList("CouponDAO.getCouponList", clientId);
	}
	
	/** 나의 쿠폰 내역 가져오기 (페이징)*/
	public List<CouponVO> getMyCouponList(HashMap<String, Object> param) {
		return sqlSessionTemplate.selectList("CouponDAO.getMyCouponList", param);
	}
	
	/** 마이페이지 쿠폰 리스트 개수 조회 */
	public int getMyCouponListCnt(String clientId) {
		return sqlSessionTemplate.selectOne("CouponDAO.getMyCouponListCnt", clientId);
	}

	/** 쿠폰 정보 조회 */
	public CouponVO getCouponInfo(String couponId) {
		return sqlSessionTemplate.selectOne("CouponDAO.getCouponInfo", couponId);
	}

	/** 쿠폰 등록_사용자 */
	public int myCouponReg(CouponVO vo) {
		return sqlSessionTemplate.insert("CouponDAO.myCouponReg", vo);
	}

	/** 쿠폰 리스트 조회_관리자 */
	public List<CouponVO> getCouponListAll(Search search) {
		return sqlSessionTemplate.selectList("CouponDAO.getCouponListAll", search);
	}

	/** 쿠폰 리스트 개수 조회_관리자 */
	public int getCouponListCnt(Search search) {
		return sqlSessionTemplate.selectOne("CouponDAO.getCouponListCnt", search);
	}

	/** 쿠폰 등록 */
	public int insertCoupon(CouponVO coupon) {
		return sqlSessionTemplate.insert("CouponDAO.insertCoupon", coupon);
	}

	/** 쿠폰 수정 */
	public int updateCoupon(CouponVO coupon) {
		return sqlSessionTemplate.update("CouponDAO.updateCoupon", coupon);
	}

	/** 쿠폰 삭제(정보) */
	public int deleteCouponInfo(String couponId) {
		return sqlSessionTemplate.delete("CouponDAO.deleteCouponInfo", couponId);
	}

	/** 쿠폰 삭제(리스트) */
	public int deleteCouponList(String couponId) {
		return sqlSessionTemplate.delete("CouponDAO.deleteCouponList", couponId);
	}

	/** 쿠폰 발급 */
	public int setCoupon() {
		return sqlSessionTemplate.insert("CouponDAO.setCoupon");
	}

	/** 쿠폰 만료 설정_스케줄 */
	public void setCouponLimit() {
		sqlSessionTemplate.update("CouponDAO.setCouponLimit");
	}

	public void insertCoupon(ClientVO client) {
		sqlSessionTemplate.insert("CouponDAO.insertNewUserCoupon", client);
	}
}

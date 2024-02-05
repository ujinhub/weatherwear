package com.w2.coupon;

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
	
	
}

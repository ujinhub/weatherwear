package com.w2.statistic;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.w2.admin.AdminVO;
import com.w2.product.ProductVO;

@Repository
public class StatisticDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/** 신규 주문 개수 조회 */
	public int getNewOrderCnt(AdminVO vo) {
		return sqlSessionTemplate.selectOne("StatisticDAO.getNewOrderCnt", vo);
	}

	/** 장바구니 사용자 수 조회 */
	public HashMap<String, Object> getCartUserCnt() {
		return sqlSessionTemplate.selectOne("StatisticDAO.getCartUserCnt");
	}

	/** 결제 수 조회 */
	public List<HashMap<String, Object>> getPaymentCnt() {
		return sqlSessionTemplate.selectList("StatisticDAO.getPaymentCnt");
	}

	/** 일별 주문 금액 조회 */
	public List<HashMap<String, Object>> getDayOrderPrice() {
		return sqlSessionTemplate.selectList("StatisticDAO.getDayOrderPrice");
	}

	/** 상품 누적 판매 조회 */
	public List<HashMap<String, Object>> getProductCnt() {
		return sqlSessionTemplate.selectList("StatisticDAO.getProductCnt");
	}

	/** 신규 회원 조회 */
	public int getNewUserCnt() {
		return sqlSessionTemplate.selectOne("StatisticDAO.getNewUserCnt");
	}

	/** 판매순 */
	public List<ProductVO> getBestProductSell() {
		return sqlSessionTemplate.selectList("StatisticDAO.getBestProductSell");
	}

	/** 조회순  */
	public List<ProductVO> getBestProductView() {
		return sqlSessionTemplate.selectList("StatisticDAO.getBestProductView");
	}
}

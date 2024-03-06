package com.w2.statistic.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.admin.AdminVO;
import com.w2.statistic.StatisticDAO;

@Service
public class StatisticServiceImpl implements StatisticService {
	@Autowired
	StatisticDAO statisticDAO;

	@Override	// 관리자 메인페이지 정보 조회
	public HashMap<String, Object> getStatisticInfo(AdminVO vo) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put("newOrderCnt", statisticDAO.getNewOrderCnt(vo)); // 관리자 최종 로그인 이후 업데이트된 신규 주문
		result.put("usingCartCnt", statisticDAO.getCartUserCnt());	// 장바구니 사용자(totalUser/user/unKnownUser)
		result.put("paymentCnt", statisticDAO.getPaymentCnt());		// 일 별 결제 건 수
		result.put("DayOrderPrice", statisticDAO.getDayOrderPrice()); // 일 별 주문 금액
		result.put("getProductCnt", statisticDAO.getProductCnt());	// 일주일간 판매 상품 누적 개수
		result.put("newUserCnt", statisticDAO.getNewUserCnt());		// 일 별 회원 가입 수
		result.put("bestSell", statisticDAO.getBestProductSell());		// 일 별 회원 가입 수
		result.put("bestView", statisticDAO.getBestProductView());		// 일 별 회원 가입 수
		return result;
	}
}

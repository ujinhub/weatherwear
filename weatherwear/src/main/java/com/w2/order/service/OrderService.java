package com.w2.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.w2.cart.CartVO;
import com.w2.client.ClientVO;
import com.w2.util.SearchOrderby;

public interface OrderService {

	List<HashMap<String, Object>> getOrderList(SearchOrderby search);	// 주문 목록 가져오기
	int getOrderListCnt(SearchOrderby search);							// 주문 목록 개수 가져오기
	int updateOrderStatus(List<Map<String, String>> checkList); 		// 주문 상태 수정
	int updateDeliverNum(List<Map<String, String>> checkList);			// 송장번호 수정
	
	// 사용자
	List<CartVO> getOrderProductList(Map<String, Object> orderMap);
	
	// 테스트
	ClientVO setClient(String string);
	int insertOrder(Map<String, Object> data);							// 주문 등록
	Map<String, Object> getOrderInfo(String orderId);					// 주문 상세
	List<Map<String, Object>> getOrderInfoList(String orderId);			// 주문 상품 목록
	
	/** 나의 주문 내역 */
	List<Map<String, Object>> getMyOrderList(HashMap<String, Object> param);
	int getMyOrderListCnt(HashMap<String, Object> param);
}

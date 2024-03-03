package com.w2.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.w2.cart.CartVO;
import com.w2.order.OrderVO;
import com.w2.util.SearchOrderby;

public interface OrderService {
	List<HashMap<String, Object>> getOrderList(SearchOrderby search);	// 주문 목록 조회
	int getOrderListCnt(SearchOrderby search);							// 주문 목록 개수 조회
	int updateOrderStatus(List<Map<String, String>> checkList); 		// 주문 상태 수정
	int updateDeliverNum(List<Map<String, String>> checkList);			// 송장번호 수정
	
	// 사용자
	List<CartVO> getOrderProductList(Map<String, Object> orderMap);		// 주문 상품 리스트 조회
	int insertImsiOrder(Map<String, Object> data);						// 주문 등록
	Map<String, Object> getOrderInfo(String orderId);					// 주문 상세
	List<Map<String, Object>> getOrderInfoList(String orderId);			// 주문 상품 목록
	int updateOrder(Map<String, Object> orderData);						// 주문 상태 수정
	int insertSwapRefund(Map<String, Object> requestInfo);				// 교환/환불 요청
	int insertPayment(Map<String, Object> data);						// 결제 정보 등록
	List<Map<String, Object>> getMyOrderList(HashMap<String, Object> param);	// 마이페이지 주문 리스트 조회
	int getMyOrderListCnt(HashMap<String, Object> param);				// 마이페이지 주문 리스트 개수 조회
	int updateSwapRefund(Map<String, Object> requestInfo);				// 교환/환불 정보 변경
	int deleteCancleOrderInfo(String orderId);							// 오류 주문 정보 삭제
	OrderVO checkUnKnownOrderInfo(HashMap<String, String> checkInfo);	// 비회원 정보 조회
	int insertDeliverInfo(Map<String, Object> data);					// 배송정보 등록
	void checkOrderDate();												// 구매 확정 업데이트
	List<String> getSwapRefundImage(String imageBy);
}

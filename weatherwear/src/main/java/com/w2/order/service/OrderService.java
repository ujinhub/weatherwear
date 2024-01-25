package com.w2.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.w2.util.SearchOrderby;

public interface OrderService {

	List<HashMap<String, Object>> getOrderList(SearchOrderby search);	// 주문 목록 가져오기
	int getOrderListCnt(SearchOrderby search);							// 주문 목록 개수 가져오기
	int updateOrderStatus(List<Map<String, String>> checkList); // 주문 상태 수정
	int updateDeliverNum(List<Map<String, String>> checkList);	// 송장번호 수정
}

package com.w2.delivery.service;

import java.util.HashMap;
import java.util.List;

import com.w2.delivery.DeliveryVO;
import com.w2.util.Search;

public interface DeliveryService {
	List<HashMap<String, Object>> getDeliveryList(Search search);	// 택배사 목록 조회
	int getDeliveryListCnt(Search search);							// 택배사 목록 개수 조회
	int insertDelivery(DeliveryVO deli);							// 택배사 신규 등록
	int updateDelivery(DeliveryVO deli);							// 택배사 수정
	int deleteDelivery(String deliveryId);							// 택배사 삭제
}

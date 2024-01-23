package com.w2.delivery.service;

import java.util.HashMap;
import java.util.List;

import com.w2.delivery.DeliveryVO;
import com.w2.util.Search;

public interface DeliveryService {

	List<HashMap<String, Object>> getProductList(Search search);	// 택배사 목록 가져오기
	int getProductListCnt(Search search);							// 택배사 목록 개수 가져오기
	int insert(DeliveryVO deli);									// 택배사 신규 등록
	int modify(DeliveryVO deli);									// 택배사 수정
	int modify(String deliveryId);									// 택배사 삭제

}

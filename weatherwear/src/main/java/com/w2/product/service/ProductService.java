package com.w2.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.w2.product.ProductVO;
import com.w2.util.SearchOrderby;

public interface ProductService {

	List<ProductVO> getProductList(SearchOrderby search);	// 상품 목록 가져오기
	int getProductListCnt(SearchOrderby search);			// 상품 목록 개수 가져오기
	int insert(Map<String, Object> pro);			// 상품 등록
	HashMap<String, Object> getProduct(String productId, Model model);			// 상품 상세
	int modify(Map<String, Object> pro);			// 상품 수정
	int delete(String productId);					// 상품 삭제
	int modifyProductStatus(List<Map<String, String>> checkList);	// 상품 상태 변경
}

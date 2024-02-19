package com.w2.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.w2.product.ProductVO;
import com.w2.util.SearchOrderby;

public interface ProductService {

	// 관리자
	List<ProductVO> getProductList(SearchOrderby search);	// 상품 목록 가져오기
	int getProductListCnt(SearchOrderby search);			// 상품 목록 개수 가져오기
	int insertProduct(Map<String, Object> pro);			// 상품 등록
	HashMap<String, Object> getProduct(String productId, Model model);			// 상품 상세
	int updateProduct(Map<String, Object> pro);			// 상품 수정
	int updateProductStatus(List<Map<String, String>> checkList);	// 상품 상태 변경
	int deleteProduct(String productId);					// 상품 삭제
	
	// 사용자
	List<ProductVO> getMainProductList(String string);		// 메인페이지 상품 리스트 가져오기
	int insertWishList(Map<String, Object> client);			// 위시리스트에 상품 추가
	int deleteWishList(Map<String, Object> client);			// 위시리스트 상품 삭제
	
	
	/**
	 * 권유진 추가 작업
	 */
	HashMap<String, Object> getProductInfo(String productId);		// 관심 상품, 최근본상품 정보 가져오기
}

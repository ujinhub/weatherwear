package com.w2.cart.service;

import java.util.List;

import com.w2.cart.CartVO;
import com.w2.product.OptionVO;

public interface CartService {
	List<CartVO> getCartList(CartVO cartvo);			// 장바구니 목록 불러오기
	int updateCart(CartVO cartvo);						// 장바구니 수량 변경
	int deleteCart(List<String> checkList);				// 장바구니 삭제
	int insertCart(List<CartVO> productList);			// 장바구니 추가
	List<OptionVO> checkStock(List<CartVO> cartList);	// 재고 조회
	int getCartListCnt(String clientId);				// 장바구니 상품 개수 가져오기
}

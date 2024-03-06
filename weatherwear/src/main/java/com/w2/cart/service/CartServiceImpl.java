package com.w2.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.cart.CartDAO;
import com.w2.cart.CartVO;
import com.w2.product.OptionVO;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO cartDAO;
	
	@Override	// 장바구니 목록 조회
	public List<CartVO> getCartList(CartVO cartvo) {
		return cartDAO.getCartList(cartvo);
	}

	@Override	// 장바구니 수량 변경
	public int updateCart(CartVO cartvo) {
		return cartDAO.updateCart(cartvo);
	}

	@Override	// 장바구니 삭제
	public int deleteCart(List<String> checkList) {
		return cartDAO.deleteCart(checkList);
	}

	@Override	// 장바구니 추가
	public int insertCart(List<CartVO> productList) {
		return cartDAO.insertCart(productList);
	}
	
	@Override	// 재고 조회
	public List<OptionVO> checkStock(List<CartVO> cartList) {
		return cartDAO.checkStock(cartList);
	}

	@Override	// 장바구니 상품 개수 조회
	public int getCartListCnt(String clientId) {
		return cartDAO.getCartListCnt(clientId);
	}

}

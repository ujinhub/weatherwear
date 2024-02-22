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
	
	@Override
	public List<CartVO> getCartList(CartVO cartvo) {
		return cartDAO.getCartList(cartvo);
	}

	@Override
	public int updateCart(CartVO cartvo) {
		return cartDAO.updateCart(cartvo);
	}

	@Override
	public int deleteCart(List<String> checkList) {
		return cartDAO.deleteCart(checkList);
	}

	@Override
	public int insertCart(List<CartVO> productList) {
		return cartDAO.insertCart(productList);
	}
	
	@Override
	public List<OptionVO> checkStock(List<CartVO> cartList) {
		return cartDAO.checkStock(cartList);
	}

	@Override
	public int getCartListCnt(String clientId) {
		return cartDAO.getCartListCnt(clientId);
	}

}

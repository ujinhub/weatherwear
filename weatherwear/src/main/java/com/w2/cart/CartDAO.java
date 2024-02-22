package com.w2.cart;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.w2.product.OptionVO;

@Repository
public class CartDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/** 장바구니에 담긴 상품 개수 가져오기 - 권유진 추가 */
	public int getCartListCnt(String cilentId) {
		return sqlSessionTemplate.selectOne("CartDAO.getCartListCnt", cilentId);
	}
	
	public List<CartVO> getCartList(CartVO cartvo) {
		return sqlSessionTemplate.selectList("CartDAO.getCartList", cartvo);
	}

	public int updateCart(CartVO cartvo) {
		return sqlSessionTemplate.update("CartDAO.updateCart", cartvo);
	}

	public int deleteCart(List<String> checkList) {
		return sqlSessionTemplate.delete("CartDAO.deleteCart", checkList);
	}

	public int insertCart(List<CartVO> productList) {
		String ckId = null;
		String clientId = null;
		
		for(int i=0; i<productList.size(); i++) {
			// 회원/비회원 구분
			if(productList.get(0).getClientId() != null) {
				clientId = productList.get(0).getClientId();
			} else if(productList.get(0).getCookieId() != null) {
				ckId = productList.get(0).getCookieId();
			}
			
			CartVO cartvo = (CartVO)productList.get(i);
			
			if(i>0) {
				if(clientId != null) {
					productList.get(i).setClientId(clientId);
				} else if (ckId != null) {
					productList.get(i).setCookieId(ckId);
				}	
			}
			
			CartVO checkCart = sqlSessionTemplate.selectOne("CartDAO.checkCart", cartvo);

			// 같은 쿠키값 가진 상품 만료시간 업데이트
			if(cartvo.getCookieId() != null) {
				sqlSessionTemplate.update("CartDAO.updateCookie", cartvo);
			}
			
			// 이미 장바구니에 있는 상품인 경우
			if (checkCart != null) {
				cartvo.setCartId(checkCart.getCartId());
				cartvo.setCartCnt(checkCart.getCartCnt()+cartvo.getCartCnt());
				sqlSessionTemplate.update("CartDAO.updateCart", cartvo);
			}else {
				sqlSessionTemplate.insert("CartDAO.insertCart", cartvo);
			}
		}
		return 1;
	}
	
	// 재고 확인
	public List<OptionVO> checkStock(List<CartVO> cartList) {
		return sqlSessionTemplate.selectList("CartDAO.checkStock", cartList);
	}

	// 만료된 쿠키 확인
	public void checkCookieLimit() {
		sqlSessionTemplate.delete("CartDAO.checkCookieLimit");
	}
}

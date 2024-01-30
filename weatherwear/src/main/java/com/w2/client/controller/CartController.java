package com.w2.client.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.w2.cart.CartVO;
import com.w2.cart.service.CartService;
import com.w2.util.ClientCookie;
import com.w2.util.ResponseDTO;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	
	/**
	 * 장바구니 화면 호출
	 * @return
	 */
	@RequestMapping("cart.do")
	public String cart(HttpServletRequest request, HttpSession session, Model model, CartVO cartvo) {
		// 비로그인 상태인 경우
		if((String)session.getAttribute("session") == null) {
			Cookie cookie = WebUtils.getCookie(request, "clientCookie");
			if (cookie != null) {
				cartvo.setCookieId((String)cookie.getValue());
			}
		} else {
			cartvo.setClientId((String)session.getAttribute("session"));
		}
		
		model.addAttribute("cartList", cartService.getCartList(cartvo));
		return "cart";
	}
	
	/** 장바구니에 상품 추가 */
	@PostMapping("cartInsert.do")
	public void cartInsert(@RequestBody List<CartVO> productList, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 비로그인 상태인 경우
		if(session.getAttribute("session") == null) {
			String ckId = ClientCookie.setCookie(request, response);
			productList.get(0).setCookieId(ckId);
		} else { // 로그인 상태인 경우
			productList.get(0).setClientId((String)session.getAttribute("session"));
		}
		
		int result = cartService.insertCart(productList);

		response.setContentType("application/json");
		response.getWriter().write(String.valueOf(result));
	}
	
	/** 장바구니 수량 변경 */
	@PostMapping("cartUpdate.do")
	public void cartUpdate(@RequestBody Map<String, String> data, HttpSession session, CartVO cartvo, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		setUser(session, request, response, cartvo);
		cartvo.setCartId((String)data.get("cartId"));
		cartvo.setCartCnt(Integer.parseInt(data.get("cnt")));
		
		int result = cartService.updateCart(cartvo);

		setResponse(response, result, "application/json");
	}
	
	/** 장바구니 상품 삭제 */
	@ResponseBody
	@PostMapping("cartDelete.do")
	public ResponseDTO<String> cartDelete(@RequestBody List<String> checkList, HttpSession session, CartVO cartvo, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer statusCode = HttpStatus.OK.value();
		int code;
		String resultCode;
		String msg;
		
		try {
			int result = cartService.deleteCart(checkList);
			if(result > 0) {
				code = 1;
				resultCode = "success";
				msg = "삭제되었습니다.";
			} else {
				code = -1;
				resultCode = "fail";
				msg = "오류가 발생했습니다. 다시 시도해주세요";
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생했습니다. 다시 시도해주세요";
		}
		return new ResponseDTO<String>(statusCode, code, resultCode, msg, null);
	}
	
	// 사용자 확인(회원/비회원)
	public CartVO setUser(HttpSession session, HttpServletRequest request, HttpServletResponse response, CartVO cartvo) {
		// 비로그인 상태인 경우
		if(session.getAttribute("session") == null) {
			String ckId = ClientCookie.setCookie(request, response);
			
			cartvo.setCookieId(ckId);
		} else { // 로그인 상태인 경우
			cartvo.setClientId((String)session.getAttribute("session"));
		}
		return cartvo;
	}
	
	// ajax 응답
	public HttpServletResponse setResponse(HttpServletResponse response, Object result, String type) throws IOException {
		response.setContentType(type);
		response.getWriter().write(String.valueOf(result));
		
		return response;
	}
}

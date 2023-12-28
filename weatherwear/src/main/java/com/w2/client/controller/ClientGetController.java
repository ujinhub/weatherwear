package com.w2.client.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.w2.client.ClientService;
import com.w2.client.ClientVO;
import com.w2.client.cart.ClientCartDTO;
import com.w2.client.cart.ClientCartService;
import com.w2.client.cart.ClientCartVO;
import com.w2.client.product.ClientProductService;

@Controller
public class ClientGetController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ClientProductService productService;
	
	@Autowired
	private ClientCartService cartService;
	
	/** 사용자 메인페이지 */
	@RequestMapping("clientMain.do")
	public String main() {
		return "client_main";
	}
	
	/** 상품 리스트 페이지 */
	@RequestMapping("clientProductList.do")
	public String clientProductList(@RequestParam(value = "page", required = false)Integer page, 
								@RequestParam(value="ordertype", required=false) String ordertype, 
								@RequestParam(value="gubun", required=false)String gubun, Model model) {
		System.out.println("1. [ Client Get Controller ] productList");
		
		HashMap<String, Object> check = new HashMap<String, Object>();
		if(page == null || page == 0) {
			check.put("page", 1);
		} else {
			check.put("page", (int)page);
		}
		
		check.put("table", "product");
		check.put("ordertype", (String)ordertype);
		check.put("gubun", (String)gubun);
		
		
		List<HashMap<String, Object>> productList = productService.clientProductList(check, model);
		
		model.addAttribute("productList", productList);
		
		return "client_productList";
	}
	
	/** 상품 상세 페이지 */
	@RequestMapping("clientProductInfo.do")
	public String clientProductInfo(@RequestParam(value = "proId", required = false)String proId, Model model) {
		System.out.println("1. [ Client Post Controller ] clientProductInfo");
		
		model.addAttribute("product", productService.clientProductInfo(proId));
		
		return "client_productInfo";
	}
	
	/** 사용자 로그인 페이지 */
	@RequestMapping("clientLogin.do")
	public String login() {
		return "client_login";
	}

	/** 로그아웃 요청 */
	@RequestMapping("clientLogout.do")
	public String logout(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/clientMain.do";
	}
	
	/** 사용자 정보 찾기 페이지 */
	@RequestMapping("clientFindInfo.do")
	public String findInfo() {
		return "client_findInfo";
	}

	/** 회원가입 페이지 */
	@RequestMapping("clientSignup.do")
	public String signup() {
		return "client_signup";
	}
	
	/** 커뮤니티 페이지 */
	@RequestMapping("clientCommunity.do")
	public String community() {
		return "client_community";
	}
	
	/** 마이페이지 */
	@RequestMapping("clientMypage.do")
	public String mypage(HttpSession session, ClientVO client, Model model) {
		System.out.println("1. [ Client Get Controller ] myPage ");
		
		HashMap<String, Object> myinfo = clientService.clientSetMypage((String)session.getAttribute("session"));
		model.addAttribute("myinfo", myinfo);
		
		return "client_mypage";
	}
	

	// ========== 2023.12.26 ujin ==========
	/** 장바구니 페이지	*/
	@RequestMapping("clientCart.do")
	public String clientCart(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		String cId = null;
		String ckId = null;
		HttpSession session = request.getSession(false);
		
		if(session.getAttribute("session") != null) {
			cId = session.getAttribute("session").toString();
			ClientCartVO cart = new ClientCartVO();
			cart.setCId(cId);
			
			model.addAttribute("cartList", cartService.getClientCart(cart));
		} else {
			// 비회원
			Cookie cookie = WebUtils.getCookie(request, "cartCookie");
			if(cookie != null) {
				ckId = cookie.getValue();
				ClientCartVO cart = new ClientCartVO();
				cart.setCkId(ckId);
				
				List<ClientCartDTO> getCartList = cartService.getClientCart(cart);
				if(getCartList.size() == 0) {
					// 쿠키 삭제
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				} 
				model.addAttribute("cartList", getCartList);
			}
		}
		
		return "client_cart";
	}
	
	/** 장바구니 동일한 상품 존재 여부 확인 */
	@ResponseBody
	@RequestMapping("checkClientCart.do")
	public String checkClientCart(@RequestBody List<ClientCartVO> cartList, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		String cId = null;
		String ckId = null;
		
		if(session.getAttribute("session") != null) {
			cId = session.getAttribute("session").toString();
			
		} else {
			// 비회원
			Cookie cookie = WebUtils.getCookie(request, "cartCookie");
			
			if(cookie != null) {
				ckId = cookie.getValue();
			}
		}
		
		for(int i = 0; i < cartList.size(); i++) {
			cartList.get(i).setCId(cId);
			cartList.get(i).setCkId(ckId);
			ClientCartVO cart = cartService.checkClientCart(cartList.get(i));
			
			if(cart != null) {
				return "exist";
			}
		}
		return null;
	}
	
}
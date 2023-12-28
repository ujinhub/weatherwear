package com.w2.client.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.w2.client.ClientService;
import com.w2.client.ClientVO;
import com.w2.client.cart.ClientCartService;
import com.w2.client.cart.ClientCartVO;

@Controller
public class ClientPostController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ClientCartService cartService;
	
	@Autowired // 비밀번호 암호화
	private BCryptPasswordEncoder pwden;
	
	/** 로그인 */
	@PostMapping("clientLogin.do")
	public String clientLogin(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("1. [ Client Post Controller ] clientLogin");
		
		HttpSession session = request.getSession(false);
		
		String id = request.getParameter("clientId");
		String pwd = request.getParameter("clientPwd");
		System.out.println("id : " + id + ", pwd : " + pwd);
		
		ClientVO result = clientService.getClient(id);
		
		if(result != null) {
			System.out.println("4. [ Controller ] 아이디 존재");
			
			if(pwden.matches(pwd, result.getClientPwd())) {
				System.out.println("5. [ Controller ] 비밀번호 일치");
				
				session.setAttribute("session", id);
				System.out.println("6. [ Controller ] 세션 설정");
				
				/** 세션 값 확인 방법
				 * session.getAttribute("session");
				 * session.getValue("session");
				 */
				
				// 최근 로그인 날짜 변경
				clientService.setLogDate(id);
				
				// ========== 2023.12.26 ujin ==========
				// 로그인시 비회원 장바구니 회원 장바구니로 이동
				Cookie cookie = WebUtils.getCookie(request, "cartCookie");
				
				if(cookie != null) {
					String ckId = cookie.getValue();
					String cId = id;
					
					ClientCartVO cart = new ClientCartVO();
					cart.setCId(cId);
					cart.setCkId(ckId);
					
					cartService.updateClientCart(cart);
					
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				
				return "redirect:/clientMain.do";
			}
			System.out.println("5. [ Controller ] 비밀번호 불일치");
		} else {
			System.out.println("4. [ Controller ] 아이디 없음");
		}
		return "redirect:/clientLogin.do";
	}
	
	/** 아이디 찾기 */
	@PostMapping("clientFindId.do")
	public void clientFindId(@RequestBody Map<String, String> data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("1. [ Client Post Controller ] clientFindId");
		
		String type = request.getParameter("type");
		String clientName = data.get("clientName");
		String keyword = data.get("keyword");
		
		String id = clientService.clientFindId(type, clientName, keyword);
		System.out.println("4. [ Controller ] 결과 : " + id);
		
		response.setContentType("application/json");
		response.getWriter().write(String.valueOf(id));
	}
	
	/** 비밀번호 찾기 */
	@PostMapping("clientFindPwd.do")
	public void clientFindPwd(@RequestBody Map<String, String> data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("1. [ Client Post Controller ] clientFindPwd");
		
		String type = request.getParameter("type");
		String clientName = data.get("clientName");
		String clientId = data.get("clientId");
		String keyword = data.get("keyword");
		
		int result = clientService.clientFindPwd(type, clientName, clientId, keyword);
		System.out.println("4. [ Controller ] 결과 : " + result);
		
		response.setContentType("application/json");
		response.getWriter().write(String.valueOf(result));
	}
	
	/** 비밀번호 변경 */
	@PostMapping("clientSetPwd.do")
	public void clientSetPwd(@RequestBody Map<String, String> data, HttpServletResponse response, ClientVO client) throws Exception {
		System.out.println("1. [ Client Post Controller ] clientSetPwd");
		
		client.setClientId(data.get("clientId"));
		client.setClientPwd(pwden.encode(data.get("clientPwd")));
		
		int result = clientService.clientSetPwd(client);
		System.out.println("4. [ Controller ] 결과 : " + result);
		
		response.setContentType("aaplication/json");
		response.getWriter().write(String.valueOf(result));
	}
	
	/** 중복 체크 */
	@PostMapping("clientCheck.do")
	public void clientCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("1. [ Client Post Controller ] clientCheck");
		
		String comp = request.getParameter("comp");
		String with = request.getParameter("checkWith");
		
		System.err.println("comp : " + comp + ", with : " + with);
		
		int check = clientService.clientCheckService(comp, with);
		
		System.out.println("5. [ Controller ] 결과 : " + check);
		
		response.setContentType("application/json");
		response.getWriter().write(String.valueOf(check));
	}
	
	/** 회원 가입 */
	@PostMapping("clientSignup.do")
	public String clientSignup(HttpServletRequest request, ClientVO client, Model model) {
		System.out.println("1. [ Client Post Controller ] clientSignup");
		System.out.println("EmailCheck : " + request.getParameter("clientEmailCheck"));
		
		// 신규 회원 기본 설정
		client.setGradeId("S");
		client.setClientPoint(3000);
		
		if(request.getParameter("clientEmailCheck") != null) {
			client.setClientEmailCheck("Y");
		} else {
			client.setClientEmailCheck("N");
		}
		
		// 비밀번호 암호화
		client.setClientPwd(pwden.encode(client.getClientPwd()));
		
		int result = clientService.clientSignup(client);
		model.addAttribute("result", result);
		
		if(result == 1 ) {
			System.out.println("4. [ Controller ] 가입 성공");
			return "client_login";
		}
		
		System.out.println("4. [ Controller ] 가입 실패");
		return "client_signup";
	}
	
	
	// ========== 2023.12.26 ujin ==========
	/** 장바구니 담기 */
	@ResponseBody
	@PostMapping("insertClientCart.do")
	public String insertClientCart(@RequestBody List<ClientCartVO> cartList, HttpServletRequest request, HttpServletResponse response) {
		String cId = null;
		String ckId = null;
		HttpSession session = request.getSession(false);
		
		if(session.getAttribute("session") != null) {
			// 회원
			cId = session.getAttribute("session").toString();
		} else {
			// 비회원
			Cookie cookie = WebUtils.getCookie(request, "cartCookie");
			
			if(cookie == null) {
				// 쿠키 생성
				ckId = RandomStringUtils.random(10, true, true);
				Cookie cartCookie = new Cookie("cartCookie", ckId);
				cartCookie.setPath("/");
				cartCookie.setMaxAge(60 * 60 * 24 * 2);
				response.addCookie(cartCookie);
			} else {
				ckId = cookie.getValue();
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 2);
				response.addCookie(cookie);
			}
		}
		
		for(int i = 0; i < cartList.size(); i++) {
		
			cartList.get(i).setCId(cId);
			cartList.get(i).setCkId(ckId);
			
			ClientCartVO cart = cartService.checkClientCart(cartList.get(i));
			if(cart != null) {
				cart.setCaCnt(cart.getCaCnt() + cartList.get(i).getCaCnt());
				// 구매 가능 수량 체크
				if(cart.getCaCnt() > cartService.checkProCnt(cart.getOpId())) {
					return "isOver";
				}
				cartService.updateClientCartCnt(cart);
			} else {
				cartService.insertClientCart(cartList.get(i));
			}
		}
		
		return "success";
	}
	
	/** 장바구니 업데이트 */
	@ResponseBody
	@PostMapping("updateClientCartCnt.do")
	public String updateClientCartCnt(@RequestBody ClientCartVO cart) {
		cartService.updateClientCartCnt(cart);
		return "success";
	}
	
	/** 장바구니 삭제 */
	@ResponseBody
	@PostMapping("deleteClientCart.do")
	public String deleteClientCart(@RequestBody List<ClientCartVO> cartList) {
		for(int i = 0; i < cartList.size(); i++) {
			cartService.deleteClientCart(cartList.get(i));
		}
		
		return "client_cart";
	}
}
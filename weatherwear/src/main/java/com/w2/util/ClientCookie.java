package com.w2.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.WebUtils;

public class ClientCookie {
	// 쿠키 설정
	public static String setCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = WebUtils.getCookie(request, "clientCookie");
		String ckId = null;
		
		if(cookie == null) { // 쿠키가 생성된 적 없는 경우
			ckId = RandomString.setRandomString(15, "word");
			Cookie newCookie = new Cookie("clientCookie", ckId);
			newCookie.setComment("비회원 아이디");
			newCookie.setPath("/w2/");
			newCookie.setMaxAge(60*60*24*2);
			response.addCookie(newCookie);
		} else { // 이미 생성된 쿠키가 있는 경우
			ckId = cookie.getValue();
			
			// 쿠키 세션 재설정
			cookie.setMaxAge(60*60*24*2);
			response.addCookie(cookie);
		}
		return ckId;
	}
	
	// 쿠키 존재 여부 확인
	public static int checkCookie(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("[ ClientCookie ] checkCookie");
		Cookie cookie = WebUtils.getCookie(request, "clientCookie");
		
		if(cookie == null) { // 쿠키가 생성된 적 없는 경우
			return 0;
		}
		return 1;
	}
	
	// 쿠키 제거
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("[ ClientCookie ] removeCookie");

		Cookie cookie = WebUtils.getCookie(request, "clientCookie");
		if(cookie != null) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
}

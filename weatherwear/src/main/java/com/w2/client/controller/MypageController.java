package com.w2.client.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MypageController {
	
	@RequestMapping("mypage.do")
	public String mypage(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			return "login";
		}
		
		if(session.getAttribute("userInfo") == null) {
			return "login";
		}
		
		return "mypage/mypage";
	}
}

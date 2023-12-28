package com.w2.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminGetController {
	
	@RequestMapping("main.mdo")
	public String main() {
		return "admin_main";
	}
	
	@RequestMapping("login.mdo")
	public String login() {
		return "admin_login";
	}
}

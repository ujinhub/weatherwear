package com.w2.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientOrderController {

	@RequestMapping("noClientOrder.do")
	public String noClientOrderView() {
		return "order/noClientOrder";
	}
}

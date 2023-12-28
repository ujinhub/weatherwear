package com.w2.test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.w2.client.cart.ClientCartService;
import com.w2.client.cart.ClientCartVO;

@Controller
public class TestController {
	
	// ajax_GET
	@RequestMapping("ajax1.to")
	public void ajaxTest1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("get ajax");
		String result = request.getParameter("input1");
		System.out.println("result : " + result);
		String re = "전송된 값 : " + result + ", 길이 : " + result.length();
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(re);
	}

	// ajax_POST
	@PostMapping("ajax2.to")
	public void ajaxTest2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("post ajax");
		String result1 = request.getParameter("input2_1");
		String result2 = request.getParameter("input2_2");
		System.out.println("result1 : " + result1);
		System.out.println("result2 : " + result2);
		String re = "1. 전송된 값 : " + result1 + ", 길이 : " + result1.length()
				+ "/ 2. 전송된 값 : " + result2 + ", 길이 : " + result2.length();
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(re);
	}	

	// ajax_GET_다른페이지로 값 넘김-1
	@RequestMapping("ajax3.to")
	public void ajaxTest3(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("get 2 ajax to other page");
		
		String result = request.getParameter("input3");
		System.out.println("result : " + result);
		String re = "전송된 값 : " + result + ", 길이 : " + result.length();
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(re);
	}

	// ajax_GET_다른페이지로 값 넘김-2
	@RequestMapping("ajax3to.to")
	public String ajaxTest3to(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Move page");
		System.out.println("request : " + request.getParameter("result"));

		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(request.getParameter("result"));
		return "ajaxResponse";
	}

	
}

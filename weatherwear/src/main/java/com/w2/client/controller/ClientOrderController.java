package com.w2.client.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.w2.cart.CartVO;
import com.w2.client.ClientVO;
import com.w2.clientAddress.ClientAddressVO;
import com.w2.clientAddress.service.ClientAddressService;
import com.w2.coupon.service.CouponService;
import com.w2.order.service.OrderService;
import com.w2.util.ClientCookie;
import com.w2.util.RandomString;
import com.w2.util.ResponseDTO;

@Controller
public class ClientOrderController {	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ClientAddressService addressService;
	
	@Autowired
	CouponService couponService;

	@RequestMapping("noClientOrder.do")
	public String noClientOrderView() {
		return "order/noClientOrder";
	}

	/**
	 * 주문 화면 호출
	 * @return
	 */
	@RequestMapping("orderRegister.do")
	public String orderRegister(@Param("caIdList") String cartList, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model, CartVO cartvo) {
		String[] list = cartList.split(",");
		List<String> cartIdList = Arrays.asList(list);
		
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("cartIdList", cartIdList);
		
		if(session.getAttribute("userInfo") == null) {
			String cookieId = ClientCookie.setCookie(request, response);
			orderMap.put("cookieId", cookieId);
		} else {
			ClientVO user= (ClientVO)session.getAttribute("userInfo");
			orderMap.put("clientId", user.getClientId());
			model.addAttribute("baseAddress", addressService.getBaseAddress(user.getClientId()));
			model.addAttribute("couponList", couponService.getCouponList(user.getClientId()));
		}
		
		model.addAttribute("orderList", orderService.getOrderProductList(orderMap));
		return "order/orderRegister";
	}
	
	/**
	 * 주문 상세 페이지 호출
	 * @return
	 */
	@RequestMapping("orderInfo.do")
	public String orderInfo(@Param("orderId") String orderId, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model, CartVO cartvo) {
		model.addAttribute("orderInfo", orderService.getOrderInfo(orderId));
		model.addAttribute("orderProductList", orderService.getOrderInfoList(orderId));
		return "order/orderInfo";
	}

	/** 주문 추가 */
	@ResponseBody
	@PostMapping("orderRegisterProc.do")
	public ResponseDTO<String> orderRegisterProc(@RequestBody Map<String, Object> data) {
		Integer statusCode = HttpStatus.OK.value();
		int code = 0;
		String resultCode;
		String msg;
		String orderId;
		
		Map<String, Object> addressInfo = (Map<String, Object>) data.get("addressInfo");
		if(addressInfo.get("addressId") == null || addressInfo.get("addressId") == "") {
			addressInfo.put("addressId", "AD" + RandomString.createFileName() + RandomString.setRandomString(5, "number"));
			data.put("addressInfo", addressInfo);
		}
		
		data.put("orderId", "OD" + RandomString.createFileName() + RandomString.setRandomString(8, "number"));
		
		try {
			int result = orderService.insertOrder(data);
			orderId = (String)data.get("orderId");
			if(result > 0) {
				code = 1;
				resultCode = "success";
				msg = "주문이 완료되었습니다.";
			} else {
				code = -1;
				resultCode = "fail";
				msg = "주문중 오류가 발생했습니다.";
			}
		} catch (Exception e) {
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생했습니다.";
			orderId = null;
		}
		
		return new ResponseDTO<String>(statusCode, code, resultCode, msg, orderId);
	}
}

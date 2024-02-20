package com.w2.client.controller;

import java.io.IOException;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.w2.board.ReviewVO;
import com.w2.board.service.ReviewService;
import com.w2.cart.CartVO;
import com.w2.client.ClientVO;
import com.w2.client.service.ClientService;
import com.w2.clientAddress.service.ClientAddressService;
import com.w2.coupon.service.CouponService;
import com.w2.file.service.ImageService;
import com.w2.order.OrderInfoVO;
import com.w2.order.service.OrderService;
import com.w2.util.ClientCookie;
import com.w2.util.RandomString;
import com.w2.util.ResponseDTO;

@Controller
public class ClientOrderController {	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ClientAddressService addressService;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ImageService imageService;
	
	@RequestMapping("noClientOrder.do")
	public String noClientOrderView() {
		return "order/noClientOrder";
	}

	@RequestMapping("ttt.do")
	public String ttt(@Param("ID")String id) {
		System.err.println(">>> id : " + id);
		return "test";
	}
	
	/**
	 * 주문 화면 호출
	 * @return
	 */
	@RequestMapping("orderRegister.do")
	public String orderRegister(@Param("caIdList") String cartList, @Param("cartOk") String cartOk,HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model, CartVO cartvo) {
		String userId = "";
		String[] list = cartList.split(",");
		List<String> cartIdList = Arrays.asList(list);
		
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("cartIdList", cartIdList);
		
		if(session.getAttribute("userInfo") == null) {
			if(cartOk != null && cartOk.equals("Y")) {
				String cookieId = ClientCookie.setCookie(request, response);
				orderMap.put("cookieId", cookieId);
				session.setAttribute("cookieId", cookieId);
			}
			if(session.getAttribute("cookieId") == null) {
				return "order/orderLogin";
			}
		} else {
			ClientVO user= (ClientVO)session.getAttribute("userInfo");
			userId = user.getClientId();
			orderMap.put("clientId", user.getClientId());
			model.addAttribute("baseAddress", addressService.getBaseAddress(user.getClientId()));
			model.addAttribute("couponList", couponService.getCouponList(user.getClientId()));
		}
		
		List<CartVO> orderList = orderService.getOrderProductList(orderMap);
		if(orderList.size() <= 0) {
			return "redirect:login.do";
		}else {
			model.addAttribute("orderList", orderList);
		}
		
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
	public ResponseDTO<String> orderRegisterProc(@RequestBody Map<String, Object> data, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Integer statusCode = HttpStatus.OK.value();
		int code = 0;
		String resultCode;
		String msg;
		String orderId = "OD" + RandomString.createFileName() + RandomString.setRandomString(8, "number");
		
		Map<String, Object> addressInfo = (Map<String, Object>) data.get("addressInfo");
		
		// 배송지Id 설정
		if(addressInfo.get("addressId") == null || addressInfo.get("addressId") == "") {
			addressInfo.put("addressId", "AD" + RandomString.createFileName() + RandomString.setRandomString(5, "number"));
			data.put("addressInfo", addressInfo);
		}
		
		// 주문번호 설정
		data.put("orderId", orderId);

		if(session.getAttribute("userInfo") == null) {
			data.put("cookieId", (String)session.getAttribute("cookieId"));
		} 
		
		try {
			System.err.println("6. try 시작");
			int result = orderService.insertImsiOrder(data);
			
			System.err.println("14. insertImsiOrder : " + result);
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
	
	/** 결제 정보 등록 */
	@ResponseBody
	@PostMapping("paymentInsert.do")
	public ResponseDTO<String> paymentInsert(@RequestBody Map<String, Object> data) throws IOException {
		Integer statusCode = HttpStatus.OK.value();
		int code = 0;
		String resultCode;
		String msg;
		String orderId = (String)data.get("orderId");

		try {
			System.err.println("31. try 시작");
			int result = orderService.insertPayment(data);
			System.err.println("32. insertPayment :  " + result);
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
		}
		return new ResponseDTO<String>(statusCode, code, resultCode, msg, orderId);
	}
	
	/**
	 * 비회원 로그인 프로세스
	 * @param vo: 사용자 정보
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("loginOrder.do")
	public String loginOrder(ClientVO vo, HttpServletRequest request, HttpServletResponse response, Model model, @Param("cartList") String cartList) {
		HttpSession session = request.getSession();
		ClientVO client = clientService.getClient(vo);
		
		if(client != null) {
			// 아이디 존재
			if(!passwordEncoder.matches(vo.getClientPwd(), client.getClientPwd())) {
				// 비밀번호 불일치
				model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
				return "order/orderLogin";
			} else {
				// 로그인 성공
				session.setAttribute("userInfo", client);
				session.setMaxInactiveInterval(10*60);
				
				// 쿠키 존재하는 경우 쿠키 삭제
				if(ClientCookie.checkCookie(request, response) == 1) {
					clientService.changeCookieSetId(WebUtils.getCookie(request, "clientCookie").getValue(), vo.getClientId());
					ClientCookie.removeCookie(request, response);
				}
				return "redirect:orderRegister.do?cartList="+cartList;
			}
		} else {
			// 아이디 불일치
			model.addAttribute("msg", "아이디가 존재하지 않습니다.");
			return "order/orderLogin";
		}
	}

	/** 주문 수정 */
	@ResponseBody
	@PostMapping("updateOrder.do")
	public ResponseDTO<OrderInfoVO> updateOrder(OrderInfoVO order, String orderStatus, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Integer statusCode = HttpStatus.OK.value();
		int code = 0;
		String resultCode;
		String msg;
		
		Map<String, Object> orderData = new HashMap<String, Object>();
		orderData.put("order", order);
		orderData.put("orderStatus", orderStatus);
		
		if(session.getAttribute("userInfo") == null) {
			orderData.put("cookieId", (String)session.getAttribute("cookieId"));
		} else {
			ClientVO client = (ClientVO) session.getAttribute("userInfo");
			orderData.put("clientId", client.getClientId());
		}
		
		System.err.println("orderData : " + orderData);
		
		try {
			int result = orderService.updateOrder(orderData);
			if(result > 0) {
				code = 1;
				resultCode = "success";
				msg = "완료되었습니다.";
			} else {
				code = -1;
				resultCode = "fail";
				msg = "오류가 발생했습니다.";
			}
		} catch (Exception e) {
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생했습니다.";
		}
		
		return new ResponseDTO<OrderInfoVO>(statusCode, code, resultCode, msg, order);
	}

	/** 교환, 환불 요청 */
	@ResponseBody
	@PostMapping("insertSwapRefund.do")
	public ResponseDTO<String> insertSwapRefund(String requestWhat, String orderId, String optionId, String reason, String email, 
												String deliverWay, int cost, String costMtd, String status, String bankId, String refundBankNum, 
												HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Integer statusCode = HttpStatus.OK.value();
		int code = 0;
		String resultCode;
		String msg;
		String id = "";
		
		Map<String, Object> requestInfo = new HashMap<String, Object>();
		requestInfo.put("requestWhat", requestWhat);
		requestInfo.put("orderId", orderId);
		requestInfo.put("reason", reason);
		requestInfo.put("email", email);
		requestInfo.put("deliverWay", deliverWay);
		requestInfo.put("cost", cost);
		requestInfo.put("costMtd", costMtd);
		requestInfo.put("status", status);

		if(requestWhat.equals("swap")) {
			id += "SW";
		} else if(requestWhat.equals("refund")) {
			id += "RF";
			requestInfo.put("bankId", bankId);
			requestInfo.put("refundBankNum", refundBankNum);
		}
		id += RandomString.createFileName() + RandomString.setRandomString(5, "number");
		requestInfo.put("id", id);
		
		if(session.getAttribute("userInfo") != null) {
			ClientVO client = (ClientVO) session.getAttribute("userInfo");
			requestInfo.put("clientId", client.getClientId());
		}
		
		try {
			int result = orderService.insertSwapRefund(requestInfo);
			if(result > 0) {
				code = 1;
				resultCode = "success";
				msg = "주문 상태가 변경되었습니다.";
			} else {
				code = -1;
				resultCode = "fail";
				msg = "오류가 발생했습니다.";
			}
		} catch (Exception e) {
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생했습니다.";
		}
		
		return new ResponseDTO<String>(statusCode, code, resultCode, msg, id);
	}
	
	/** 리뷰 등록 */
	@ResponseBody
	@PostMapping("insertReview.do")
	public ResponseDTO<String> insertReview(ReviewVO review, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Integer statusCode = HttpStatus.OK.value();
		int code = 0;
		String resultCode;
		String msg;
		String id = "";

		ClientVO client = (ClientVO)session.getAttribute("userInfo");

		review.setClientId(client.getClientId());
		review.setReviewId("RE" + RandomString.createFileName() + RandomString.setRandomString(5, "number"));

		try {
			int result = reviewService.insertReview(review);
			System.err.println("누가 먼저 실행되냐고");
			if(result > 0) {
				code = 1;
				resultCode = "success";
				msg = "주문 상태가 변경되었습니다.";
			} else {
				code = -1;
				resultCode = "fail";
				msg = "오류가 발생했습니다.";
			}
		} catch (Exception e) {
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생했습니다.";
		}
		return new ResponseDTO<String>(statusCode, code, resultCode, msg, id);
	}
	
	/** 리뷰 조회 */
	@ResponseBody
	@PostMapping("getReviewInfo.do")
	public ResponseDTO<HashMap<String, Object>> getReviewInfo(String reviewId, HttpSession session) {
		Integer statusCode = HttpStatus.OK.value();
		int code = 0;
		String resultCode;
		String msg;

		HashMap<String, Object> result = new HashMap<String, Object>();
		ReviewVO review = reviewService.getReviewInfo(reviewId);
		if(review != null) {
			code = 1;
			resultCode = "success";
			msg = "조회가 완료되었습니다.";
			
			result.put("review", review);
			if(review.getReviewStatus().equals("포토")) {
				result.put("reviewImage", reviewService.getReviewImage(review));
			}
			System.err.println("result : " + result);
		} else {
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생했습니다.";
		}
		return new ResponseDTO<HashMap<String, Object>>(statusCode, code, resultCode, msg, result);
	}
	
	/** 리뷰 삭제 */
	@ResponseBody
	@PostMapping("deleteReview.do")
	public ResponseDTO<String> deleteReview(String reviewId, HttpSession session) {
		Integer statusCode = HttpStatus.OK.value();
		int code = 0;
		String resultCode;
		String msg;

		ClientVO client = (ClientVO)session.getAttribute("userInfo");
		ReviewVO review = reviewService.getReviewInfo(reviewId);
		if(review.getClientId().equals(client.getClientId())) {
			int result = reviewService.deleteReview(reviewId);
			if(result > 0) {
				result = imageService.deleteReviewImage(review.getOrderId() + "_" + review.getOptionId());
				code = 1;
				resultCode = "success";
				msg = "리뷰가 삭제되었습니다.";
			} else {
				code = -1;
				resultCode = "fail";
				msg = "오류가 발생했습니다.";
			}
		} else {
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생했습니다.";
		}
		return new ResponseDTO<String>(statusCode, code, resultCode, msg, reviewId);
	}
}

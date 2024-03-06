package com.w2.client.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
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
import com.w2.order.OrderVO;
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

	@Autowired
	private JavaMailSender mailSender;
	
	/**
	 * 비회원 주문 조회 화면 호출
	 * @return
	 */
	@RequestMapping("noClientOrder.do")
	public String noClientOrderView() {
		return "order/noClientOrder";
	}

	/** 
	 * 비회원 주문 조회
	 * @param name
	 * @param num
	 * @param pwd
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("noClientOrderProc.do")
	public String noClientOrderProc(String name, String num, String pwd, Model model, HttpSession session) {
		
		HashMap<String, String> checkInfo = new HashMap<String, String>();
		checkInfo.put("name", name);
		checkInfo.put("num", num);
		
		OrderVO orderInfo = orderService.checkUnKnownOrderInfo(checkInfo);
		
		if(orderInfo == null) {
			model.addAttribute("msg", "일치하는 주문 정보가 없습니다.");
			return "order/noClientOrder";
		} else if(orderInfo.getCookiePwd().equals(pwd)) {
			session.setAttribute("cookieId", orderInfo.getCookieId());
			return "redirect:orderInfo.do?orderId="+orderInfo.getOrderId();
		}
		
		model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
		return "order/noClientOrder";
	}
	
	/**
	 * 주문 화면 호출
	 * @param cartList
	 * @param cartOk
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 * @param cartvo
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
			return "order/orderLogin";
		}else {
			model.addAttribute("orderList", orderList);
		}
		
		return "order/orderRegister";
	}
	
	/**
	 * 주문 상세 페이지 호출
	 * @param orderId
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 * @param cartvo
	 * @return
	 */
	@RequestMapping("orderInfo.do")
	public String orderInfo(@Param("orderId") String orderId, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model, CartVO cartvo) {
		model.addAttribute("orderInfo", orderService.getOrderInfo(orderId));
		model.addAttribute("orderProductList", orderService.getOrderInfoList(orderId));
		return "order/orderInfo";
	}
	
	/** 
	 * 주문 추가 
	 * @param data
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
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
			int result = orderService.insertImsiOrder(data);
			orderId = (String)data.get("orderId");
			if(result > 0) {
				result = orderService.insertDeliverInfo(data);
				if(result > 0) {
					code = 1;
					resultCode = "success";
					msg = "주문이 완료되었습니다.";
					if(session.getAttribute("userInfo") != null) {
						ClientVO user = (ClientVO)session.getAttribute("userInfo");
						user = clientService.getClient(user);
						session.setAttribute("userInfo", user);
					}
				} else {
					code = -1;
					resultCode = "fail";
					msg = "배송정보 등록 중 오류가 발생했습니다.";
				}
			} else {
				code = -1;
				resultCode = "fail";
				msg = "주문중 오류가 발생했습니다.";
			}
			
			if(code == -1) {
				orderService.deleteCancleOrderInfo(orderId);
			}
		} catch (Exception e) {
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생했습니다.";
			orderId = null;
		}
		return new ResponseDTO<String>(statusCode, code, resultCode, msg, orderId);
	}
	
	/** 
	 * 주문 정보 취소 
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@PostMapping("deleteCancleOrderInfo.do")
	public ResponseDTO<String> deleteCancleOrderInfo(String orderId) {
		Integer statusCode = HttpStatus.OK.value();
		int code = 0;
		String resultCode;
		String msg;
		
		try {
			int result = orderService.deleteCancleOrderInfo(orderId);
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
	
	
	/** 
	 * 결제 정보 등록 
	 * @param data
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@PostMapping("paymentInsert.do")
	public ResponseDTO<String> paymentInsert(@RequestBody Map<String, Object> data, HttpSession session) throws IOException {
		Integer statusCode = HttpStatus.OK.value();
		int code = 0;
		String resultCode;
		String msg;
		String orderId = (String)data.get("orderId");

		if(session.getAttribute("userInfo") == null) {
			data.put("cookieId", (String)session.getAttribute("cookieId"));
		} else {
			data.put("clientId", (String)((ClientVO)session.getAttribute("userInfo")).getClientId());
		}
		
		try {
			int result = orderService.insertPayment(data);
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

	/** 
	 * 주문 수정 
	 * @param order
	 * @param orderStatus
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
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

	/** 
	 * 교환, 환불 요청 
	 * @param requestWhat
	 * @param orderId
	 * @param optionId
	 * @param reason
	 * @param email
	 * @param deliverWay
	 * @param cost
	 * @param costMtd
	 * @param status
	 * @param bankId
	 * @param refundBankNum
	 * @param keyword
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@PostMapping("insertSwapRefund.do")
	public ResponseDTO<String> insertSwapRefund(String requestWhat, String orderId, String optionId, String reason, String email, 
												String deliverWay, int cost, String costMtd, String status, String bankId, String refundBankNum, 
												String keyword, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Integer statusCode = HttpStatus.OK.value();
		int code = 0;
		String resultCode;
		String msg;
		String id = "";
		
		Map<String, Object> requestInfo = new HashMap<String, Object>();
		requestInfo.put("requestWhat", requestWhat);
		requestInfo.put("orderId", orderId);
		requestInfo.put("optionId", optionId);
		requestInfo.put("reason", reason);
		requestInfo.put("email", email);
		requestInfo.put("deliverWay", deliverWay);
		requestInfo.put("cost", cost);
		requestInfo.put("costMtd", costMtd);
		requestInfo.put("status", status);
		requestInfo.put("keyword", keyword);

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
	
	/** 
	 * 리뷰 등록 
	 * @param review
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
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
	
	/** 
	 * 리뷰 조회 
	 * @param reviewId
	 * @param session
	 * @return
	 */
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
		} else {
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생했습니다.";
		}
		return new ResponseDTO<HashMap<String, Object>>(statusCode, code, resultCode, msg, result);
	}

	@PostMapping("sendMail.do")
	public void sendMail(@RequestBody Map<String, Object> data) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 d일");
		
		List<HashMap<String, String>> orderInfoList = (List<HashMap<String, String>>)data.get("orderInfoList");
		
		String orderListString = "";
		for(int i=0; i<orderInfoList.size(); i++) {
			orderListString += (String)orderInfoList.get(i).get("productName");
			if(i<orderInfoList.size()-1) {
				orderListString += ", <br>";
			}
		
			data.put("orderString", orderListString);
			
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				StringBuffer content = new StringBuffer()
									.append("<p><img src='https://hyeongabucket.s3.ap-northeast-2.amazonaws.com/main/logo.png' width='237px' onclick=\"location.href='http://localhost:8080/w2/main.do'\"></p><p>&nbsp;</p>")
									.append("<h1><span style=\"font-family: 'Nanum Gothic';\"><b>웨더웨어 주문 완료</b></span></h1>")
									.append("<div style='witdh:80%;'><hr><p><span style=\"font-family: 'Nanum Gothic';\">안녕하세요.</span></p>")
									.append("<p><span style=\"font-family: 'Nanum Gothic';\">주문하신 내역입니다.</span><span style=\"font-family: 'Nanum Gothic';\"></span></p>")
									.append("<hr>")
									.append("<p><span style=\"font-family: 'Nanum Gothic';\">주문번호 : <b>" + ((HashMap<String,String>)data.get("paymentInfo")).get("orderId") + "</b></span></p>")
									.append("<p><span style=\"font-family: 'Nanum Gothic';\">결제금액 : <b>" + ((HashMap<String,String>)data.get("orderInfo")).get("orderPrice") + "</b></span></p>")
									.append("<p><span style=\"font-family: 'Nanum Gothic';\">결제방식 : <b>" + ((HashMap<String,String>)data.get("paymentInfo")).get("paymentMethod") + "</b></span></p>")
									.append("<p><span style=\"font-family: 'Nanum Gothic';\">결제상태 : <b>" + ((HashMap<String,String>)data.get("paymentInfo")).get("paymentStatus") + "</b></span></p>")
									.append("<p><span style=\"font-family: 'Nanum Gothic';\">상품정보 <br><b>" + (String)data.get("orderString") + "</b></span></p>")
									.append("<hr></div>");
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					mimeMessage.setFrom(new InternetAddress("weatherwear493@gmail.com", "WeatherWear", "UTF-8"));
					mimeMessage.setSubject("[웨더웨어] 주문하신 내역입니다.");
					mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(((HashMap<String,String>)data.get("orderInfo")).get("orderEmail")));
					mimeMessage.setContent(content.toString(), "text/html;charset=UTF-8");
					mimeMessage.setReplyTo(InternetAddress.parse(((HashMap<String,String>)data.get("orderInfo")).get("orderEmail")));
				}
			};
			
			try {
				mailSender.send(preparator);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}

package com.w2.client.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.w2.board.QnaVO;
import com.w2.board.service.QnaService;
import com.w2.board.service.ReviewService;
import com.w2.cart.service.CartService;
import com.w2.client.ClientVO;
import com.w2.client.service.ClientService;
import com.w2.coupon.CouponVO;
import com.w2.coupon.service.CouponService;
import com.w2.order.service.OrderService;
import com.w2.product.service.ProductService;
import com.w2.util.RandomString;
import com.w2.util.ResponseDTO;
import com.w2.util.Search;

@Controller
public class MypageController {
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private CouponService couponService;
	@Autowired
	private CartService cartService;
	@Autowired
	private QnaService qnaService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	
	/**
	 * 마이페이지 화면 호출
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("mypage.do")
	public String mypage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			return "login";
		}
		
		if(session.getAttribute("userInfo") == null) {
			return "login";
		}
		
		ClientVO client = (ClientVO) session.getAttribute("userInfo");
		
		model.addAttribute("totalPrice", client.getClientBuyCnt());
		model.addAttribute("point", client.getClientPoint());
		model.addAttribute("coupon", couponService.getMyCouponListCnt(client.getClientId()));
		model.addAttribute("cart", cartService.getCartListCnt(client.getClientId()));
		
		Search search = new Search();
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("search", search);
		param.put("clientId", client.getClientId());
		model.addAttribute("orderList", orderService.getMyOrderList(param));
		
		String strMarkList = clientService.getWishList(client);
		if(strMarkList.startsWith(",")) {
			strMarkList = strMarkList.substring(1, strMarkList.length());
		}
		if(strMarkList.endsWith(",")) {
			strMarkList = strMarkList.substring(0, strMarkList.length() - 1);
		}
		String[] arrMarkList = strMarkList.split(",");
		if(strMarkList.length() > 0) {
			List<HashMap<String, Object>> wishList = new ArrayList<HashMap<String, Object>>();
			for(int i = 0; i < arrMarkList.length; i++) {
				HashMap<String, Object> result = new HashMap<String, Object>();
				result = productService.getProductInfo(arrMarkList[i]);
				
				wishList.add(result);
			}
			model.addAttribute("wishList", wishList);
		}
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			List<HashMap<String, Object>> recentList = new ArrayList<HashMap<String,Object>>();
			for(int i = cookies.length - 1; i >= 0; i--) {
				if(cookies[i].getName().startsWith("recent_")) {
					String productId = cookies[i].getValue();
					HashMap<String, Object> result = productService.getProductInfo(productId);
					
					recentList.add(result);
				}
			}
			model.addAttribute("recentList", recentList);
		}
		
		return "mypage/mypage";
	}
	
	/**
	 * 정보수정 화면 호출
	 * @param model
	 * @param request
	 * @param vo
	 * @return
	 */
	@RequestMapping("editInfo.do")
	public String editInfoView(Model model, HttpServletRequest request, ClientVO vo) {
		
		HttpSession session = request.getSession(false);
		
		if(session == null) { return "main"; }
		if(session.getAttribute("userInfo") == null) { return "login"; }
		
		if(session.getAttribute("checkPwd") == null) {
			if(vo.getClientId() == null && vo.getClientPwd() == null) { 
				return "mypage/editInfo"; 
			} else {
				ClientVO client = clientService.getClient(vo);
				
				if(passwordEncoder.matches(vo.getClientPwd(), client.getClientPwd())) {
					session.setAttribute("checkPwd", "true");
					return "redirect:editInfo.do";
				} else {
					session.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
					return "redirect:editInfo.do";
				}
			}
		} else {
			vo = (ClientVO) session.getAttribute("userInfo");
			ClientVO client = clientService.getClient(vo);
			model.addAttribute("info", client);
			return "mypage/editInfo";
		}
	}
	
	/**
	 * 정보수정 프로세스
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping("editInfoProc.do")
	public String editInfoProc(ClientVO vo, Model model) {
		
		if(!vo.getClientPwd().isEmpty()) {
			vo.setClientPwd(passwordEncoder.encode(vo.getClientPwd()));
			vo.setChangePwdDate(new Timestamp(System.currentTimeMillis()));
		}
		vo.setClientEmail(vo.getClientEmail());
		vo.setClientNum(vo.getClientNum());
		vo.setClientBirth(vo.getClientBirth());
		
		clientService.updateClient(vo);
		
		return "redirect:editInfo.do";
	}
	
	/**
	 * 나의 쿠폰 리스트
	 * @param model
	 * @param session
	 * @param page
	 * @param range
	 * @param searchType
	 * @param keyword
	 * @param search
	 * @return
	 */
	@RequestMapping("mycouponList.do")
	public String mycouponList(Model model, HttpSession session, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "couponId") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) {
		
		if(session.getAttribute("userInfo") == null) { return "main"; }
		
		ClientVO client = (ClientVO) session.getAttribute("userInfo");
		
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// 전체 게시글 개수
		int listCnt = couponService.getMyCouponListCnt(client.getClientId());
		
		// 검색 페이지 정보
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("search", search);
		param.put("clientId", client.getClientId());
		
		// 화면 출력
		model.addAttribute("couponList", couponService.getMyCouponList(param));
		
		return "mypage/couponList";
	}
	
	/**
	 * 쿠폰 등록
	 * @param couponId
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("couponRegProc.do")
	public ResponseDTO<String> couponRegProc(@RequestBody String couponId, HttpSession session) {
		Integer statusCode = HttpStatus.OK.value();
		int code = 0;
		String resultCode = null;
		String msg = null;
		String data = null;
		
		if(session.getAttribute("userInfo") == null) { 
			code = -2;
			resultCode = "fail";
			msg = "로그인 후 이용할 수 있습니다.";
			data = "로그인하시겠습니까?";
		}
		
		ClientVO client = (ClientVO) session.getAttribute("userInfo");
		CouponVO coupon = couponService.getCouponInfo(couponId);
		
		if(coupon == null) {
			code = -1;
			resultCode = "fail";
			msg = "입력하신 쿠폰 번호가 존재하지 않습니다.";
		} else {
			Timestamp today = new Timestamp(System.currentTimeMillis());
			if(today.before(coupon.getCouponEndDate())) {
				coupon.setClientId(client.getClientId());
				coupon.setCouponStatus("사용가능");
				
				if(couponService.myCouponReg(coupon) > 0) {
					code = 1;
					resultCode = "success";
					msg = "쿠폰이 등록되었습니다.";
				} else {
					code = -1;
					resultCode = "fail";
					msg = "쿠폰 등록에 실패하였습니다.";
				}
			} else {
				code = -1;
				resultCode = "fail";
				msg = "사용 기간이 만료된 쿠폰 번호입니다.";
			}
		}
		
		return new ResponseDTO<String>(statusCode, code, resultCode, msg, data);
	}
	
	/**
	 * 나의 문의 목록
	 * @param model
	 * @param session
	 * @param page
	 * @param range
	 * @param searchType
	 * @param keyword
	 * @param search
	 * @return
	 */
	@RequestMapping("myqnaList.do")
	public String myqnaList(Model model, HttpSession session, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "qnaTitle") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) {

		if(session.getAttribute("userInfo") == null) { return "login"; }
		
		ClientVO client = (ClientVO) session.getAttribute("userInfo");
		
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// 전체 게시글 개수
		int listCnt = qnaService.getMyQnaListCnt(client.getClientId());
		
		// 검색 페이지 정보
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("search", search);
		param.put("clientId", client.getClientId());
				
		
		model.addAttribute("qnaList", qnaService.getMyQnaList(param));
		
		return "mypage/qnaList";
	}
	
	/**
	 * 1:1 문의 상세보기
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("myqnaInfo.do")
	public String myqnaInfo(Model model, QnaVO vo) {
		model.addAttribute("info", qnaService.getQna(vo));
		return "mypage/qnaInfo";
	}
	
	/**
	 * 1:1 문의 삭제
	 * @param vo
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("qnaDeleteProc.do")
	public ResponseDTO<String> qnaDeleteProc(QnaVO vo, HttpSession session) {
		
		Integer statusCode = HttpStatus.OK.value();
		int code = 0;
		String resultCode = null;
		String msg = null;
		String data = null;
		
		if(session.getAttribute("userInfo") == null) { 
			code = -2;
			resultCode = "fail";
			msg = "로그인 후 이용할 수 있습니다.";
			data = "로그인하시겠습니까?";
		}
		
		if(qnaService.deleteQna(vo) > 0) {
			code = 1;
			resultCode = "success";
			msg = "문의가 삭제되었습니다.";
		} else {
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생하였습니다.";
		}
		
		return new ResponseDTO<String>(statusCode, code, resultCode, msg, data);
	}
	
	/**
	 * 1:1 문의 등록 화면
	 * @return
	 */
	@RequestMapping("qnaRegister.do")
	public String qnaRegisterView() {
		return "mypage/qnaRegister";
	}
	
	/**
	 * 1:1 문의 등록 프로세스
	 * @param vo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("qnaRegProc.do")
	public String qnaRegProc(QnaVO vo) throws IOException {

		String qnaId = String.format("Q%s", RandomString.createFileName());
		vo.setQnaId(qnaId);
		vo.setQnaStatus("답변대기");
		
		qnaService.insertQna(vo);
		
		return "redirect:myqnaList.do";
	}
	
	/**
	 * 1:1 문의 수정
	 * @param vo
	 * @return
	 */
	@RequestMapping("qnaUpdateProc.do")
	public String qnaUpdateProc(QnaVO vo) {
		qnaService.updateQna(vo);
		
		return "redirect:myqnaList.do";
	}
	
	/**
	 * 나의 리뷰 목록
	 * @param model
	 * @param session
	 * @param page
	 * @param range
	 * @param searchType
	 * @param keyword
	 * @param search
	 * @return
	 */
	@RequestMapping("myreviewList.do")
	public String myreviewList(Model model, HttpSession session, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "review") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) {
		
		if(session.getAttribute("userInfo") == null) { return "main"; }
		
		ClientVO client = (ClientVO) session.getAttribute("userInfo");
		
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// 전체 게시글 개수
		int listCnt = reviewService.getMyReviewListCnt(client.getClientId());
		
		// 검색 페이지 정보
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("search", search);
		param.put("clientId", client.getClientId());
				
		model.addAttribute("reviewList", reviewService.getMyReviewList(param));
		
		return "mypage/reviewList";
	}
	
	/**
	 * 나의 관심상품 목록
	 * @param model
	 * @param session
	 * @param page
	 * @param range
	 * @param searchType
	 * @param keyword
	 * @param search
	 * @return
	 */
	@RequestMapping("mywishList.do")
	public String mywishList(Model model, HttpSession session, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "couponId") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) {
		
		if(session.getAttribute("userInfo") == null) { return "login"; }
		
		ClientVO client = (ClientVO) session.getAttribute("userInfo");
		
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		String strMarkList = clientService.getWishList(client);
		if(strMarkList.startsWith(",")) {
			strMarkList = strMarkList.substring(1, strMarkList.length());
		}
		if(strMarkList.endsWith(",")) {
			strMarkList = strMarkList.substring(0, strMarkList.length() - 1);
		}
		String[] arrMarkList = strMarkList.split(",");
		
		// 전체 게시글 개수
		int listCnt = arrMarkList.length;
		
		// 검색 페이지 정보
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		
		if(strMarkList.length() > 0) {
			List<HashMap<String, Object>> wishList = new ArrayList<HashMap<String, Object>>();
			for(int i = 0; i < arrMarkList.length; i++) {
				HashMap<String, Object> result = new HashMap<String, Object>();
				result = productService.getProductInfo(arrMarkList[i]);
				
				wishList.add(result);
			}
			model.addAttribute("wishList", wishList);
		}
		
		return "mypage/wishList";
	}
	
	/**
	 * 최근본상품 목록
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("myrecentList.do")
	public String myrecentList(HttpServletRequest request, Model model) {
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			List<HashMap<String, Object>> recentList = new ArrayList<HashMap<String,Object>>();
			for(int i = cookies.length - 1; i >= 0; i--) {
				if(cookies[i].getName().startsWith("recent_")) {
					String productId = cookies[i].getValue();
					HashMap<String, Object> result = productService.getProductInfo(productId);
					
					recentList.add(result);
				}
			}
			model.addAttribute("recentList", recentList);
		}
		
		return "mypage/recentList";
	}
	
	/**
	 * 최근본 상품 목록 삭제
	 * @param checkList
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("recentListDelete.do")
	public ResponseDTO<String> recentListDelete(@RequestBody List<String> checkList, HttpServletRequest request, HttpServletResponse response) {
		
		Cookie[] cookies = request.getCookies();
		
		for(int j = 0; j < checkList.size(); j++) {
			String productId = checkList.get(j);
			
			for(int i = cookies.length - 1; i >= 0; i--) {
				if(cookies[i].getName().equals("recent_" + productId)) {
					cookies[i].setPath("/");
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
					break;
				}
			}
		}

		return new ResponseDTO<String>(HttpStatus.OK.value(), 1, "success", "최근본 상품에서 삭제되었습니다.", null);
	}
	
	/**
	 * 주문조회
	 * @param model
	 * @param session
	 * @param page
	 * @param range
	 * @param searchType
	 * @param keyword
	 * @param search
	 * @return
	 */
	@RequestMapping("myorderList.do")
	public String myorderList(Model model, HttpSession session, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "orderDate") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) {
		
		if(session.getAttribute("userInfo") == null) { return "login"; }
		
		ClientVO client = (ClientVO) session.getAttribute("userInfo");
		
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("search", search);
		param.put("clientId", client.getClientId());
		
		// 전체 게시글 개수
		int listCnt = orderService.getMyOrderListCnt(param);
		
		// 검색 페이지 정보
		search.setListSize(15);
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
	
		model.addAttribute("orderList", orderService.getMyOrderList(param));
		
		return "mypage/orderList";
	}
}

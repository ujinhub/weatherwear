package com.w2.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.w2.order.service.OrderService;
import com.w2.util.ResponseDTO;
import com.w2.util.SearchOrderby;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;

	/**
	 * 주문 목록 가져오기
	 * @param model
	 * @return
	 */
	@RequestMapping("orderList.mdo")
	public String orderList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "orderName") String searchType,
			@RequestParam(required = false) String keyword, @RequestParam(required = false, defaultValue = "orderDate") String orderby, @ModelAttribute("search") SearchOrderby search) {

		// 검색
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		search.setOrderby(orderby);
		model.addAttribute("search", search);
		
		// 전체 게시글 개수
		int listCnt = orderService.getOrderListCnt(search);
		
		// 검색 페이지 정보
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		// 화면 출력
		model.addAttribute("orderList", orderService.getOrderList(search));
		
		return "order/orderList";
	}

	/**
	 * 주문 수정
	 * @param model
	 * @return
	 */
	@PostMapping("orderUpdate.mdo")
	public void orderUpdate(Model model, HttpServletResponse response, 
					@RequestBody List<Map<String, String>> checkList, @RequestParam("modifyType")String modifyType) throws IOException {
		int code = -1;
		System.err.println("checkList : " + checkList);
		
		if(modifyType.equals("orderStatus")) {
			code = orderService.updateOrderStatus(checkList);
		} else if(modifyType.equals("deliverNum")) {
			code = orderService.updateDeliverNum(checkList);
		}

		response.setContentType("application/json");
		response.getWriter().write(String.valueOf(code));
	}

	/**
	 * 교환, 환불 처리완료 
	 * @param requestWhat
	 * @param id
	 * @param status
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@PostMapping("updateSwapRefundStatus.mdo")
	public ResponseDTO<String> updateSwapRefundStatus(String requestWhat, String id, String status, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Integer statusCode = HttpStatus.OK.value();
		int code = 0;
		String resultCode;
		String msg;
		
		Map<String, Object> requestInfo = new HashMap<String, Object>();
		requestInfo.put("requestWhat", requestWhat);
		requestInfo.put("id", id);
		requestInfo.put("status", status);

		try {
			int result = orderService.updateSwapRefund(requestInfo);
			if(result > 0) {
				code = 1;
				resultCode = "success";
				msg = "요청이 완료되었습니다.";
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
	
	@ResponseBody
	@PostMapping("getSwapRefundImage.mdo")
	public ResponseDTO<List<String>> getSwapRefundImage(String imageBy) {
		Integer statusCode = HttpStatus.OK.value();
		int code = 0;
		
		List<String> result = orderService.getSwapRefundImage(imageBy);
		
		return  new ResponseDTO<List<String>>(statusCode, code, null, null, result);
	}
	
}

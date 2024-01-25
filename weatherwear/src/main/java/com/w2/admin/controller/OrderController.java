package com.w2.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.w2.order.service.OrderService;
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
		
		Integer statusCode = HttpStatus.OK.value();
		int code = -1;
		String resultCode = "fail";
		String message = "오류가 발생했습니다. 다시 시도해주세요";
		
		if(modifyType.equals("orderStatus")) {
			orderService.updateOrderStatus(checkList);
		} else if(modifyType.equals("deliverNum")) {
			orderService.updateDeliverNum(checkList);
		}
		
		if(code == 1) {
			resultCode = "success";
			message = "변경되었습니다.";
		}

		response.setContentType("application/json");
		response.getWriter().write(String.valueOf(code));
	}
}

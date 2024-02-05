package com.w2.client.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.w2.client.service.ClientService;
import com.w2.product.service.ProductService;
import com.w2.util.SearchOrderby;

@Controller
public class ClientProductController {
	
	@Autowired
	private ProductService productService;
	
	/**
	 * 메인 화면 호출
	 * @return
	 */
	@RequestMapping("main.do")
	public String mainView(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		model.addAttribute("bestItem", productService.getMainProductList("best"));
		model.addAttribute("newItem", productService.getMainProductList("new"));
		//model.addAttribute("userInfo", session.getAttribute("userInfo"));
		return "main";
	}

	/**
	 * 상품 목록 화면 호출
	 * @return
	 */
	@RequestMapping("productList.do")
	public String productList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "productName") String searchType,
			@RequestParam(required = false) String keyword, @RequestParam(required = false, defaultValue = "productRegDate") String orderby, @ModelAttribute("search") SearchOrderby search) {
		// 검색
		model.addAttribute("search", search);
		if(search.getListSize()==10)
			search.setListSize(20);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		search.setOrderby(orderby);
		
		// 전체 게시글 개수
		int listCnt = productService.getProductListCnt(search);
		
		// 검색 페이지 정보
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		// 화면 출력
		model.addAttribute("productList", productService.getProductList(search));
		
		return "product/productList";
	}

	/**
	 * 상품 상세 화면 호출
	 * @return
	 */
	@RequestMapping("productInfo.do")
	public String productInfo(@RequestParam(value = "productId", required = false)String productId, Model model, HttpServletRequest request) {
		model.addAttribute("product", productService.getProduct(productId, model));
		return "product/productInfo";
	}
}

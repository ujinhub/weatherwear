package com.w2.client.product.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.w2.client.product.ClientProductDAO;
import com.w2.client.product.ClientProductService;
import com.w2.util.Paging;

@Service("productService")
public class ClientProductServiceImpl implements ClientProductService {

	@Autowired
	public ClientProductDAO productdao;
	
	// 상품 목록
	@Override
	public List<HashMap<String, Object>> clientProductList(HashMap<String, Object> check, Model model) {
		System.out.println("2. [ Impl ] productList");
		
		int currentPage;
		
		if(check.get("page") == null || (int)check.get("page") == 0) {
			currentPage = 1;
		} else {
			currentPage = (int)check.get("page");
		}
		
		int totalCount = productdao.searchCount(check);
		
		Paging paging = new Paging(totalCount, currentPage, check);
		
		int postStart = (paging.getCurrentPage()-1) * 20;
		int postEnd = paging.getCurrentPage() * 20;
		
		check.put("startPage", paging.getStartPage());
		check.put("endPage", paging.getEndPage());
		check.put("postStart", postStart);
		check.put("postEnd", postEnd);
		
		model.addAttribute("paging", paging);
		
		return productdao.clientProductList(check);
	}

	// 상품 상세
	@Override
	public HashMap<String, Object> clientProductInfo(String proId) {
		System.out.println("2. [ Impl ] clientProductInfo");
		
		return productdao.clientProductInfo(proId);
	}

}

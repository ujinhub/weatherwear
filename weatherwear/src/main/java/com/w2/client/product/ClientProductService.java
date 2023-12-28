package com.w2.client.product;

import java.util.HashMap;
import java.util.List;

import org.springframework.ui.Model;

public interface ClientProductService {

	// 상품 목록
	List<HashMap<String, Object>> clientProductList(HashMap<String, Object> check, Model model);

	// 싱픔 상세
	HashMap<String, Object> clientProductInfo(String proId);

}

package com.w2.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.w2.product.ProductVO;
import com.w2.product.service.ProductService;
import com.w2.util.RandomString;
import com.w2.util.ResponseDTO;
import com.w2.util.SearchOrderby;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	/**
	 * 상품 목록 가져오기
	 * @param model
	 * @return
	 */
	@RequestMapping("productList.mdo")
	public String productList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "productName") String searchType,
			@RequestParam(required = false) String keyword, @RequestParam(required = false, defaultValue = "productRegDate") String orderby, @ModelAttribute("search") SearchOrderby search) {
		// 검색
		model.addAttribute("search", search);
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
	 * 상품 등록 화면 호출
	 * @return
	 */
	@RequestMapping("productRegister.mdo")
	public String productRegister(Model model) {
		model.addAttribute("productId", RandomString.setRandomString(5, "number"));
		return "product/productRegister";
	}

	/**
	 * 상품 등록
	 * @return
	 */
	@ResponseBody
	@RequestMapping("productRegisterProc.mdo")
	public ResponseDTO<String> productRegisterProc(ProductVO product, String productId, String colorList, String sizeList, String cntList, String productPrimeCost) {
		String[] color = colorList.split(",");
		String[] size = sizeList.split(",");
		String[] stock = cntList.split(",");
		
		List<String> optionColorList = Arrays.asList(color);
		List<String> optionSizeList = Arrays.asList(size);
		List<Integer> stockCntList = new ArrayList<>();
		for(String s: stock) {
			stockCntList.add(Integer.parseInt(s));
		}
		
		Map<String, Object> pro = new HashMap<String, Object>();
		pro.put("product", product);
		pro.put("optionColorList", optionColorList);
		pro.put("optionSizeList", optionSizeList);
		pro.put("stockCntList", stockCntList);
		pro.put("productPrimeCost", productPrimeCost);
		
		Integer statusCode = HttpStatus.OK.value();
		int code;
		String resultCode;
		String msg;
		String data=null;
		
		try {
			int result = productService.insertProduct(pro);
			
			if(result > 0) {
				code = 1;
				resultCode = "success";
				msg = "상품이 등록되었습니다.";
				data = "상품 상세페이지로 이동하시겠습니까?";
			} else {
				code = -1;
				resultCode = "fail";
				msg = "오류가 발생했습니다. 다시 시도해주세요";
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생했습니다. 다시 시도해주세요";
		}
		
		return new ResponseDTO<String>(statusCode, code, resultCode, msg, data);
	}
	
	/**
	 * 상품 상세 화면 호출
	 * @return
	 */
	@RequestMapping("productInfo.mdo")
	public String productInfo(@RequestParam("productId")String productId, Model model) {
		HashMap<String, Object> product = productService.getProduct(productId, model);
		if(product == null) {
			model.addAttribute("data", "nodata");
		}
		model.addAttribute("product", product);
		return "product/productInfo";
	}

	/**
	 * 상품 수정
	 * @return
	 */
	@ResponseBody
	@RequestMapping("productUpdateProc.mdo")
	public ResponseDTO<String> productUpdateProc(ProductVO product, String colorList, String sizeList, String cntList, String productPrimeCost) {
		String[] color = colorList.split(",");
		String[] size = sizeList.split(",");
		String[] stock = cntList.split(",");
		
		List<String> optionColorList = Arrays.asList(color);
		List<String> optionSizeList = Arrays.asList(size);
		List<Integer> stockCntList = new ArrayList<>();
		for(String s: stock) {
			stockCntList.add(Integer.parseInt(s));
		}
		
		Map<String, Object> pro = new HashMap<String, Object>();
		pro.put("productId", product.getProductId());
		pro.put("product", product);
		pro.put("optionColorList", optionColorList);
		pro.put("optionSizeList", optionSizeList);
		pro.put("stockCntList", stockCntList);
		pro.put("productPrimeCost", productPrimeCost);
		
		Integer statusCode = HttpStatus.OK.value();
		int code;
		String resultCode;
		String msg;
		String data=null;
		
		try {
			int result = productService.updateProduct(pro);
			
			if(result > 0) {
				code = 1;
				resultCode = "success";
				msg = "상품 수정되었습니다";
				data = "상품 상세페이지로 이동하시겠습니까?";
			} else {
				code = -1;
				resultCode = "fail";
				msg = "오류가 발생했습니다. 다시 시도해주세요";
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생했습니다. 다시 시도해주세요";
		}
		
		return new ResponseDTO<String>(statusCode, code, resultCode, msg, data);
	}

	/**
	 * 상품 삭제
	 * @return
	 */
	@ResponseBody
	@RequestMapping("productDelete.mdo")
	public ResponseDTO<String> productDelete(String productId) {
		Integer statusCode = HttpStatus.OK.value();
		int code;
		String resultCode;
		String msg;
		
		try {
			int result = productService.deleteProduct(productId);
			
			if(result > 0) {
				code = 1;
				resultCode = "success";
				msg = "상품이 삭제되었습니다. 상품관리 페이지로 이동합니다.";
			} else {
				code = -1;
				resultCode = "fail";
				msg = "오류가 발생했습니다. 다시 시도해주세요";
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생했습니다. 다시 시도해주세요";
		}
		
		return new ResponseDTO<String>(statusCode, code, resultCode, msg, productId);
	}

	/**
	 * 상품 상태 수정
	 * @param model
	 * @return
	 */
	@PostMapping("productUpdateStatus.mdo")
	public void ProductUpdateStatus(Model model, HttpServletResponse response, @RequestBody List<Map<String, String>> checkList) throws IOException {
		int code = productService.updateProductStatus(checkList);

		response.setContentType("application/json");
		response.getWriter().write(String.valueOf(code));
	}
}

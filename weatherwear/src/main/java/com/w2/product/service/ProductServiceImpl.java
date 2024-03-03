package com.w2.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.w2.product.ProductDAO;
import com.w2.product.ProductVO;
import com.w2.util.SearchOrderby;

@Transactional
@Service("ProductService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	@Override	// 상품 목록 가져오기
	public List<ProductVO> getProductList(SearchOrderby search) {
		return productDAO.getProductList(search);
	}
	
	@Override	// 상품 목록 개수 가져오기
	public int getProductListCnt(SearchOrderby search) {
		return productDAO.getProductListCnt(search);
	}

	@Override	// 상품 등록	
	public int insertProduct(Map<String, Object> pro) {
		int result = -1;
		try {
			result = productDAO.insertProduct((ProductVO)pro.get("product"));
			result = productDAO.insertPrice(pro);
			result = productDAO.insertOption(pro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override	// 상품 상세
	public HashMap<String, Object> getProduct(String productId, Model model) {
		return productDAO.getProduct(productId, model);
	}

	@Override	// 상품 수정
	public int updateProduct(Map<String, Object> pro) {
		int result=-1;
		try {
			result = productDAO.updateProduct((ProductVO)pro.get("product"));
			result = productDAO.updatePrice(pro);
			result = productDAO.deleteOption((String)pro.get("productId"));
			if(result > 0) {
				result = productDAO.insertOption(pro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override	// 상품 삭제
	public int deleteProduct(String productId) {
		int result = -1;
		result = productDAO.deletePrice(productId);
		result = productDAO.deleteOption(productId);
		result = productDAO.deleteProduct(productId);
		
		return result;
	}

	@Override	// 상품 상태 변경
	public int updateProductStatus(List<Map<String, String>> checkList) {
		return productDAO.updateProductStatus(checkList);
	}

	@Override	// 메인페이지 상품 리스트 가져오기
	public List<ProductVO> getMainProductList(String type) {
		return productDAO.getMainProductList(type);
	}

	@Override	// 위시리스트에 상품 추가
	public int insertWishList(Map<String, Object> client) {
		return productDAO.insertWishList(client);
	}

	@Override	// 위시리스트 상품 삭제
	public int deleteWishList(Map<String, Object> client) {
		return productDAO.deleteWishList(client);
	}

	@Override	// 관심 상품, 최근본상품 정보 가져오기
	public HashMap<String, Object> getProductInfo(String productId) {
		return productDAO.getProductInfo(productId);
	}
}

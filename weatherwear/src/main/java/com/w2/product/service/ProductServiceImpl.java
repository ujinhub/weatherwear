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
	
	@Override
	public List<ProductVO> getProductList(SearchOrderby search) {
		return productDAO.getProductList(search);
	}
	
	@Override
	public int getProductListCnt(SearchOrderby search) {
		return productDAO.getProductListCnt(search);
	}

	@Override
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

	@Override
	public HashMap<String, Object> getProduct(String productId, Model model) {
		return productDAO.getProduct(productId, model);
	}

	@Override
	public int updateProduct(Map<String, Object> pro) {
		int result=-1;
		try {
			result = productDAO.updateProduct((ProductVO)pro.get("product"));
			result = productDAO.updatePrice(pro);
			result = productDAO.deleteOption((String)pro.get("productId"));
			if(result > 0) {
				result = productDAO.insertOption(pro);
				System.err.println("insert option : " + result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteProduct(String productId) {
		int result = -1;
		result = productDAO.deletePrice(productId);
		result = productDAO.deleteOption(productId);
		result = productDAO.deleteProduct(productId);
		
		return result;
	}

	@Override
	public int updateProductStatus(List<Map<String, String>> checkList) {
		return productDAO.updateProductStatus(checkList);
	}

	@Override
	public List<ProductVO> getMainProductList(String type) {
		return productDAO.getMainProductList(type);
	}

	@Override
	public int insertWishList(Map<String, Object> client) {
		return productDAO.insertWishList(client);
	}

	@Override
	public int deleteWishList(Map<String, Object> client) {
		return productDAO.deleteWishList(client);
	}
}

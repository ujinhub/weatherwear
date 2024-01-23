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
	public int insert(Map<String, Object> pro) {
		int result = -1;
		try {
			result = productDAO.insertProduct((ProductVO)pro.get("product"));
			System.err.println("insert product : " + result);
			result = productDAO.insertPrice(pro);
			System.err.println("insert price : " + result);
			result = productDAO.insertOption(pro);
			System.err.println("insert option : " + result);
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
	public int modify(Map<String, Object> pro) {
		int result=-1;
		try {
			result = productDAO.modifyProduct((ProductVO)pro.get("product"));
			System.err.println("modify product : " + result);
			result = productDAO.modifyPrice(pro);
			System.err.println("modify price : " + result);
			result = productDAO.deleteOption((String)pro.get("productId"));
			System.err.println("delete option : " + result);
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
	public int delete(String productId) {
		int result = -1;
		result = productDAO.deletePrice(productId);
		System.err.println("delete price : " + result);
		result = productDAO.deleteOption(productId);
		System.err.println("delete option : " + result);
		result = productDAO.deleteProduct(productId);
		System.err.println("delete product : " + result);
		
		return result;
	}

	@Override
	public int modifyProductStatus(List<Map<String, String>> checkList) {
		return productDAO.modifyProductStatus(checkList);
	}
}

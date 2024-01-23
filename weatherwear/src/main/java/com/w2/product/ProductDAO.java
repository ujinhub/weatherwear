package com.w2.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.w2.util.SearchOrderby;

@Repository
public class ProductDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<ProductVO> getProductList(SearchOrderby search) {
		System.err.println("search : " + search);
		return sqlSessionTemplate.selectList("ProductDAO.getProductList", search);
	}

	public int getProductListCnt(SearchOrderby search) {
		System.err.println(">>search : " + search);
		return sqlSessionTemplate.selectOne("ProductDAO.getProductListCnt", search);
	}

	public int insertProduct(ProductVO product) {
		return sqlSessionTemplate.insert("ProductDAO.insertProduct", product);
	}

	public int insertPrice(Map<String, Object> pro) {
		return sqlSessionTemplate.insert("ProductDAO.insertPrice", pro);
	}

	public int insertOption(Map<String, Object> pro) {
		List<OptionVO> optionList = new ArrayList<OptionVO>();
		List<String> optionColorList = (List<String>)pro.get("optionColorList");
		List<String> optionSizeList = (List<String>)pro.get("optionSizeList");
		List<Integer> stockCntList = (List<Integer>)pro.get("stockCntList");

		
		for(int i=0; i<optionColorList.size(); i++) {
			for(int j=0; j<optionSizeList.size(); j++) {
				OptionVO option = new OptionVO();
				option.setProductId(((ProductVO)pro.get("product")).getProductId());
				option.setOptionColor(optionColorList.get(i));
				option.setOptionSize(optionSizeList.get(j));
				option.setStockCnt(stockCntList.get(((i+1)*(j+1)-1)));
				System.err.println("option : " + option);
				optionList.add(option);
			}
		}
		return sqlSessionTemplate.insert("ProductDAO.insertOption", optionList);
	}

	public HashMap<String, Object> getProduct(String productId, Model model) {
		model.addAttribute("optionInfo",sqlSessionTemplate.selectOne("ProductDAO.getOptionInfo", productId));
		model.addAttribute("optionList",sqlSessionTemplate.selectList("ProductDAO.getOptionList", productId));
		model.addAttribute("detailImageList",sqlSessionTemplate.selectList("ProductDAO.getDetailImage", productId));
		return sqlSessionTemplate.selectOne("ProductDAO.getProduct", productId);
	}

	public int modifyProduct(ProductVO product) {
		return sqlSessionTemplate.update("ProductDAO.updateProduct", product);
	}

	public int modifyPrice(Map<String, Object> pro) {
		return sqlSessionTemplate.update("ProductDAO.updatePrice", pro);
	}

	public int deleteOption(String productId) {
		return sqlSessionTemplate.delete("ProductDAO.deleteOption", productId);
	}

	public int deleteProduct(String productId) {
		return sqlSessionTemplate.delete("ProductDAO.deleteProduct", productId);
	}

	public int deletePrice(String productId) {
		return sqlSessionTemplate.delete("ProductDAO.deletePrice", productId);
	}

	public int modifyProductStatus(List<Map<String, String>> checkList) {
		int result = -1;
		for(Map<String, String> product: checkList) {
			result = sqlSessionTemplate.update("ProductDAO.updateStatus", product);
			if(result < 1) {
				return -1;
			}
		}
		return result;
	}
}

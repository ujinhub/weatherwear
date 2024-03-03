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

	/** 상품 목록 조회 */
	public List<ProductVO> getProductList(SearchOrderby search) {
		return sqlSessionTemplate.selectList("ProductDAO.getProductList", search);
	}

	/** 상품 목록 개수 조회 */
	public int getProductListCnt(SearchOrderby search) {
		return sqlSessionTemplate.selectOne("ProductDAO.getProductListCnt", search);
	}

	/** 상품 등록 */
	public int insertProduct(ProductVO product) {
		return sqlSessionTemplate.insert("ProductDAO.insertProduct", product);
	}

	/** 상품 가격 등록 */
	public int insertPrice(Map<String, Object> pro) {
		return sqlSessionTemplate.insert("ProductDAO.insertPrice", pro);
	}

	/** 상품 옵션 등록 */
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
				optionList.add(option);
			}
		}
		return sqlSessionTemplate.insert("ProductDAO.insertOption", optionList);
	}

	/** 상품 조회 */
	public HashMap<String, Object> getProduct(String productId, Model model) {
		model.addAttribute("optionInfo",sqlSessionTemplate.selectOne("ProductDAO.getOptionInfo", productId));
		model.addAttribute("optionList",sqlSessionTemplate.selectList("ProductDAO.getOptionList", productId));
		model.addAttribute("detailImageList",sqlSessionTemplate.selectList("ProductDAO.getDetailImage", productId));
		return sqlSessionTemplate.selectOne("ProductDAO.getProduct", productId);
	}

	/** 상품 수정 */
	public int updateProduct(ProductVO product) {
		return sqlSessionTemplate.update("ProductDAO.updateProduct", product);
	}

	/** 상품 가격 수정 */
	public int updatePrice(Map<String, Object> pro) {
		return sqlSessionTemplate.update("ProductDAO.updatePrice", pro);
	}

	/** 상품 옵션 삭제 */
	public int deleteOption(String productId) {
		return sqlSessionTemplate.delete("ProductDAO.deleteOption", productId);
	}

	/** 상품 삭제 */
	public int deleteProduct(String productId) {
		return sqlSessionTemplate.delete("ProductDAO.deleteProduct", productId);
	}

	/** 상품 가격 삭제 */
	public int deletePrice(String productId) {
		return sqlSessionTemplate.delete("ProductDAO.deletePrice", productId);
	}

	/** 상품 상태 변경 */
	public int updateProductStatus(List<Map<String, String>> checkList) {
		int result = -1;
		for(Map<String, String> product: checkList) {
			result = sqlSessionTemplate.update("ProductDAO.updateStatus", product);
			if(result < 1) {
				return -1;
			}
		}
		return result;
	}

	/** 메인 페이지 상품 리스트 조회 */
	public List<ProductVO> getMainProductList(String type) {
		return sqlSessionTemplate.selectList("ProductDAO.getMainProductList", type);
	}

	/** 위시리스트 추가 */
	public int insertWishList(Map<String, Object> client) {
		String result = sqlSessionTemplate.selectOne("ProductDAO.checkWishList", client);
		
		if(result != null) {
			return -3;
		}
		
		return sqlSessionTemplate.update("ProductDAO.insertWishList", client);
	}

	/** 위시리스트 삭제 */
	public int deleteWishList(Map<String, Object> client) {
		return sqlSessionTemplate.update("ProductDAO.deleteWishList", client);
	}
	
	/** 나의 관심 상품, 최근본 상품 */
	public HashMap<String, Object> getProductInfo(String productId) {
		return sqlSessionTemplate.selectOne("ProductDAO.getProductInfo", productId);
	}
	
}

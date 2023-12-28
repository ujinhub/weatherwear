package com.w2.client.product;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientProductDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 상품 리스트 조회
	public List<HashMap<String, Object>> clientProductList(HashMap<String, Object> check) {
		System.out.println("3. [ Product DAO ] productList");
		
		return sqlSessionTemplate.selectList("ProductDAO.clientProductList", check);
	}

	// 상품 개수 확인
	public int searchCount(HashMap<String, Object> check) {
		System.out.println("3. [ Product DAO ] searchCount");
		
		return sqlSessionTemplate.selectOne("ProductDAO.searchCount", check);
	}

	// 상품 상세
	public HashMap<String, Object> clientProductInfo(String proId) {
		System.out.println("3. [ Product DAO ] clientProductInfo");
		
		HashMap<String, Object> product = sqlSessionTemplate.selectOne("ProductDAO.clientProductInfo", proId);
		
		product.put("colorList", sqlSessionTemplate.selectList("ProductDAO.getColorList", proId));
		product.put("sizeList", sqlSessionTemplate.selectList("ProductDAO.getSizeList", proId));
		product.put("detailImage", sqlSessionTemplate.selectList("ProductDAO.getDetailImage", proId));
		
		return product;
	}

}

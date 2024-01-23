package com.w2.delivery;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.w2.util.Search;

@Repository
public class DeliveryDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<HashMap<String, Object>> getDeliveryList(Search search) {
		return sqlSessionTemplate.selectList("DeliveryDAO.getDeliveryList", search);
	}

	public int getDeliveryListCnt(Search search) {
		return sqlSessionTemplate.selectOne("DeliveryDAO.getDeliveryListCnt", search);
	}

	public int insert(DeliveryVO deli) {
		return sqlSessionTemplate.insert("DeliveryDAO.insert", deli);
	}

	public int modify(DeliveryVO deli) { 
		return sqlSessionTemplate.update("DeliveryDAO.update", deli);
	}

	public int delete(String deliveryId) {
		return sqlSessionTemplate.delete("DeliveryDAO.delete", deliveryId);
	}
	
	public int check(String deliveryId) {
		return sqlSessionTemplate.selectOne("DeliveryDAO.check", deliveryId);
	}
	
}

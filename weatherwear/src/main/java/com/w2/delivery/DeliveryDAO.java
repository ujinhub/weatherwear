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

	public int insertDelivery(DeliveryVO deli) {
		return sqlSessionTemplate.insert("DeliveryDAO.insertDelivery", deli);
	}

	public int updateDelivery(DeliveryVO deli) { 
		return sqlSessionTemplate.update("DeliveryDAO.updateDelivery", deli);
	}

	public int deleteDelivery(String deliveryId) {
		return sqlSessionTemplate.delete("DeliveryDAO.deleteDelivery", deliveryId);
	}
	
	public int checkDelivery(String deliveryId) {
		return sqlSessionTemplate.selectOne("DeliveryDAO.checkDelivery", deliveryId);
	}
	
}

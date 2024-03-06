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

	/** 배송회사 리스트 조회 */
	public List<HashMap<String, Object>> getDeliveryList(Search search) {
		return sqlSessionTemplate.selectList("DeliveryDAO.getDeliveryList", search);
	}

	/** 배송회사 리스트 개수 조회 */
	public int getDeliveryListCnt(Search search) {
		return sqlSessionTemplate.selectOne("DeliveryDAO.getDeliveryListCnt", search);
	}

	/** 배송회사 등록 */
	public int insertDelivery(DeliveryVO deli) {
		return sqlSessionTemplate.insert("DeliveryDAO.insertDelivery", deli);
	}

	/** 배송회사 정보 수정 */
	public int updateDelivery(DeliveryVO deli) { 
		return sqlSessionTemplate.update("DeliveryDAO.updateDelivery", deli);
	}

	/** 배송회사 정보 삭제 */
	public int deleteDelivery(String deliveryId) {
		return sqlSessionTemplate.delete("DeliveryDAO.deleteDelivery", deliveryId);
	}

	/** 배송회사 정보 확인 */
	public int checkDelivery(String deliveryId) {
		return sqlSessionTemplate.selectOne("DeliveryDAO.checkDelivery", deliveryId);
	}
	
}

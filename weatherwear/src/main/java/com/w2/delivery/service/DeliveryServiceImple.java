package com.w2.delivery.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.delivery.DeliveryDAO;
import com.w2.delivery.DeliveryVO;
import com.w2.util.Search;

@Service("DeliveryService")
public class DeliveryServiceImple implements DeliveryService {

	@Autowired
	private DeliveryDAO deliveryDAO;
	
	@Override	// 택배사 목록 조회
	public List<HashMap<String, Object>> getDeliveryList(Search search) {
		return deliveryDAO.getDeliveryList(search);
	}

	@Override	// 택배사 목록 개수 조회
	public int getDeliveryListCnt(Search search) {
		return deliveryDAO.getDeliveryListCnt(search);
	}

	@Override	// 택배사 신규 등록
	public int insertDelivery(DeliveryVO deli) {
		if(deliveryDAO.checkDelivery(deli.getDeliveryId()) > 0) {
			return -2;
		}
		return deliveryDAO.insertDelivery(deli);
	}

	@Override	// 택배사 수정
	public int updateDelivery(DeliveryVO deli) {
		return deliveryDAO.updateDelivery(deli);
	}

	@Override	// 택배사 삭제
	public int deleteDelivery(String deliveryId) {
		return deliveryDAO.deleteDelivery(deliveryId);
	}

}

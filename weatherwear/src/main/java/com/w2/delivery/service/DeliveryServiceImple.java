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
	
	@Override
	public List<HashMap<String, Object>> getProductList(Search search) {
		return deliveryDAO.getDeliveryList(search);
	}

	@Override
	public int getProductListCnt(Search search) {
		return deliveryDAO.getDeliveryListCnt(search);
	}

	@Override
	public int insertDelivery(DeliveryVO deli) {
		if(deliveryDAO.checkDelivery(deli.getDeliveryId()) > 0) {
			return -2;
		}
		return deliveryDAO.insertDelivery(deli);
	}

	@Override
	public int updateDelivery(DeliveryVO deli) {
		return deliveryDAO.updateDelivery(deli);
	}

	@Override
	public int deleteDelivery(String deliveryId) {
		return deliveryDAO.deleteDelivery(deliveryId);
	}

}

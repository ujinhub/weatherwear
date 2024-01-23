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
	public int insert(DeliveryVO deli) {
		if(deliveryDAO.check(deli.getDeliveryId()) > 0) {
			return -2;
		}
		return deliveryDAO.insert(deli);
	}

	@Override
	public int modify(DeliveryVO deli) {
		return deliveryDAO.modify(deli);
	}

	@Override
	public int modify(String deliveryId) {
		return deliveryDAO.delete(deliveryId);
	}

}

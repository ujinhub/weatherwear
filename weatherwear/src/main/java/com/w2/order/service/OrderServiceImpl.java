package com.w2.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.w2.cart.CartVO;
import com.w2.client.ClientVO;
import com.w2.order.OrderDAO;
import com.w2.util.SearchOrderby;

@Transactional(readOnly=true)
@Service("OrderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;
	
	@Override
	public List<HashMap<String, Object>> getOrderList(SearchOrderby search) {
		return orderDAO.getOrderList(search);
	}

	@Override
	public int getOrderListCnt(SearchOrderby search) {
		return orderDAO.getOrderListCnt(search);
	}

	@Override
	public int updateOrderStatus(List<Map<String, String>> checkList) {
		return orderDAO.updateOrderStatus(checkList);
	}

	@Override
	public int updateDeliverNum(List<Map<String, String>> checkList) {
		return orderDAO.updateDeliverNum(checkList);
	}

	@Override
	public List<CartVO> getOrderProductList(Map<String, Object> orderMap) {
		return orderDAO.getOrderProductList(orderMap);
	}

	@Override
	public ClientVO setClient(String clientId) {
		return orderDAO.setClient(clientId);
	}

	@Override
	@Transactional
	public int insertOrder(Map<String, Object> data) {
		int result = orderDAO.insertAddress(data);
		if(data.get("addressBase").equals("Y")) {
			result = orderDAO.updateBaseAddress(data);
		}
		result = orderDAO.insertOrder(data);
		result = orderDAO.insertOrderInfo(data);
		result = orderDAO.updateClientPoint(data);
		if(data.get("usedCouponInfo") != null) {
			result = orderDAO.updateCouponList(data);
		}
		result = orderDAO.updateProductStock(data);
		result = orderDAO.insertPayment(data);
		result = orderDAO.deleteCart(data);
		return result;
	}

	@Override
	public Map<String, Object> getOrderInfo(String orderId) {
		return orderDAO.getOrderInfo(orderId);
	}

	@Override
	public List<Map<String, Object>> getOrderInfoList(String orderId) {
		return orderDAO.getOrderInfoList(orderId);
	}

	@Override
	public List<Map<String, Object>> getMyOrderList(HashMap<String, Object> param) {
		return orderDAO.getMyOrderList(param);
	}

	@Override
	public int getMyOrderListCnt(HashMap<String, Object> param) {
		return orderDAO.getMyOrderListCnt(param);
	}

	
}

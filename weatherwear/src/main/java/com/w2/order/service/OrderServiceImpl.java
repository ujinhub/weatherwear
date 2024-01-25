package com.w2.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.order.OrderDAO;
import com.w2.util.SearchOrderby;

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

}

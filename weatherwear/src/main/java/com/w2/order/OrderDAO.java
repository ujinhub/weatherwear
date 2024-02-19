package com.w2.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.w2.cart.CartVO;
import com.w2.client.ClientVO;
import com.w2.util.SearchOrderby;

@Repository
public class OrderDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<HashMap<String, Object>> getOrderList(SearchOrderby search) {
		return sqlSessionTemplate.selectList("OrderDAO.getOrderList", search);
	}

	public int getOrderListCnt(SearchOrderby search) {
		return sqlSessionTemplate.selectOne("OrderDAO.getOrderListCnt", search);
	}

	public int updateOrderStatus(List<Map<String, String>> checkList) {
		int result = -1;
		result = sqlSessionTemplate.update("OrderDAO.updateOrderStatus", checkList);
		return result;
	}

	public int updateDeliverNum(List<Map<String, String>> checkList) {
		int result = -1;
		result = sqlSessionTemplate.update("OrderDAO.updateDeliverNum", checkList);
		return result;
	}

	public List<CartVO> getOrderProductList(Map<String, Object> orderMap) {
		return sqlSessionTemplate.selectList("CartDAO.getOrderProductList", orderMap);
	}

	public ClientVO setClient(String clientId) {
		return sqlSessionTemplate.selectOne("ClientDAO.getClient", clientId);
	}

	public int insertAddress(Map<String, Object> data) {
		return sqlSessionTemplate.insert("OrderDAO.insertAddress", data);
	}

	public int updateBaseAddress(Map<String, Object> data) {
		return sqlSessionTemplate.update("OrderDAO.updateBaseAddress", data);
	}

	public int insertOrder(Map<String, Object> data) {
		return sqlSessionTemplate.insert("OrderDAO.insertOrder", data);
	}

	public int insertOrderInfo(Map<String, Object> data) {
		return sqlSessionTemplate.insert("OrderDAO.insertOrderInfo", data);
	}

	public int updateClientPoint(Map<String, Object> data) {
		System.err.println("updateClientPoint");
		return sqlSessionTemplate.update("OrderDAO.updateClientPoint", data);
	}

	public int updateCouponList(Map<String, Object> data) {
		System.err.println("updateCouponList");
		return sqlSessionTemplate.update("OrderDAO.updateCouponList", data);
	}

	public int insertPayment(Map<String, Object> data) {
		System.err.println("insertPayment");
		return sqlSessionTemplate.insert("OrderDAO.insertPayment", data);
	}

	public int deleteCart(Map<String, Object> data) {
		System.err.println("deleteCart");
		return sqlSessionTemplate.delete("OrderDAO.deleteCart", data);
	}

	public int updateProductStock(Map<String, Object> data) {
		return sqlSessionTemplate.update("OrderDAO.updateProductStock", data);
	}
	
	public Map<String, Object> getOrderInfo(String orderId) {
		return sqlSessionTemplate.selectOne("OrderDAO.getOrderInfo", orderId);
	}
	
	public List<Map<String, Object>> getOrderInfoList(String orderId) {
		return sqlSessionTemplate.selectList("OrderDAO.getOrderInfoList", orderId);
	}
	
	/** 나의 주문 내역 */
	public List<Map<String, Object>> getMyOrderList(HashMap<String, Object> param) {
		return sqlSessionTemplate.selectList("OrderDAO.getMyOrderList", param);
	}
	
	public int getMyOrderListCnt(HashMap<String, Object> param) {
		return sqlSessionTemplate.selectOne("OrderDAO.getMyOrderListCnt", param);
	}
	
	public int updateOrderStatus(Map<String, Object> orderData) {
		return sqlSessionTemplate.update("OrderDAO.updateOrderInfo", orderData);
	}

	public int insertSwapRefund(Map<String, Object> requestInfo) {
		return sqlSessionTemplate.insert("OrderDAO.insertSwapRefund", requestInfo);
	}
}

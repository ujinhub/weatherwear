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
import com.w2.order.OrderVO;
import com.w2.util.SearchOrderby;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;
	
	@Override	// 주문 목록 조회
	public List<HashMap<String, Object>> getOrderList(SearchOrderby search) {
		return orderDAO.getOrderList(search);
	}

	@Override	// 주문 목록 개수 조회
	public int getOrderListCnt(SearchOrderby search) {
		return orderDAO.getOrderListCnt(search);
	}

	@Override	// 주문 상태 수정
	public int updateOrderStatus(List<Map<String, String>> checkList) {
		return orderDAO.updateOrderStatus(checkList);
	}

	@Override	// 송장번호 수정
	public int updateDeliverNum(List<Map<String, String>> checkList) {
		return orderDAO.updateDeliverNum(checkList);
	}

	@Override	// 주문 상품 리스트 조회
	public List<CartVO> getOrderProductList(Map<String, Object> orderMap) {
		return orderDAO.getOrderProductList(orderMap);
	}

	@Override	// 주문 등록
	@Transactional
	public int insertImsiOrder(Map<String, Object> data) {
		int result = orderDAO.insertAddress(data);
		
		if(data.get("cookieId")== null) {
			if(data.get("addressBase").equals("Y")) {
				result = orderDAO.updateBaseAddress(data);
			}
			result = orderDAO.updateClientPoint(data);
		}

		result = orderDAO.insertOrder(data);
		result = orderDAO.insertOrderInfo(data);
		if(data.get("usedCouponInfo") != null) {
			result = orderDAO.updateCouponList(data);
		}
		result = orderDAO.updateProductStock(data);
		return result;
	}

	@Override	// 주문 상세
	public Map<String, Object> getOrderInfo(String orderId) {
		return orderDAO.getOrderInfo(orderId);
	}

	@Override	// 주문 상품 목록
	public List<Map<String, Object>> getOrderInfoList(String orderId) {
		return orderDAO.getOrderInfoList(orderId);
	}

	@Override	// 마이페이지 주문 리스트 조회
	public List<Map<String, Object>> getMyOrderList(HashMap<String, Object> param) {
		return orderDAO.getMyOrderList(param);
	}

	@Override	// 마이페이지 주문 리스트 개수 조회
	public int getMyOrderListCnt(HashMap<String, Object> param) {
		return orderDAO.getMyOrderListCnt(param);
	}

	@Override	// 주문 상태 수정
	public int updateOrder(Map<String, Object> orderData) {
		return orderDAO.updateOrderStatus(orderData);
	}

	@Override	// 교환/환불 요청
	public int insertSwapRefund(Map<String, Object> requestInfo) {
		return orderDAO.insertSwapRefund(requestInfo);
	}

	@Override	// 결제 정보 등록
	public int insertPayment(Map<String, Object> data) {
		orderDAO.deleteCart(data);
		return orderDAO.insertPayment(data);
	}

	@Override	// 교환/환불 정보 변경
	public int updateSwapRefund(Map<String, Object> requestInfo) {
		return orderDAO.updateSwapRefund(requestInfo);
	}

	@Override	// 오류 주문 정보 삭제
	public int deleteCancleOrderInfo(String orderId) {
		return orderDAO.deleteCancleOrderInfo(orderId);
	}

	@Override	// 비회원 정보 조회
	public OrderVO checkUnKnownOrderInfo(HashMap<String, String> checkInfo) {
		return orderDAO.checkUnKnownOrderInfo(checkInfo);
	}

	@Override	// 배송정보 등록
	public int insertDeliverInfo(Map<String, Object> data) {
		return orderDAO.insertDeliverInfo(data);
	}

	@Override	// 구매 확정 업데이트
	public void checkOrderDate() {
		List<String> checkOrderList = orderDAO.checkOrderDate();

		if(checkOrderList.size()>0) {
			orderDAO.updateOrderDate(checkOrderList);
		}
	}

	@Override
	public List<String> getSwapRefundImage(String imageBy) {
		return orderDAO.getSwapRefundImage(imageBy);
	}
}

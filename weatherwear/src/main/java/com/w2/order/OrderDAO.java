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

	/** 주문 목록 조회 */
	public List<HashMap<String, Object>> getOrderList(SearchOrderby search) {
		return sqlSessionTemplate.selectList("OrderDAO.getOrderList", search);
	}

	/** 주문 목록 개수 조회 */
	public int getOrderListCnt(SearchOrderby search) {
		return sqlSessionTemplate.selectOne("OrderDAO.getOrderListCnt", search);
	}

	/** 주문 상태 수정 */
	public int updateOrderStatus(List<Map<String, String>> checkList) {
		int result = -1;
		result = sqlSessionTemplate.update("OrderDAO.updateOrderStatus", checkList);
		return result;
	}

	/** 송장번호 수정 */
	public int updateDeliverNum(List<Map<String, String>> checkList) {
		int result = -1;
		result = sqlSessionTemplate.update("OrderDAO.updateDeliverNum", checkList);
		return result;
	}

	/** 주문 상품 리스트 조회 */
	public List<CartVO> getOrderProductList(Map<String, Object> orderMap) {
		return sqlSessionTemplate.selectList("CartDAO.getOrderProductList", orderMap);
	}

	/** 주문 등록 */
	public ClientVO setClient(String clientId) {
		return sqlSessionTemplate.selectOne("ClientDAO.getClient", clientId);
	}

	/** 주문 상세 */
	public int insertAddress(Map<String, Object> data) {
		return sqlSessionTemplate.insert("OrderDAO.insertAddress", data);
	}

	/** 기본 배송지 변경 */
	public int updateBaseAddress(Map<String, Object> data) {
		return sqlSessionTemplate.update("OrderDAO.updateBaseAddress", data);
	}

	/** 신규 주문 등록 */
	public int insertOrder(Map<String, Object> data) {
		return sqlSessionTemplate.insert("OrderDAO.insertOrder", data);
	}

	/** 신규 주문 상세 등록 */
	public int insertOrderInfo(Map<String, Object> data) {
		return sqlSessionTemplate.insert("OrderDAO.insertOrderInfo", data);
	}

	/** 사용자 포인트 등록 */
	public int updateClientPoint(Map<String, Object> data) {
		return sqlSessionTemplate.update("OrderDAO.updateClientPoint", data);
	}

	/** 사용자 쿠폰 리스트 변경 */
	public int updateCouponList(Map<String, Object> data) {
		return sqlSessionTemplate.update("OrderDAO.updateCouponList", data);
	}

	/** 결제 정보 등록 */
	public int insertPayment(Map<String, Object> data) {
		return sqlSessionTemplate.insert("OrderDAO.insertPayment", data);
	}

	/** 장바구니 삭제 */
	public int deleteCart(Map<String, Object> data) {
		return sqlSessionTemplate.delete("OrderDAO.deleteCart", data);
	}

	/** 상품 재고 변경 */
	public int updateProductStock(Map<String, Object> data) {
		return sqlSessionTemplate.update("OrderDAO.updateProductStock", data);
	}

	/** 주문 상세 조회 */
	public Map<String, Object> getOrderInfo(String orderId) {
		return sqlSessionTemplate.selectOne("OrderDAO.getOrderInfo", orderId);
	}

	/** 주문 상세 목록 조회 */
	public List<Map<String, Object>> getOrderInfoList(String orderId) {
		return sqlSessionTemplate.selectList("OrderDAO.getOrderInfoList", orderId);
	}
	
	/** 나의 주문 내역 */
	public List<Map<String, Object>> getMyOrderList(HashMap<String, Object> param) {
		return sqlSessionTemplate.selectList("OrderDAO.getMyOrderList", param);
	}

	/** 마이페이지 주문 내역 개수 조회 */
	public int getMyOrderListCnt(HashMap<String, Object> param) {
		return sqlSessionTemplate.selectOne("OrderDAO.getMyOrderListCnt", param);
	}

	/** 주문 상태 변경 */
	public int updateOrderStatus(Map<String, Object> orderData) {
		return sqlSessionTemplate.update("OrderDAO.updateOrderInfo", orderData);
	}

	/** 교환/환불 등록 */
	public int insertSwapRefund(Map<String, Object> requestInfo) {
		return sqlSessionTemplate.insert("OrderDAO.insertSwapRefund", requestInfo);
	}

	/** 교환/환불 상태 변경 */
	public int updateSwapRefund(Map<String, Object> requestInfo) {
		return sqlSessionTemplate.update("OrderDAO.updateSwapRefund", requestInfo);
	}

	/** 취소된 주문 정보 삭제 */
	public int deleteCancleOrderInfo(String orderId) {
		return sqlSessionTemplate.delete("OrderDAO.deleteOrderInfo", orderId);
	}

	/** 비회원 주문 조회 */
	public OrderVO checkUnKnownOrderInfo(HashMap<String, String> checkInfo) {
		return sqlSessionTemplate.selectOne("OrderDAO.checkUnKnownOrderInfo", checkInfo);
	}

	/** 배송정보 등록 */
	public int insertDeliverInfo(Map<String, Object> data) {
		return sqlSessionTemplate.insert("OrderDAO.insertDeliverInfo", data);
	}

	/** 구매 일자 확인(구매확정) */
	public List<String> checkOrderDate() {
		return sqlSessionTemplate.selectList("OrderDAO.checkOrderDate");
	}

	/** 구매 확정 변경 */
	public void updateOrderDate(List<String> checkOrderList) {
		sqlSessionTemplate.update("OrderDAO.updateOrderDate", checkOrderList);		
	}

	/** 교환/환불 이미지 조회 */
	public List<String> getSwapRefundImage(String imageBy) {
		return sqlSessionTemplate.selectList("OrderDAO.getSwapRefundImage", imageBy);
	}
}

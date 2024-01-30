package com.w2.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.w2.util.Search;
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
}

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

	public int modifyOrderStatus(List<Map<String, String>> checkList) {
		System.err.println("DAO modifyOrderStatus");
		int result = -1;
		for(Map<String, String> order : checkList) {
			result = sqlSessionTemplate.update("OrderDAO.modifyOrderStatus", order);
		}
		return result;
	}

	public int modifyDeliverNum(List<Map<String, String>> checkList) {
		System.err.println("DAO modifyDeliverNum");
		int result = -1;
		for(Map<String, String> order : checkList) {
			result = sqlSessionTemplate.update("OrderDAO.modifyDeliverNum", order);
		}
		return result;
	}

}

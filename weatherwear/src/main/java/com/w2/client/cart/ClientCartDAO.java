package com.w2.client.cart;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientCartDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int insertClientCart(ClientCartVO vo) {
		return sqlSessionTemplate.insert("ClientCartDAO.insertClientCart", vo);
	}
	
	public List<ClientCartDTO> getClientCart(ClientCartVO vo) {
		return sqlSessionTemplate.selectList("ClientCartDAO.getClientCart", vo);
	}
	
	public int updateClientCartCnt(ClientCartVO vo) {
		return sqlSessionTemplate.update("ClientCartDAO.updateClientCartCnt", vo);
	}
	
	public ClientCartVO checkClientCart(ClientCartVO vo) {
		return sqlSessionTemplate.selectOne("ClientCartDAO.checkClientCart", vo);
	}
	
	public int deleteClientCart(ClientCartVO vo) {
		return sqlSessionTemplate.delete("ClientCartDAO.deleteClientCart", vo);
	}
	
	public int checkProCnt(String opId) {
		return sqlSessionTemplate.selectOne("ClientCartDAO.checkProCnt", opId);
	}
	
	public int updateClientCart(ClientCartVO vo) {
		return sqlSessionTemplate.update("ClientCartDAO.updateClientCart", vo);
	}
	
	public List<ClientCartVO> getNonClientCart() {
		return sqlSessionTemplate.selectList("ClientCartDAO.getNonClientCart");
	}
}

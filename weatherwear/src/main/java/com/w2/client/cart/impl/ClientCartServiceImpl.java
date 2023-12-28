package com.w2.client.cart.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.client.cart.ClientCartDAO;
import com.w2.client.cart.ClientCartDTO;
import com.w2.client.cart.ClientCartService;
import com.w2.client.cart.ClientCartVO;

@Service
public class ClientCartServiceImpl implements ClientCartService {
	@Autowired
	private ClientCartDAO cartdao;

	@Override
	public int insertClientCart(ClientCartVO vo) {
		return cartdao.insertClientCart(vo);
	}

	@Override
	public List<ClientCartDTO> getClientCart(ClientCartVO vo) {
		return cartdao.getClientCart(vo);
	}

	@Override
	public int updateClientCartCnt(ClientCartVO vo) {
		return cartdao.updateClientCartCnt(vo);
	}

	@Override
	public ClientCartVO checkClientCart(ClientCartVO vo) {
		return cartdao.checkClientCart(vo);
	}

	@Override
	public int deleteClientCart(ClientCartVO vo) {
		return cartdao.deleteClientCart(vo);
	}

	@Override
	public int checkProCnt(String opId) {
		return cartdao.checkProCnt(opId);
	}

	@Override
	public int updateClientCart(ClientCartVO vo) {
		return cartdao.updateClientCart(vo);
	}

	@Override
	public List<ClientCartVO> getNonClientCart() {
		return cartdao.getNonClientCart();
	}
	
	
}

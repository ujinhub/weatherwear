package com.w2.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.client.ClientDAO;
import com.w2.client.ClientVO;
import com.w2.util.Search;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientDAO clientDao;
	
	@Override
	public List<ClientVO> getClientList(Search search) {
		return clientDao.getClientList(search);
	}

	@Override
	public int getClientListCnt(Search search) {
		return clientDao.getClientListCnt(search);
	}

	@Override
	public ClientVO getClient(ClientVO vo) {
		return clientDao.getClient(vo);
	}

}

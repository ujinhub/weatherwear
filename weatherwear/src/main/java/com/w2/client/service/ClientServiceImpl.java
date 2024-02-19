package com.w2.client.service;

import java.util.List;
import java.util.Map;

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

	@Override
	public ClientVO getClientEmail(ClientVO vo) {
		return clientDao.getClientEmail(vo);
	}

	@Override
	public List<ClientVO> getClientEmailList() {
		return clientDao.getClientEmailList();
	}

	@Override
	public ClientVO getClientFindInfo(Map<String, Object> param) {
		return clientDao.getClientFindInfo(param);
	}

	@Override
	public int updateClientPwd(ClientVO vo) {
		return clientDao.updateClientPwd(vo);
	}

	@Override
	public int updateClient(ClientVO vo) {
		return clientDao.updateClient(vo);
	}

	@Override
	public String getWishList(ClientVO vo) {
		return clientDao.getWishList(vo);
	}

	@Override
	public void changeCookieSetId(String cookieId, String clientId) {
		clientDao.changeCookieSetId(cookieId, clientId);		
	}

	@Override
	public void setLogDate(String clientId) {
		clientDao.setLogDate(clientId);		
	}
}

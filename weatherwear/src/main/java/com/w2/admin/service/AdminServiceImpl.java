package com.w2.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.admin.AdminDAO;
import com.w2.admin.AdminVO;
import com.w2.util.Search;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO adminDao;
	
	@Override
	public List<AdminVO> getAdminList(Search search) {
		return adminDao.getAdminList(search);
	}

	@Override
	public int getAdminListCnt(Search search) {
		return adminDao.getAdminListCnt(search);
	}

	@Override
	public AdminVO getAdmin(AdminVO vo) {
		return adminDao.getAdmin(vo);
	}

	@Override
	public int insertAdmin(AdminVO vo) {
		return adminDao.insertAdmin(vo);
	}

	@Override
	public int updateAdmin(AdminVO vo) {
		return adminDao.updateAdmin(vo);
	}

	@Override
	public int deleteAdmin(AdminVO vo) {
		return adminDao.deleteAdmin(vo);
	}
	
}

package com.web.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.login.dao.MemberDao;
import com.web.login.model.MemberBean;
import com.web.login.service.MemberService;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {
	MemberDao dao;
	
	@Autowired
	public void setDao(MemberDao dao) {
		this.dao = dao;
	}

	@Override
	public void saveMember(MemberBean mb) {
		dao.saveMember(mb);
	}

	@Override
	public MemberBean queryMember(String account) {
		return dao.queryMember(account);
	}

	@Override
	public List<MemberBean> getAllMembers() {
		return dao.getAllMembers();
	}

	@Override
	public void updatePwd(String account, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemberBean checkIDPassword(String account, String password) {
		try {
			return dao.checkIDPassword(account, password);
		}catch(Exception e){
			return null;
		}
		
	}

	@Override
	public boolean idExists(String account) {
		return dao.idExists(account);
	}

	@Override
	public MemberBean getMemberById(Integer memberId) {
		return dao.getMemberById(memberId);
	}

}

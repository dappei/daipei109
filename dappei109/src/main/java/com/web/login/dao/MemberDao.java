package com.web.login.dao;

import java.util.List;

import com.web.login.model.MemberBean;

public interface MemberDao {
	MemberBean checkIDPassword(String account, String password);

	boolean idExists(String account);

	MemberBean queryMember(String account);
	
	MemberBean getMemberById(Integer memberId);
	
	void saveMember(MemberBean mb);

	List<MemberBean> getAllMembers();
	
	void updatePwd(String account, String password);
}

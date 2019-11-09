package com.web.login.service;

import java.util.List;

import com.web.login.model.MemberBean;

public interface MemberService {

	MemberBean checkIDPassword(String account, String password);

	boolean idExists(String account);

	void saveMember(MemberBean mb); // 會員註冊

	MemberBean queryMember(String account);

	MemberBean getMemberById(Integer memberId);

	List<MemberBean> getAllMembers();

	void updatePwd(String account, String password);
}

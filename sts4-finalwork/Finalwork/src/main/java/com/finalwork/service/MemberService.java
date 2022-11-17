package com.finalwork.service;

import com.finalwork.domain.MemberDTO;

public interface MemberService {
	public void joinUser(MemberDTO params); // 회원가입
	//public MemberDTO loadUserByUsername(String uid);
}

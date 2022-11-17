
package com.finalwork.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.finalwork.domain.MemberDTO;

@Mapper
public interface MemberMapper {

	// 로그인
	MemberDTO getUserAccount(String id);
	
	// 회원가입
	public int saveUser(MemberDTO params);
	
}


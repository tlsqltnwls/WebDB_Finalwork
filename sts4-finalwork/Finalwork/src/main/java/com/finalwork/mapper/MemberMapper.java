package com.finalwork.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.finalwork.domain.MemberDTO;

@Mapper
public interface MemberMapper {

	public void saveUser(MemberDTO params);
	
}

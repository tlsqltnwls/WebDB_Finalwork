package com.finalwork;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.finalwork.domain.MemberDTO;
import com.finalwork.mapper.MemberMapper;

@SpringBootTest
public class MemberMapperTests {

	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	public void saveUser() {
		MemberDTO params = new MemberDTO();
		params.setId("1번 아이디");
		params.setPwd("1번 비번");
		
		int result = memberMapper.saveUser(params);
		System.out.println("결과는 "+result+"입니다.");
	}
}

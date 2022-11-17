package com.finalwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.finalwork.domain.MemberDTO;
import com.finalwork.mapper.MemberMapper;


@Service
public class MemberServiceImpl implements MemberService, UserDetailsService {

	@Autowired
	private MemberMapper memberMapper;
	
	// 회원가입
	@Override
	public void joinUser(MemberDTO params) {
		System.out.println("=============");
		System.out.println("=joinuser");
		System.out.println("=============");
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		params.setPwd(passwordEncoder.encode(params.getPassword()));
		memberMapper.saveUser(params);
		
	}
	
	@Override
	public MemberDTO loadUserByUsername(String id) throws UsernameNotFoundException {
		// 여기서 받은 유저 패스워드와 비교하여 로그인 인증
		MemberDTO memberDTO = memberMapper.getUserAccount(id);
		if (memberDTO == null) {
			throw new UsernameNotFoundException("User not authorized.");
		}
		return memberDTO;
	}
}

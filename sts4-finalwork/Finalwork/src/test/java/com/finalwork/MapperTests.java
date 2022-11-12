package com.finalwork;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.finalwork.domain.PostDTO;
import com.finalwork.mapper.PostMapper;

@SpringBootTest
public class MapperTests {

	@Autowired
	private PostMapper postMapper;
	
	@Test
	public void testOfInsert() {
		PostDTO params = new PostDTO();
		params.setSiteName("1번 사이트 이름");
		params.setSiteUrl("1번 사이트 url");
		params.setSiteId("1번 사이트 아이디");
		params.setSitePwd("1번 사이트 비밀번호");
		params.setMemUid((long) 1);
		
		int result = postMapper.insertPost(params);
		System.out.println("결과는 "+result+"입니다.");
	}
	
	@Test
	public void testOfSelectDetail() {
		PostDTO post = postMapper.selectPostDetail((long) 1);
		try {
			String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
			System.out.println("==============");
			System.out.println(postJson);
			System.out.println("==============");
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOfUpdate() {
		PostDTO params = new PostDTO();
		params.setSiteName("1번 사이트 이름 수정");
		params.setSiteUrl("1번 사이트 url 수정");
		params.setSiteId("1번 사이트 아이디 수정");
		params.setSitePwd("1번 사이트 비밀번호 수정");
		params.setIdx((long) 1);
		
		int result = postMapper.updatePost(params);
		
		System.out.println("결과는 "+result+"입니다./n");
		if(result == 1) {
			PostDTO post = postMapper.selectPostDetail((long) 1);
			try {
				String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
				
				System.out.println("==============");
				System.out.println(postJson);
				System.out.println("==============");
			
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testOfDelete() {
		int result = postMapper.deletePost((long) 1);
		if(result == 1) {
			PostDTO post = postMapper.selectPostDetail((long) 1);
			try {
				String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
				
				System.out.println("==============");
				System.out.println(postJson);
				System.out.println("==============");
			
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testSelectList() {
		int postTotalCount = postMapper.selectPostTotalCount();
		if(postTotalCount > 0) {
			System.out.println("postTotalCount > 0");
			List<PostDTO> postList = postMapper.selectPostList();
			if (CollectionUtils.isEmpty(postList) == false) {
				for (PostDTO post : postList) {
					System.out.println("============");
					System.out.println(post.getSiteName());
					System.out.println(post.getSiteUrl());
					System.out.println(post.getSiteId());
					System.out.println(post.getSitePwd());
					System.out.println(post.getMemUid());	
					System.out.println("============");
				}
			} else {System.out.println("empty");}
		} else {
			System.out.println("postTotalCount <= 0");
		}
	}
}

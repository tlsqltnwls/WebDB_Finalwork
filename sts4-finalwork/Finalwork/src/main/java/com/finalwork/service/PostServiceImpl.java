package com.finalwork.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalwork.domain.PostDTO;
import com.finalwork.mapper.PostMapper;
import com.finalwork.paging.Criteria;
import com.finalwork.paging.PaginationInfo;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostMapper postMapper;
	
	@Override
	public boolean registerPost(PostDTO params) {
		
		int queryResult = 0;
		
		if(params.getIdx() == null) {
			System.out.println("====================");
	        System.out.println("getIdx"+params.getIdx());
	        System.out.println("====================");
	        
			queryResult = postMapper.insertPost(params);
			System.out.println("====================");
	        System.out.println("hi?");
	        System.out.println("====================");
		} else {
			queryResult = postMapper.updatePost(params);
		}
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public PostDTO getPostDetail(long idx) {
		return postMapper.selectPostDetail(idx);
	}

	@Override
	public boolean deletePost(long idx) {
		// TODO Auto-generated method stub
		int queryResult = 0;
		
		PostDTO post = postMapper.selectPostDetail(idx);
		
		if (post != null && "N".equals(post.getDeleteYn())) {
			queryResult = postMapper.deletePost(idx);
		}
		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<PostDTO> getPostList(PostDTO params) {
		// TODO Auto-generated method stub
		List<PostDTO> postList = Collections.emptyList();
		
		int postTotalCount = postMapper.selectPostTotalCount(params);
		
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(postTotalCount);
		
		params.setPaginationInfo(paginationInfo);
		
		if(postTotalCount > 0) {
			postList = postMapper.selectPostList(params);
		}
		return postList;
	}
}

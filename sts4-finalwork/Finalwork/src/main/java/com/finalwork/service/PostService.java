package com.finalwork.service;

import java.util.List;

import com.finalwork.domain.PostDTO;
import com.finalwork.paging.Criteria;

public interface PostService {

	public boolean registerPost(PostDTO params);	// 게시글 생성 및 수정 처리
	public PostDTO getPostDetail(long idx);			// 하나의 게시글 조회
	public boolean deletePost(long idx);			// 게시글 조회하고 deletr_Yn을 Y로 변경하여 삭제 처리
	public List<PostDTO> getPostList(PostDTO params); // 삭제되지 않은 게시글을 전체 조회
}

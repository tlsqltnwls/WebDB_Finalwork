
package com.finalwork.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.finalwork.domain.PostDTO;

// Post 테이블과 통신하기 위한 인터페이스
@Mapper
public interface PostMapper {

	public int insertPost(PostDTO params);		// 게시글 생성
	public PostDTO selectPostDetail(Long idx);	// 하나의 게시글 조회
	public int updatePost(PostDTO params);		// 게시글 수정
	public int deletePost(Long idx);			// 게시글 삭제
	public List<PostDTO> selectPostList(PostDTO params);		// 게시글 목록 조회
	public int selectPostTotalCount(PostDTO params);			// 삭제여부(delete_yn)가 'N'으로 지정된 게시글 개수 조회
}

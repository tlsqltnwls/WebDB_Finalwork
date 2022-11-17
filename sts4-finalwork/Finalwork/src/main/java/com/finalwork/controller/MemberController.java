package com.finalwork.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalwork.constant.Method;
import com.finalwork.domain.MemberDTO;
import com.finalwork.domain.PostDTO;
import com.finalwork.paging.Criteria;
import com.finalwork.service.MemberService;
import com.finalwork.service.PostService;
import com.finalwork.util.UiUtils;


@Controller
public class MemberController extends UiUtils {

	@Autowired
	private MemberService memberService;
	@Autowired
	private PostService postService;
	
	// localhost:8080 시 login 으로 redirect
    @GetMapping
    public String root() {
        return "redirect:/finalwork/login";
    }
    
    // 로그인 폼
    @GetMapping("/finalwork/login")
    public String login(@RequestParam(value = "error", required = false)String error,
            @RequestParam(value = "exception", required = false)String exception,
            Model model){
    	String err = "아이디 또는 비밀번호가 맞지 않습니다.";
    	
    	model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        model.addAttribute("t", err);
        return "finalwork/login";
    }
    
	// 회원가입 폼
	@GetMapping(value = "/finalwork/signUp")
	public String signUpForm() {
		return "finalwork/signup";
	}
	
	// 로그인 실패 폼
	@GetMapping("/finalwork/access_denied")
	public String accessDenied() {
	   return "finalwork/access_denied";
	}
	
	// 회원가입 진행
	@PostMapping(value = "/finalwork/signUp")
	public String signUp (MemberDTO params) {
		memberService.joinUser(params);
		return "redirect:/finalwork/login";
	}

	
	// 유저 작성 페이지
	@GetMapping("/post/write.do")
	public String openPostWrite(@RequestParam(value = "idx", required = false) Long idx, Model model, 
			Authentication authentication, @AuthenticationPrincipal MemberDTO memberDTO) {
		System.out.println("====================");
        System.out.println("유저화면");
        System.out.println("====================");
		
		
		// Authentication 객체를 통해 유저 정보를 가져올 수 있다.
		// MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal(); //userDetail 객체를 가져옴
	    model.addAttribute("info",memberDTO.getUsername() + "님 개인정보"); //유저 아이디
	    model.addAttribute("uid", memberDTO.getUid() + " 회원님");
	    
	    	PostDTO post = new PostDTO();
	    
			/* 제대로 객체 가져오는지 테스트
			 * System.out.println("====================");
			 * System.out.println("유저번호 : "+post.getMemUid());
			 * System.out.println("memberDTO uid : "+memberDTO.getUid());
			 * System.out.println("idx : "+idx); System.out.println("====================");
			 */
        
	    if (idx == null) {
	    	model.addAttribute("post", new PostDTO());
	    } else {
	    	post = postService.getPostDetail(idx);
	    	if (post == null) {
	    		return "redirect:/post/list.do";
	    	}
	    	model.addAttribute("post", post);
	    }
	    
	    return "post/write";
	}
	
	// 작성, 수정 눌렀을 때
	@PostMapping(value = "/post/register.do")
	public String registerPost(final PostDTO params, Model model) {
		System.out.println("====================");
        System.out.println("registerPost");
        System.out.println("====================");
		try {
			boolean isRegistered = postService.registerPost(params);
				
			if( isRegistered == false ) {
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/post/list.do", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
				// TODO: handle exception
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/post/list.do", Method.GET, null, model);
		} catch (Exception e) {
				// TODO: handle exception
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/post/list.do", Method.GET, null, model);
			}
			
			return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/post/list.do", Method.GET, null, model);
	}
	
	// 게시글 목록 관리
	@GetMapping(value = "/post/list.do")
	public String openPostList(@ModelAttribute("params") PostDTO params, Model model, 
			@AuthenticationPrincipal MemberDTO memberDTO) {
		model.addAttribute("info",memberDTO.getUsername() + "님 개인정보"); //유저 아이디
		
		List<PostDTO> postList = postService.getPostList(params);
		model.addAttribute("postList", postList);
		
		return "post/list";
	}
	
	// 개인정보 작성 자세히 볼 때
	@GetMapping(value = "/post/view.do")
	public String openPostDetail(@ModelAttribute("params") PostDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if( idx == null ) {
			//return showMessageWithRedirect("올바르지 않은 접근입니다.", "/post/list.do", Method.GET, null, model);
			return "redirect:/post/list.do";
		}
		
		PostDTO post = postService.getPostDetail(idx);
		
		if( post == null || "Y".equals(post.getDeleteYn())) {
			//return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/post/list.do", Method.GET, null, model);
			return "redirect:/post/list.do";
		}
		model.addAttribute("post", post);
		
		return "post/view";
	}
	
	// 게시글 삭제
	@PostMapping(value = "/post/delete.do")
	public String deletePost(@ModelAttribute("params") PostDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if( idx == null ) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/post/list.do", Method.GET, null, model);
		}
		//Map<String, Object> pagingParams = getPagingParams(params);
		
		try {
			boolean isDeleted = postService.deletePost(idx);
			if( isDeleted == false ) {
				return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/post/list.do", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/post/list.do", Method.GET, null, model);
		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/post/list.do", Method.GET, null, model);
		}
		
		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/post/list.do", Method.GET, null, model);
	}
	
}

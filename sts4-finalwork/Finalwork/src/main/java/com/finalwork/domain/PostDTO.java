
package com.finalwork.domain;

import java.time.LocalDateTime;

// 게시물 관리하는 post table의 DTO
public class PostDTO extends CommonDTO {

	private Long idx;					// 글 번호(PK)
	private String siteName;			// 사이트 이름 (제목)
	private String siteUrl;				// 사이트 url
	private String siteId;				// 사이트 아이디
	private String sitePwd;				// 사이트 비밀번호
	private Long memUid;				// 작성한 회원번호 (FK)

	
	@Override
	public String toString() {
		return "PostDTO [idx=" + idx + ", siteName=" + siteName + ", siteUrl=" + siteUrl + ", siteId=" + siteId
				+ ", sitePwd=" + sitePwd + ", memUid=" + memUid + "]";
	}
	public Long getIdx() {
		return idx;
	}
	public void setIdx(Long idx) {
		this.idx = idx;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getSitePwd() {
		return sitePwd;
	}
	public void setSitePwd(String sitePwd) {
		this.sitePwd = sitePwd;
	}
	public Long getMemUid() {
		return memUid;
	}
	public void setMemUid(Long memUid) {
		this.memUid = memUid;
	}

	
}

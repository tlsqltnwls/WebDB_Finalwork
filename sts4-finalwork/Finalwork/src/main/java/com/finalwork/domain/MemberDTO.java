package com.finalwork.domain;

import java.time.LocalDateTime;

public class MemberDTO {

	private Long uid;					// 회원 번호(PK)
	private String id;					// 회원 아이디
	private String pwd;					// 회원 비밀번호
	private LocalDateTime appendTime;	// 등록일
	private LocalDateTime updateTime;	// 수정일
	private LocalDateTime deleteTime;	// 삭제일
	
	
	
	@Override
	public String toString() {
		return "MemberDTO [uid=" + uid + ", id=" + id + ", pwd=" + pwd + ", appendTime=" + appendTime + ", updateTime="
				+ updateTime + ", deleteTime=" + deleteTime + "]";
	}
	
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public LocalDateTime getAppendTime() {
		return appendTime;
	}
	public void setAppendTime(LocalDateTime appendTime) {
		this.appendTime = appendTime;
	}
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	public LocalDateTime getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(LocalDateTime deleteTime) {
		this.deleteTime = deleteTime;
	}
	
	
	
}

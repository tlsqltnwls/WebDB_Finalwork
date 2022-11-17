package com.finalwork.domain;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// 회원 정보
@SuppressWarnings("serial")
public class MemberDTO implements UserDetails {

	private Long uid;					// 회원 번호(PK)
	private String id;					// 회원 아이디
	private String pwd;					// 회원 비밀번호
	private LocalDateTime appendTime;	// 등록일
	private LocalDateTime updateTime;	// 수정일
	private LocalDateTime deleteTime;	// 삭제일
	
    
    @Override
    public String getPassword() {
        return this.pwd;
    }

    // 시큐리티의 userName
    // -> 따라서 얘는 인증할 때 id를 봄
    @Override
    public String getUsername() {
        return this.id;
    }

    @Override
    public boolean isAccountNonExpired() { // 계정이 만료되지 않았는 지 리턴 (true : 만료안됨)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정이 잠겨있지 않았는 지 리턴 (true : 잠기지 않음)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 비밀번호가 만료되지 않았는 지 리턴 (true 만료안됨)
        return true;
    }

    @Override
    public boolean isEnabled() { // 계정이 활성화(사용가능)인 지 리턴 (true : 활성화)
        return true;
    }
    
    
	
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	
}

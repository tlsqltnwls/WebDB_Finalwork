package com.finalwork.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

//import com.finalwork.service.MemberService;

import lombok.RequiredArgsConstructor;

// security 설정 파일
@SuppressWarnings("deprecation")
@EnableWebSecurity        //spring security 를 적용한다는 Annotation
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	private MemberService memberService;
	private AuthFailureHandler AuthFailureHandler;

    @Bean
    public BCryptPasswordEncoder Encoder() { // 비밀번호 암호화
        return new BCryptPasswordEncoder();
    }
    
	/* static 관련설정은 무시 */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring().antMatchers( "/css/**", "/js/**", "/img/**");
    }
    
	/**
     * 규칙 설정
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().ignoringAntMatchers("/api/**") /* REST API 사용 예외처리 */
        		.and()
            .formLogin()
            	.usernameParameter("id")
            	.passwordParameter("pwd")
                .loginPage("/finalwork/login")
                .loginProcessingUrl("/login_proc")
                .defaultSuccessUrl("/post/list.do")
                .failureUrl("/finalwork/access_denied") // 인증에 실패했을 때 보여주는 화면 url, 로그인 form으로 파라미터값 error=true로 보낸다.
                .failureHandler(AuthFailureHandler) // 로그인 실패했을 때
                .and()
            .csrf().disable();		//로그인 창
    }
    
 
 
 
}

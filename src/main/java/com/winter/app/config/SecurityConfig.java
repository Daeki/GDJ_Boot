package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.winter.app.member.MemberService;

import jakarta.validation.Valid;

@Configuration
//@EnableWebSecurity(debug = true)
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private SecurityLoginSucessHandler handler;
	
	@Autowired
	private SecurityLoginFailHandler failHandler;
	
	@Autowired
	private SecurityLogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
	private MemberService memberService;
	
	@Value("${security.debugMode}")
	private boolean debugMode; 
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web
				.debug(debugMode)
				.ignoring()
				.requestMatchers("/css/**")
				.requestMatchers("/js/**")
				.requestMatchers("/vendor/**")
				.requestMatchers("/img/**")
				.requestMatchers("/favicon/**")
				;
	}
	
	@Bean
	SecurityFilterChain filterChain (HttpSecurity security) throws Exception {
//		security.cors()
//				.and()
//				.csrf()
//				.disable();
		security
			//권한에 관련된 설정
			.authorizeHttpRequests(
				(authorizeRequests)->
					
						authorizeRequests
							.requestMatchers("/").permitAll()
							.requestMatchers("/member/add").permitAll()
							.requestMatchers("/member/page", "/member/logout").authenticated()
							.requestMatchers("/notice/list").authenticated()
							.requestMatchers("/notice/add", "/notice/delete").hasRole("ADMIN")
							.requestMatchers("/notice/update").hasAnyRole("ADMIN", "MANGER")
							.anyRequest().permitAll()
					
			)//authorizeHttpRequests 끝부분
			.formLogin(
					(login)->
						login
							.loginPage("/member/login")
							//.defaultSuccessUrl("/")
							.successHandler(handler)
							//.failureUrl("/notice/list")
							.failureHandler(failHandler)
							//파라미터이름이 username이 아닌 'id'를 사용 했을 경우
							//.usernameParameter("id")
							//파라미터이름이 password가 아닌 'pw'를 사용 했을 경우
							//.passwordParameter("pw")
							.permitAll()
			)//formLogin 끝부분
			.logout(
					(logout)->
						logout
							//.logoutUrl("/member/logout")
							.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
							//.logoutSuccessUrl("/")
							.logoutSuccessHandler(logoutSuccessHandler)
							.invalidateHttpSession(true) //로그아웃시 seession만료
							.permitAll()
			)//logout 끝부분
			.rememberMe(
					(rememberMe)->
						rememberMe
							.rememberMeParameter("rememberMe")
							.tokenValiditySeconds(600)
							.key("rememberMe")
							.userDetailsService(memberService)
							.authenticationSuccessHandler(handler)
							.useSecureCookie(false)
			)//rememberMe 끝 부분
			.sessionManagement(
					(sessionManagement)->
						sessionManagement
							.maximumSessions(1)
							.maxSessionsPreventsLogin(false)
							.expiredUrl("/expired")
							
			)//sessionManagement 끝
			.oauth2Login(
					(oauth2Login)-> 
						oauth2Login.userInfoEndpoint(
								(ue)->ue.userService(memberService)
						)
			)//oauth2Login 끝부분
			
			
			;	
	
		
		
		
		
		
		
		
		return security.build();
		
	}
	


}

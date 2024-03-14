package com.winter.app.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.winter.app.member.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
@Component
@Slf4j
public class SecurityLogoutSuccessHandler implements LogoutSuccessHandler {
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String adminKey;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		//카카오 로그아웃
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		
		if(memberVO.getSocial()==null) {
			response.sendRedirect("/");
			return;
		}
		
		if(memberVO.getSocial().toUpperCase().equals("KAKAO")) {
			
			MultiValueMap<String, String> p = new LinkedMultiValueMap<>();
			p.add("target_id_type", "user_id");
			p.add("target_id", memberVO.getUsername());
			
			WebClient webClient = WebClient.create("https://kapi.kakao.com/v1/user/logout");
			Mono<String> result = webClient
										.post()
										.header("Authorization", "KakaoAK "+adminKey)
										.body(BodyInserters.fromFormData(p))
										.retrieve()
										.bodyToMono(String.class)
										;
			
			log.info("Kakao Logout {}", result.block());
			
					
			
		}
		
		
		
	}
	
	

}

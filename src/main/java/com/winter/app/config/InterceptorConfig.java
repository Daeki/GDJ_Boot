package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.winter.app.interceptors.LoginInterceptor;
import com.winter.app.interceptors.TestInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private TestInterceptor testInterceptor;
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//어떤 URL이 왔을 때 어떤 Interceptor를 거치게 할것인가??
//		registry.addInterceptor(testInterceptor)
//				.addPathPatterns("/notice/**")
//				.excludePathPatterns("/notice/add");
//		
//		registry.addInterceptor(loginInterceptor)
//				.addPathPatterns("/**")
//				.excludePathPatterns("");
		
		registry.addInterceptor(localeChangeInterceptor)
				.addPathPatterns("/**");
		
	}
	
	

}

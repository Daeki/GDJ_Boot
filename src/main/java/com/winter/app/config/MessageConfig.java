package com.winter.app.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration// xml
public class MessageConfig implements WebMvcConfigurer {
	
   @Bean
   LocaleResolver localeResolver() {
		//session
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);
		//cookie
		
		CookieLocaleResolver cResolver = new CookieLocaleResolver();
		cResolver.setDefaultLocale(Locale.KOREAN);
		//cResolver.setCookieName("lang");
		
		return cResolver;
	}
    
    @Bean
    LocaleChangeInterceptor changeInterceptor() {
    	LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
    	changeInterceptor.setParamName("lang");
    	
    	return changeInterceptor;
    }
    
    
    
    

}

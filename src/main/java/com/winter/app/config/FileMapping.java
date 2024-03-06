package com.winter.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//***-context.xml
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileMapping implements WebMvcConfigurer {
	@Value("${app.upload.url}")
	private String urlPath;// /files/**
	@Value("${app.upload.base}")
	private String filePath;//  D://upload/ 
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler(urlPath)
				.addResourceLocations(filePath);
		
	}

}

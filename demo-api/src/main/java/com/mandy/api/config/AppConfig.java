package com.mandy.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("com.mandy.api")
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
	
	private static final int CACHE_PERIOD = 31556926;
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/").setCachePeriod(CACHE_PERIOD);
	}
	 
}
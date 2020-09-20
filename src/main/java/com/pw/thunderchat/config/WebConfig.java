package com.pw.thunderchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author André
 * Adiciona configurações cors para requisições na API
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
	/**
	 * Mapeamento do cors 
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*");
	}
}

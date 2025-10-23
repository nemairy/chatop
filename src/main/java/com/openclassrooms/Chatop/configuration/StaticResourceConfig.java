package com.openclassrooms.Chatop.configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer{
	
	@Value("${app.upload.dir:uploads}")
	private String uploadDir;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		Path rootPath = Paths.get(uploadDir).toAbsolutePath().normalize();
		
	    String location = rootPath.toUri().toString();
	    registry.addResourceHandler("/uploads/**")
	            .addResourceLocations(location);
	    
	    registry.addResourceHandler("/api/uploads/**")
	            .addResourceLocations(location);
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/uploads/**")
		        .allowedOrigins("http://localhost:4200")
		        .allowedMethods("GET")
		        .allowCredentials(false);
		
	}

}

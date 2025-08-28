package com.openclassrooms.Chatop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.openclassrooms.Chatop.service.CustomUserDetailService;

@Configuration
public class SecurityConfig {
	
	
	
	//Filters chain
	@Bean
	public SecurityFilterChain securityChain(HttpSecurity http) throws Exception{
		
		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth
		.requestMatchers("/auth/**").permitAll()
		.requestMatchers("/locataire").hasRole("LOCAT")
		.requestMatchers("/proprietaire").hasRole("PROPRIE")
		.anyRequest().authenticated()
		);
		//.addFilterBefore(null, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http,
			CustomUserDetailService userDetService, PasswordEncoder passwordEncoder) throws Exception {
		
		return http.getSharedObject(AuthenticationManagerBuilder.class)
		           .userDetailsService(userDetService)
		           .passwordEncoder(passwordEncoder)
		           .and()
		           .getOrBuild();
		           
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

}

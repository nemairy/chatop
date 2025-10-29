package com.openclassrooms.Chatop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.openclassrooms.Chatop.security.CustomUserDetailService;
import com.openclassrooms.Chatop.security.JwtAuthFilter;




@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter jwtFilter;


	//Filters chain
	@Bean
	public SecurityFilterChain securityChain(HttpSecurity http) throws Exception{

		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth
		.requestMatchers("/", "/auth/login", "/auth/register", "/login", "/register",
				         "/uploads/**", "/api/uploads/**",
				         "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
		.anyRequest().authenticated())
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

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

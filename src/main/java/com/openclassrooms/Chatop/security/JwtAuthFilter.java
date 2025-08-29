package com.openclassrooms.Chatop.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter{
	
	private final JwtUtil jwtUtil;
	private final CustomUserDetailService myUserDetails;
	
	//Pass the Http requests and responses throw the filter chain
	public void doFilterInternal(HttpServletRequest request,
			                     HttpServletResponse response,
			                     FilterChain chain) throws ServletException, IOException{
		
		String authHeader = request.getHeader("Authorization");
		String email = null;
		String token = null;
		
		if(authHeader != null && authHeader.startsWith("Brear ")) {
			token = authHeader.substring(7);
			email = jwtUtil.extractEmail(token);
		}
		//current authentication information
		if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = myUserDetails.loadUserByUsername(email);
			
			if(userDetails != null) {
				UsernamePasswordAuthenticationToken authToken = 
						new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
				
			}
			
			chain.doFilter(request, response);
		}
		
		
		
	}

}

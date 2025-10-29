package com.openclassrooms.Chatop.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityHeadersFilter extends OncePerRequestFilter {
	  @Override
	  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
	      throws IOException, ServletException {

	    // set before the handler writes the body
	    if (req.getRequestURI().startsWith("/uploads/") || req.getRequestURI().startsWith("/api/uploads/")){
	      res.setHeader("Cross-Origin-Resource-Policy", "cross-origin");
	      res.setHeader("Vary", "Origin");

	      String origin = req.getHeader("Origin");
	      if ("http://localhost:4200".equals(origin)) {
	        res.setHeader("Access-Control-Allow-Origin", origin);
	      }
	    }
	    chain.doFilter(req, res);
	  }

}

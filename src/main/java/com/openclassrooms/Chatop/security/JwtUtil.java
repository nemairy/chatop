package com.openclassrooms.Chatop.security;


import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
	
	//init from app.properties
	@Value("${jwt.secret}")
	private String secretKey;
	
	@Value("${jwt.expiration}")
	private long expiration;
	
	//My String secretKey to byte
	private SecretKey key;
	private JwtParser parser;
	
	//Constructor
	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
		this.parser = Jwts.parser().verifyWith(key).build();
	}
	
	public String generateToken(String email, String role) {
		
		return Jwts.builder()
				.subject(email)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(key, Jwts.SIG.HS256)
				.compact();		
	}
	
	public String extractEmail(String token) {
		
		return  parser
				.parseSignedClaims(token)
				.getPayload()
		        .getSubject();
	}

}

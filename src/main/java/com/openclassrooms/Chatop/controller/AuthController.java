package com.openclassrooms.Chatop.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.Chatop.DTOs.AuthResponse;
import com.openclassrooms.Chatop.DTOs.UserLoginDto;
import com.openclassrooms.Chatop.DTOs.UserRegisterDto;
import com.openclassrooms.Chatop.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
	
	private final AuthService authService;
	
	/*
	 * @PostMapping("/auth/register") public ResponseEntity<AuthResponse>
	 * register(@RequestBody UserRegisterDto request) {
	 * System.out.println("Register called with: " + request); String token =
	 * authService.register(request); return ResponseEntity.ok(new
	 * AuthResponse(token)); }
	 */

	
	
	  @PostMapping("/auth/register") 
	  public String register(@RequestBody
	  UserRegisterDto regisRequest) { authService.register(regisRequest); return
	  "User registered successfully"; }
	 
	
	
	  @PostMapping("/auth/login") 
	  public AuthResponse login(@RequestBody
	     UserLoginDto loginRequest) {
	  
	     return authService.login(loginRequest); }
	 
	
	/*
	 * @PostMapping("/auth/login") public ResponseEntity<Map<String, String>>
	 * login(@RequestBody UserLoginDto loginRequest) {
	 * System.out.println("AuthController.login called with: " +
	 * loginRequest.getEmail()); AuthResponse resp =
	 * authService.login(loginRequest);
	 * System.out.println("AuthController returning token: " + resp.getToken());
	 * return ResponseEntity.ok(Map.of("token", resp.getToken())); }
	 */
	


}

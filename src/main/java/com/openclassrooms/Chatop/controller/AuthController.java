package com.openclassrooms.Chatop.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.Chatop.DTOs.AuthResponse;
import com.openclassrooms.Chatop.DTOs.MeResponse;
import com.openclassrooms.Chatop.DTOs.UserLoginDto;
import com.openclassrooms.Chatop.DTOs.UserRegisterDto;
import com.openclassrooms.Chatop.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/auth/register")
	public String register(@RequestBody UserRegisterDto regisRequest) {
		authService.register(regisRequest);
		return "User registered successfully";
	}

	@PostMapping("/auth/login")
	public AuthResponse login(@RequestBody UserLoginDto loginRequest) {

		return authService.login(loginRequest);
	}

	@GetMapping("/auth/me")
	public MeResponse me(Authentication auth) {
		String email = auth.getName();
		return authService.me(email);
	}
}

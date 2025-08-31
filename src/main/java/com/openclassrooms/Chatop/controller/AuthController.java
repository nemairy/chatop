package com.openclassrooms.Chatop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}

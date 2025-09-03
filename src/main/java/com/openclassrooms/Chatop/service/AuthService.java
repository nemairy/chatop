package com.openclassrooms.Chatop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.Chatop.DTOs.*;
import com.openclassrooms.Chatop.model.UserN;
import com.openclassrooms.Chatop.repository.UserRepository;
import com.openclassrooms.Chatop.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
	@Autowired
	private final UserRepository repository;
	
	private final JwtUtil jwtUtil;
	private final PasswordEncoder encoder;

	public void register(UserRegisterDto regisRequest) {

		if (repository.findByEmail(regisRequest.getEmail()).isPresent()) {
			throw new RuntimeException("Email is already exit!");
		}

		UserN user = new UserN();
		user.setName(regisRequest.getName());
		user.setEmail(regisRequest.getEmail());
		user.setPassword(encoder.encode(regisRequest.getPassword()));

		repository.save(user);
	}

	public AuthResponse login(UserLoginDto loginRequest) {

		UserN user = repository.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new RuntimeException("Email not found!"));

		if (!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new RuntimeException("Wrong password!");
		}

		String token = jwtUtil.generateToken(user.getEmail());

		return new AuthResponse(token);
	}

	public MeResponse me(String email) {

		var user = repository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not founds!"));

		return new MeResponse(user.getId(), user.getName(), user.getEmail());
	}

}

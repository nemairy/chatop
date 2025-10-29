package com.openclassrooms.Chatop.service.api;

import com.openclassrooms.Chatop.DTOs.AuthResponse;
import com.openclassrooms.Chatop.DTOs.MeResponse;
import com.openclassrooms.Chatop.DTOs.UserLoginDto;
import com.openclassrooms.Chatop.DTOs.UserRegisterDto;

public interface AuthService {
	AuthResponse register(UserRegisterDto regisRequest);

	AuthResponse login(UserLoginDto loginRequest);

	MeResponse me(String email);
}

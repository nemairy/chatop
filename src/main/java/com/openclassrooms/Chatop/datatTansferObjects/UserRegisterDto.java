package com.openclassrooms.Chatop.datatTansferObjects;

import com.openclassrooms.Chatop.model.Role;

import lombok.Data;

@Data
public class UserRegisterDto {
	
	private String email;
	private String password;
	private String role;

}

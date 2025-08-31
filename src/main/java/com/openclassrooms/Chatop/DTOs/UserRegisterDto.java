package com.openclassrooms.Chatop.DTOs;

import com.openclassrooms.Chatop.model.Role;

import lombok.Data;

@Data
public class UserRegisterDto {
	
	private String name;
	private String email;
	private String password;
	

}

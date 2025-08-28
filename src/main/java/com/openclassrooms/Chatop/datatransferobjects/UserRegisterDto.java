package com.openclassrooms.Chatop.datatransferobjects;

import com.openclassrooms.Chatop.model.Role;

import lombok.Data;

@Data
public class UserRegisterDto {
	
	private String email;
	private String password;
	private String role;

}

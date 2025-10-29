package com.openclassrooms.Chatop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.Chatop.DTOs.MeResponse;
import com.openclassrooms.Chatop.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/{id}")
	public MeResponse getUser(@PathVariable Long id) {
		return userService.getPublicUser(id);
	}


}

package com.openclassrooms.Chatop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.Chatop.DTOs.MessageDto;
import com.openclassrooms.Chatop.service.api.MessageService;

import jakarta.validation.Valid;

@RestController
public class MessageController {
	
	@Autowired
	private MessageService service;
	
	@PostMapping("/messages")
	public Map<String, String> create(Authentication auth, @Valid @RequestBody MessageDto dto){
		String authorEmail = auth.getName();
		String sentResult = service.messageCreate(authorEmail, dto);
		
		return Map.of("message", sentResult);
		
	}

}

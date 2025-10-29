package com.openclassrooms.Chatop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.openclassrooms.Chatop.DTOs.MeResponse;
import com.openclassrooms.Chatop.model.UserN;
import com.openclassrooms.Chatop.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public MeResponse getPublicUser(Long id) {
		UserN user = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"+ id));

		return new MeResponse(
				user.getId(),
				user.getName(),
				user.getEmail(),
				user.getCreatedAt(),
				user.getUpdatedAt());
	}
}

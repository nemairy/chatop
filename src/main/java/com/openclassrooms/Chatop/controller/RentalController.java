package com.openclassrooms.Chatop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.Chatop.DTOs.RentalCreUpDto;
import com.openclassrooms.Chatop.DTOs.RentalDto;
import com.openclassrooms.Chatop.service.RentalService;

@RestController
public class RentalController {
	
	@Autowired
	private RentalService service;
	
	@GetMapping("/rentals")
	public List<RentalDto> rentList() {
		return service.rentList();
	}
	
	@GetMapping("/rentals/:id")
	public RentalDto getById(Long id) {
		return service.getById(id);
	}
	
	@PostMapping("/rentals")
	public RentalDto create(Authentication auth, @RequestBody RentalCreUpDto dto) {
		return service.createRental(auth.getName(), dto);
	}
	
	@PutMapping("/rentals/:id")
	public RentalDto update(@PathVariable Long id, @RequestBody RentalCreUpDto dto) {
	    return service.updateRental(id, dto);
	}

}

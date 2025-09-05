package com.openclassrooms.Chatop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

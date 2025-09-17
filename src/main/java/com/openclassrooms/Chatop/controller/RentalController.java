package com.openclassrooms.Chatop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.Chatop.DTOs.RentalCreUpDto;
import com.openclassrooms.Chatop.DTOs.RentalDto;
import com.openclassrooms.Chatop.service.FileStorageService;
import com.openclassrooms.Chatop.service.RentalService;

@RestController
public class RentalController {

	@Autowired
	private RentalService service;
	
	@Autowired
	private FileStorageService fileStorage;

	@GetMapping("/rentals")
	public Map<String, List<RentalDto>> rentList() {
		return Map.of("rentals", service.rentList());
	}

	@GetMapping("/rentals/:id")
	public RentalDto getById(Long id) {
		return service.getById(id);
	}

	@PostMapping("/rentals")
	public RentalDto create(Authentication auth, @RequestBody RentalCreUpDto dto) {
		return service.createRental(auth.getName(), dto);
	}

	// Multipart create version
	@PostMapping(value = "/rentals", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public RentalDto createMultipart(Authentication auth,
			@RequestParam("name") String name,
			@RequestParam("surface") Integer surface, 
			@RequestParam("price") Integer price,
			@RequestPart("picture") MultipartFile picture,
			@RequestParam(value = "description", required = false) String description) {
		// save file & get public URL
		String pictureUrl = fileStorage.storeAndGetPublicUrl(picture);

		// build your existing input DTO
		RentalCreUpDto in = new RentalCreUpDto();
		in.setName(name);
		in.setSurface(surface);
		in.setPrice(price);
		in.setPicture(pictureUrl); 
		in.setDescription(description);

		// reuse the same service
		return service.createRental(auth.getName(), in);
	}

	@PutMapping("/rentals/:id")
	public RentalDto update(@PathVariable Long id, @RequestBody RentalCreUpDto dto) {
		return service.updateRental(id, dto);
	}

}

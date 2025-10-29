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
import com.openclassrooms.Chatop.service.api.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
public class RentalController {

	@Autowired
	private RentalService service;

	@Autowired
	private FileStorageService fileStorage;

	@GetMapping("/rentals")
	@Operation(summary = "Get all rentals", description = "Retrieves a list of all rentals.", responses = {
			@ApiResponse(responseCode = "200", description = "List of rentals retrieved successfully", content = @Content(schema = @Schema(implementation = RentalDto.class))),
			@ApiResponse(responseCode = "401", description = "User is unauthorized", content = @Content()) }, security = @SecurityRequirement(name = "bearerAuth"))
	public Map<String, List<RentalDto>> rentList() {
		return Map.of("rentals", service.rentList());
	}

	@GetMapping("/rentals/{id}")
	@Operation(summary = "Get rental by ID", description = "Retrieves details of a rental by their unique ID.", responses = {
			@ApiResponse(responseCode = "200", description = "Rental found", content = @Content(schema = @Schema(implementation = RentalDto.class))),
			@ApiResponse(responseCode = "401", description = "User is unauthorized", content = @Content()) }, security = @SecurityRequirement(name = "bearerAuth"))

	public RentalDto getById(@PathVariable Long id) {
		return service.getById(id);
	}
	/*
	 * @PostMapping("/rentals")
	 *
	 * @Operation(summary = "Create a new rental", description =
	 * "Creates a new rental with the provided details.", responses = {
	 *
	 * @ApiResponse(responseCode = "201", description =
	 * "Rental created successfully", content = @Content(schema
	 * = @Schema(implementation = RentalDto.class))),
	 *
	 * @ApiResponse(responseCode = "400", description = "Invalid input data",
	 * content = @Content()),
	 *
	 * @ApiResponse(responseCode = "401", description = "User is unauthorized",
	 * content = @Content()) }, security = @SecurityRequirement(name =
	 * "bearerAuth")) public RentalDto create(Authentication auth, @RequestBody
	 * RentalCreUpDto dto) { return service.createRental(auth.getName(), dto); }
	 */

	// Multipart create version
	@PostMapping(value = "/rentals", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "Create a new rental with a picture", description = "Creates a new rental with the provided details and uploads a picture.", responses = {
			@ApiResponse(responseCode = "201", description = "Rental created successfully", content = @Content(schema = @Schema(implementation = RentalDto.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content()),
			@ApiResponse(responseCode = "401", description = "User is unauthorized", content = @Content()) }, security = @SecurityRequirement(name = "bearerAuth"))
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

		return service.createRental(auth.getName(), in);
	}

	@PutMapping("/rentals/{id}")
	@Operation(summary = "Update a rental", description = "Updates the details of an existing rental.", responses = {
			@ApiResponse(responseCode = "200", description = "Rental updated successfully", content = @Content(schema = @Schema(implementation = RentalDto.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content()),
			@ApiResponse(responseCode = "404", description = "Rental not found", content = @Content()),
			@ApiResponse(responseCode = "401", description = "User is unauthorized", content = @Content()) }, security = @SecurityRequirement(name = "bearerAuth"))
	public RentalDto update(@PathVariable Long id, @RequestBody RentalCreUpDto dto) {
		return service.updateRental(id, dto);
	}

	@PutMapping(value = "/rentals/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "Update a rental with a picture", description = "Updates the details of an existing rental and uploads a new picture if provided.", responses = {
			@ApiResponse(responseCode = "200", description = "Rental updated successfully", content = @Content(schema = @Schema(implementation = RentalDto.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content()),
			@ApiResponse(responseCode = "404", description = "Rental not found", content = @Content()),
			@ApiResponse(responseCode = "401", description = "User is unauthorized", content = @Content()) }, security = @SecurityRequirement(name = "bearerAuth"))
	public RentalDto updateMultipart(@PathVariable Long id,
			                         @RequestParam(value="name", required=false) String name,
			                         @RequestParam(value="surface", required=false) Integer surface,
			                         @RequestParam(value="price", required = false) Integer price,
			                         @RequestParam(value="picture", required= false) MultipartFile picture,
			                         @RequestParam(value= "description", required= false) String description) {

	    RentalCreUpDto in = new RentalCreUpDto();
	    in.setName(name);
	    in.setSurface(surface);
	    in.setPrice(price);
	    in.setDescription(description);

	    //if a new image is uploaded, store it and set the URL
	    if (picture != null && !picture.isEmpty()) {
	      String pictureUrl = fileStorage.storeAndGetPublicUrl(picture);
	      in.setPicture(pictureUrl);
	    } else {
	      in.setPicture(null); //service will keep the current picture if null
	    }

	    return service.updateRental(id, in);
	  }
}
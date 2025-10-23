package com.openclassrooms.Chatop.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.openclassrooms.Chatop.repository.RentalRepository;
import com.openclassrooms.Chatop.repository.UserRepository;
import com.openclassrooms.Chatop.service.api.RentalService;
import com.openclassrooms.Chatop.DTOs.*;
import com.openclassrooms.Chatop.model.Rental;
import com.openclassrooms.Chatop.model.UserN;
import com.openclassrooms.Chatop.exception.UserNotFoundException;
import com.openclassrooms.Chatop.exception.RentalNotFoundException;

@Service
public class RentalServiceImple implements RentalService {

	@Autowired
	private RentalRepository rentRepository;

	@Autowired
	private UserRepository userRepository;

	// Return list of the rentals
	public List<RentalDto> rentList() {
		List<RentalDto> list = rentRepository.findAll().stream().map(this::mapToDto).toList();
		return list;
	}

	public RentalDto getById(Long id) {
		Rental rental = rentRepository.findById(id).orElseThrow(() -> new RentalNotFoundException("Rental not found"));
		return mapToDto(rental);
	}

	@Transactional
	public RentalDto createRental(String email, RentalCreUpDto dto) {
		UserN owner = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found!"));

		Rental rental = new Rental();
		rental.setName(dto.getName());
		rental.setSurface(dto.getSurface());
		rental.setPrice(dto.getPrice());
		rental.setPicture(dto.getPicture());
		rental.setDescription(dto.getDescription());
		rental.setOwner(owner);

		rentRepository.save(rental);
		return mapToDto(rental);
	}

	@Transactional
	public RentalDto updateRental(Long id, RentalCreUpDto dto) {
		Rental rental = rentRepository.findById(id).orElseThrow(() -> new RentalNotFoundException("Rental not found! " + id));

		// Make sur the fields are not blank before setting
		if (dto.getName() != null)
			rental.setName(dto.getName());
		if (dto.getSurface() != null)
			rental.setSurface(dto.getSurface());
		if (dto.getPrice() != null)
			rental.setPrice(dto.getPrice());
		if (dto.getPicture() != null)
			rental.setPicture(dto.getPicture());
		if (dto.getDescription() != null)
			rental.setDescription(dto.getDescription());

		rentRepository.save(rental);
		return mapToDto(rental);
	}

	// Converting Rental to RentalDto
	private RentalDto mapToDto(Rental rental) {
		return new RentalDto(
				rental.getId(),
				rental.getName(),
				rental.getSurface(),
				rental.getPrice(),
				rental.getPicture(),
				rental.getDescription(),
				rental.getOwner().getId(),
				rental.getCreatedAt(),
				rental.getCreatedAt());
	}
}
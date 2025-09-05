package com.openclassrooms.Chatop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.Chatop.repository.RentalRepository;
import com.openclassrooms.Chatop.repository.UserRepository;
import com.openclassrooms.Chatop.DTOs.*;
import com.openclassrooms.Chatop.model.Rental;
import com.openclassrooms.Chatop.model.UserN;

@Service
public class RentalService {
	
	@Autowired
	private RentalRepository rentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	//Return list of the rentals
	public List<RentalDto> rentList(){
	  List<RentalDto> list = rentRepository.findAll()
			      .stream()
			      .map(this::mapToDto)
			      .toList();
	  return list;
	}
    
	@Transactional
	public RentalDto createRental(String email, RentalCreUpDto create) {
		UserN owner = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found!"));
		
		Rental rental = new Rental();
				rental.setName(create.getName());
				rental.setSurface(create.getSurface());
				rental.setPrice(create.getPrice());
				rental.setPicture(create.getPicture());
				rental.setDescription(create.getDescription());
				rental.setOwner(owner);
				
		rentRepository.save(rental);
		return mapToDto(rental);
	}
	
	@Transactional
	public RentalDto UpdateRental(Long id, String email, RentalCreUpDto update) {
		Rental rental = rentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Rental not found!" + id));
		
		
				rental.setName(update.getName());
				rental.setSurface(update.getSurface());
				rental.setPrice(update.getPrice());
				rental.setPicture(update.getPicture());
				rental.setDescription(update.getDescription());
				
				
		rentRepository.save(rental);
		return mapToDto(rental);
	}
	
	//Converting Rental to RentalDto
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

package com.openclassrooms.Chatop.service.api;

import java.util.List;

import com.openclassrooms.Chatop.DTOs.RentalCreUpDto;
import com.openclassrooms.Chatop.DTOs.RentalDto;

public interface RentalService {

	  List<RentalDto> rentList();
	  RentalDto getById(Long id);
	  RentalDto createRental(String ownerEmail, RentalCreUpDto in);
	  RentalDto updateRental(Long id, RentalCreUpDto in);
}

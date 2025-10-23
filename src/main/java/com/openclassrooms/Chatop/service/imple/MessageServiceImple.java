package com.openclassrooms.Chatop.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.openclassrooms.Chatop.DTOs.MessageDto;
import com.openclassrooms.Chatop.model.Message;
import com.openclassrooms.Chatop.model.Rental;
import com.openclassrooms.Chatop.model.UserN;
import com.openclassrooms.Chatop.repository.MessageRepository;
import com.openclassrooms.Chatop.repository.RentalRepository;
import com.openclassrooms.Chatop.repository.UserRepository;

public class MessageServiceImple {
	
	@Autowired
	private MessageRepository msgRepo;

	@Autowired
	private RentalRepository rentRepo;

	@Autowired
	private UserRepository userRepo;

	@Transactional
	public String messageCreate(String authorEmail, MessageDto dto) {
		UserN user = userRepo.findByEmail(authorEmail)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));

		Rental rental = rentRepo.findById(dto.getRentalId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental not found"));

		if (dto.getMessage() == null || dto.getMessage().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "message is required");
		}

		Message msg = new Message();
		msg.setMessage(dto.getMessage());
		msg.setAuthor(user);
		msg.setRental(rental);

		msgRepo.save(msg);
		return "Message sent successfully";
	}

}

package com.openclassrooms.Chatop.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice //this catch exceptions for all controllers
public class GlobalExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Map<String, String>> handle(ResponseStatusException ex) {
		return ResponseEntity
				.status(ex.getStatusCode())
				.body(Map.of("error", ex.getReason() == null ? "Error" : ex.getReason()));
	}

}

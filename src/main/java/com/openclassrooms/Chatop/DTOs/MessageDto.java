package com.openclassrooms.Chatop.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDto {

	@NotBlank
	private String message;

	@NotNull
	@JsonProperty("rental_id")
	private Long rentalId;

}

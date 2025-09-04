package com.openclassrooms.Chatop.DTOs;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeResponse {
	
	private Long id;
	private String name;
	private String email;
	
	@JsonProperty("created_at")
	private Instant createdAt;
	
	@JsonProperty("updated_at")
	private Instant updatedAt;

}

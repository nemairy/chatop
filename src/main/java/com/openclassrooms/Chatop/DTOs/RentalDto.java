package com.openclassrooms.Chatop.DTOs;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {
	
	private Long id;
	private String name;
	private Integer surface;
	private Integer price;
	private String picture;
	private String description;
	
	@JsonProperty("owner_id")
	private Long ownerId;
	
	@JsonProperty("created_at")
	private Instant createdAt;
	
	@JsonProperty("updated_at")
	private Instant updatedAt;

}

package com.openclassrooms.Chatop.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCreUpDto {

	private String name;
	private Integer surface;
	private Integer price;
	private String picture;
	private String description;
}

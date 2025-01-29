package com.demo.user.service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

	private String ratingId;
	private String userId;
	private String hotelId;
	private int Rating;
	private String feedback;
	
	private Hotel hotel;
}

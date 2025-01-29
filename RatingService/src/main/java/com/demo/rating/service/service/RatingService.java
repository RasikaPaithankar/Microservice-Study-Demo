package com.demo.rating.service.service;

import java.util.List;

import com.demo.rating.service.entity.Rating;

public interface RatingService {

	Rating getRating(String id);
	
	List<Rating> getAllRatings();
	
	Rating createRating(Rating r);
	
	List<Rating> getAllByUser(String userId);
	
	List<Rating> getAllByHotel(String hotelId);
	
}

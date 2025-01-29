package com.demo.rating.service.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.rating.service.entity.Rating;
import com.demo.rating.service.exceptions.RatingNotFoundException;
import com.demo.rating.service.repository.RatingRepository;
import com.demo.rating.service.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	private RatingRepository repository;

	@Override
	public Rating getRating(String id) {
		Rating rating = repository.findById(id).orElseThrow(() -> new RatingNotFoundException("Rating not found with given id."));
		return rating;
	}

	@Override
	public List<Rating> getAllRatings() {
		return repository.findAll();
	}

	@Override
	public Rating createRating(Rating r) {
		String id = UUID.randomUUID().toString();
		r.setRatingId(id);
		return repository.save(r);
	}

	@Override
	public List<Rating> getAllByUser(String userId) {
		List<Rating> byUserId = repository.findByUserId(userId);
		return byUserId;
	}

	@Override
	public List<Rating> getAllByHotel(String hotelId) {
		return repository.findByHotelId(hotelId);
	}

}

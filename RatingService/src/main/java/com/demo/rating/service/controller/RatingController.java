package com.demo.rating.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rating.service.entity.Rating;
import com.demo.rating.service.service.impl.RatingServiceImpl;


@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	RatingServiceImpl service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Rating> getRatingById(@RequestParam String id) {
		Rating rating = service.getRating(id);
		return ResponseEntity.status(HttpStatus.OK).body(rating);
	}
	
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRatings(){
		List<Rating> ratings = service.getAllRatings();
		return ResponseEntity.status(HttpStatus.OK).body(ratings);
	}
	
	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating r) {
		Rating rating = service.createRating(r);
		return ResponseEntity.status(HttpStatus.CREATED).body(rating);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {
		List<Rating> ratings = service.getAllByUser(userId);
		return ResponseEntity.status(HttpStatus.OK).body(ratings);
	}
	
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
		System.out.println("YTest");
		List<Rating> ratings = service.getAllByHotel(hotelId);
		return ResponseEntity.status(HttpStatus.OK).body(ratings);
	}
	
}

package com.demo.user.service.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.user.service.entity.Rating;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

	@GetMapping("ratings/user/{userId}")
	Rating[] getRating(@PathVariable String userId);
	
	@PostMapping("/ratings")
	Rating createRating(@RequestBody Rating r);
}

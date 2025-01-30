package com.demo.user.service.controller;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.user.service.entity.Rating;
import com.demo.user.service.entity.User;
import com.demo.user.service.services.UserService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;

    private static final String BACKEND_A = "backendA";
    private static final String RETRY = "retry";
    private static final String LIMITER = "limiter";
    
    org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User saveUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}
	
	int retryCount = 1;

	@GetMapping("/{id}")
//	@CircuitBreaker(name = BACKEND_A, fallbackMethod = "ratingHotelFallbackMethod")
//	@Retry(name =RETRY, fallbackMethod = "ratingHotelFallbackMethod")
	@RateLimiter(name = LIMITER, fallbackMethod = "ratingHotelFallbackMethod")
	public ResponseEntity<User> getUser(@PathVariable String id){
		logger.info("retry count is "+ retryCount);
		retryCount++;
		User user = userService.getUser(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
	
	@PostMapping(path = "/rating")
	public ResponseEntity<Rating> createRating(@RequestBody Rating r){
		Rating rating = userService.createRating(r);
		return ResponseEntity.status(HttpStatus.CREATED).body(rating);
	}
	
	
	public ResponseEntity<User> ratingHotelFallbackMethod(String userID, Exception e) {
		User user = User.builder().userId("123").
							name("dummy").email("DummyEmail@test.com").about("This is dummy user as some services are down")
							.build();
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
}

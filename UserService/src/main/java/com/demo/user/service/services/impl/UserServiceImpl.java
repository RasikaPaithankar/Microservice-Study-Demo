package com.demo.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.user.service.entity.Hotel;
import com.demo.user.service.entity.Rating;
import com.demo.user.service.entity.User;
import com.demo.user.service.exceptions.UserNotFoundException;
import com.demo.user.service.external.service.HotelService;
import com.demo.user.service.external.service.RatingService;
import com.demo.user.service.repository.UserRepository;
import com.demo.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private RatingService ratingService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);	

	@Override
	public User saveUser(User u) {
		String uid = UUID.randomUUID().toString();
		u.setUserId(uid);
		return userRepository.save(u);
	}

	@Override
	public List<User> getAllUser() {
		List<User> allUsers = userRepository.findAll();
		// ArrayList<Rating> ratings =
		// restTemplate.getForObject("http://localhost:8083/ratings", ArrayList.class);

		List<Rating> ratings = restTemplate.exchange("http://RATINGSERVICE/ratings", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Rating>>() {
				}).getBody();

		// Map userId to its ratings
		Map<String, List<Rating>> ratingsByUserId = ratings.stream().collect(Collectors.groupingBy(Rating::getUserId));

		// Assign ratings to the corresponding user
		allUsers.forEach(user -> user.setRatings(ratingsByUserId.get(user.getUserId())));

		logger.info("{}" + allUsers);

		return allUsers;
	}

	@Override
	public User getUser(String userID) {
// 		get user by user id
		User user = userRepository.findById(userID)
				.orElseThrow(() -> new UserNotFoundException("User with given id is not found."));
// 		get ratings by user from Rating Service
// 		http://localhost:8083/ratings/user/3bf338af-42c8-4c6a-a16b-e5dbabd18d17

//		ArrayList<Rating> forObject = restTemplate.getForObject("http://localhost:8083/ratings/user/"+user.getUserId(), ArrayList.class);

//		using restTemplate
//		Rating[] ratingsForUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/user/" + user.getUserId(),Rating[].class);

		Rating[] ratingsForUser = ratingService.getRating(user.getUserId());

		List<Rating> listOfuser = Arrays.stream(ratingsForUser).toList();

//		set hotel for user
		List<Rating> ratingList = listOfuser.stream().map(rating -> {

//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel = forEntity.getBody();
//			logger.info("Response code status {}", forEntity.getStatusCode());

//	 		using feign client
			Hotel hotel = hotelService.getHotel(rating.getHotelId());

			rating.setHotel(hotel);
			return rating;

		}).collect(Collectors.toList());

		logger.info("{}", ratingList);
		// set ratings for user
		user.setRatings(ratingList);
		return user;
	}

	@Override
	public Rating createRating(Rating r) {
		System.out.println("Create Rating");
		Rating rating = ratingService.createRating(r);
		return rating;
	}

}

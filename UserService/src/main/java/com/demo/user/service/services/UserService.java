package com.demo.user.service.services;

import java.util.List;

import com.demo.user.service.entity.Rating;
import com.demo.user.service.entity.User;

public interface UserService {

	User saveUser(User u);
	
	List<User> getAllUser();
	
	User getUser(String userID);
	
	Rating createRating(Rating r);
	
	//TODO: delete and update
}

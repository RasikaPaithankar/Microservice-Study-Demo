package com.demo.hotel.service.service;

import java.util.List;

import com.demo.hotel.service.entity.Hotel;

public interface HotelService {
	
	Hotel createHotel(Hotel h);
	
	List<Hotel> getAllHotels();
	
	Hotel getHotel(String id);
	
}

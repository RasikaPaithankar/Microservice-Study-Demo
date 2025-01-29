package com.demo.hotel.service.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.hotel.service.entity.Hotel;
import com.demo.hotel.service.exception.HotelNotFoundException;
import com.demo.hotel.service.repository.HotelRepository;
import com.demo.hotel.service.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository repository;
	
	@Override
	public Hotel createHotel(Hotel h) {
		String id = UUID.randomUUID().toString();
		h.setId(id);
		return repository.save(h);
	}

	@Override
	public List<Hotel> getAllHotels() {
		List<Hotel> all = repository.findAll();
		return all;
	}

	@Override
	public Hotel getHotel(String id) {
		Hotel hotel = repository.findById(id).orElseThrow(() -> new HotelNotFoundException("Hotel not found with given ID."));
		return hotel;
	}

}

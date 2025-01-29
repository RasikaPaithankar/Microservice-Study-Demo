package com.demo.hotel.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hotel.service.entity.Hotel;
import com.demo.hotel.service.service.impl.HotelServiceImpl;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelServiceImpl serviceImpl;

	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotels(){
		List<Hotel> allHotels = serviceImpl.getAllHotels();
		return ResponseEntity.ok(allHotels);
	}
	
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		Hotel newHotel = serviceImpl.createHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(newHotel);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotel(@PathVariable String id){
		System.out.println("Test");
		Hotel hotel = serviceImpl.getHotel(id);
		return ResponseEntity.status(HttpStatus.OK).body(hotel);
	}
	
	
}

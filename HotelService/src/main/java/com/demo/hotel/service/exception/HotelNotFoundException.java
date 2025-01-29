package com.demo.hotel.service.exception;

public class HotelNotFoundException extends RuntimeException{

	public HotelNotFoundException() {
		super("Hotel not found.");
	}
	
	public HotelNotFoundException(String m) {
		super(m);
	}
}

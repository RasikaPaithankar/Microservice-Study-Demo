package com.demo.hotel.service.exception;

public class StaffNotFoundException extends RuntimeException {

	public StaffNotFoundException() {
		super("Staff not found.");
	}
	
	public StaffNotFoundException(String m) {
		super(m);
	}
	
}

package com.demo.rating.service.exceptions;

public class RatingNotFoundException extends RuntimeException{

	public RatingNotFoundException() {
		super("Rating not found");
	}
	
	public RatingNotFoundException(String m) {
		super(m);
	}
}

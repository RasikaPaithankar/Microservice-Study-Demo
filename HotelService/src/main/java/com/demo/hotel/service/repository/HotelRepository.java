package com.demo.hotel.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.hotel.service.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {
	
}

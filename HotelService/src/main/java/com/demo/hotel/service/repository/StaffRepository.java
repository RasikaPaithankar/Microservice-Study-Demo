package com.demo.hotel.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.hotel.service.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

}

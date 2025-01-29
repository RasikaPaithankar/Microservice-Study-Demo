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

import com.demo.hotel.service.entity.Staff;
import com.demo.hotel.service.service.impl.SfattServiceImpl;

@RestController
@RequestMapping("/staffs")
public class StaffController {

	@Autowired
	SfattServiceImpl staffServiceImpl;
	
	@GetMapping
	public ResponseEntity<List<Staff>> getStaffs() {
		List<Staff> staffs = staffServiceImpl.getStaffs();
		return ResponseEntity.status(HttpStatus.OK).body(staffs);
	}
	
	@GetMapping("/{staffId}")
	public ResponseEntity<Staff> getStaff(@PathVariable String staffId){
		Staff staff = staffServiceImpl.getSfatt(staffId);
		return ResponseEntity.status(HttpStatus.OK).body(staff);
	}
	
	@PostMapping
	public ResponseEntity<Staff> createStaff(@RequestBody Staff staff){
		Staff newStaff = staffServiceImpl.createStaff(staff);
		return ResponseEntity.status(HttpStatus.CREATED).body(newStaff);
	}
	
}

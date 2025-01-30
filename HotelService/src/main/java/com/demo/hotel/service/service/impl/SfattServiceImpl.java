package com.demo.hotel.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.hotel.service.entity.Staff;
import com.demo.hotel.service.exception.StaffNotFoundException;
import com.demo.hotel.service.repository.StaffRepository;
import com.demo.hotel.service.service.SfattService;

@Service
public class SfattServiceImpl implements SfattService {

	@Autowired
	StaffRepository repository;
	
	@Override
	public List<Staff> getStaffs() {
		List<Staff> sfatts = repository.findAll();
		return sfatts;
	}

	@Override
	public Staff getSfatt(Integer staffId) {
		Staff staff = repository.findById(staffId).orElseThrow(() -> new StaffNotFoundException("Staff with given id is not found."));
		return staff;
	}

	@Override
	public Staff createStaff(Staff s) {
		Staff newStaff = repository.save(s);
		return newStaff;
	}

}

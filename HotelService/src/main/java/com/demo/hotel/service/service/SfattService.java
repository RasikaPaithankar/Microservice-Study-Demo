package com.demo.hotel.service.service;

import java.util.List;

import com.demo.hotel.service.entity.Staff;

public interface SfattService {

	List<Staff> getStaffs();
	
	Staff getSfatt(Integer staffId);
	
	Staff createStaff(Staff s);
	
}

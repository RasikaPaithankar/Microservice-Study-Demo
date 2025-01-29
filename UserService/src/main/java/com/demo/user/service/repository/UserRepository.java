package com.demo.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.user.service.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}

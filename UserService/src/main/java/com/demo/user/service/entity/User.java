package com.demo.user.service.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userId;
	
	@Column(name = "name" ,length = 20)
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "about" ,length = 200)
	private String about;
	
	@Transient
	private List<Rating> ratings = new ArrayList<>();
	
}

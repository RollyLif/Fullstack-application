package com.learning.Learning.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Users")
public class UserDTO {
	
	@Id
	@GeneratedValue
	@Column(name= "USER_ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="ADRESS")
	private String address;
	
	@Column(name="EMAIL")
	private String email;

	/*
	 * public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 * 
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 * 
	 * public String getAddress() { return address; }
	 * 
	 * public void setAddress(String address) { this.address = address; }
	 * 
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 */
	
	
}

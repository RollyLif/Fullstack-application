package com.learning.Learning.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
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
	
}

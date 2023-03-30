package com.learning.Learning.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

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
	
	@NotBlank
	@Length(max=50)
	@Column(name="NAME")
	private String name;
	
	@NotBlank
	@Length(max=150)
	@Column(name="ADRESS")
	private String address;
	
	@Email
	@NotBlank
	@Length(max =80)
	@Column(name="EMAIL")
	private String email;
	
}

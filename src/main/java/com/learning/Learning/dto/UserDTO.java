package com.learning.Learning.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotNull(message = "name is mandatory")
	@Column(name="NAME")
	private String name;
	
	@NotNull(message = "adress is mandatory")
	@Column(name="ADRESS")
	private String address;
	
	@Email
	@NotBlank(message = "email is mandatory")
	@Column(name="EMAIL")
	private String email;
	
}

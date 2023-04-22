package com.learning.Learning.dto;


import javax.validation.constraints.NotEmpty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private Long id;
	
	@Column(name = "username")
	@NotEmpty
	private String username;
	
	@Column(name = "password")
	@NotEmpty
	private String password;
	
	@Column(name = "enabled")
	private boolean isEnabled;
	
	@Column(name = "role")
	private String role;

}

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
@Data
@Table(name="users")
public class UserInfo {
	
	@Id
	@GeneratedValue
	@Column(name = "userid")
	private int id;
	
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

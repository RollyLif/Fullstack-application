package com.learning.Learning.Exception;

import com.learning.Learning.dto.UserDTO;

import lombok.Data;

@Data
public class CustomErrorType extends UserDTO{
	
	private String errorMessage;
	
	public CustomErrorType(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

}

package com.casalibertad.user_records.DTOS;

import lombok.Data;

@Data
public class ErrorDTO {
	
	private String message;
	
	public ErrorDTO(String message) {
		this.message = message;
	}
}

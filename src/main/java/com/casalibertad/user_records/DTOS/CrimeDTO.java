package com.casalibertad.user_records.DTOS;

import lombok.Data;

@Data
public class CrimeDTO {
	private int uniqid;
	private String external_uniqid;
	private String crime;
}

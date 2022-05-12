package com.casalibertad.user_records.DTOS;

import java.util.List;

import lombok.Data;

@Data
public class CrimesDTO {
	private List<CrimeDTO> crimes;
}

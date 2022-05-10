package com.casalibertad.user_records.DTOS;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CrimeDTO extends NewCrime{
	private int uniqid;
}

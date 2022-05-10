package com.casalibertad.user_records.DTOS;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NewUserRecordsDTO extends  UpdatedUserRecordsDTO{
	private int user_uniqid;
}

package com.casalibertad.user_records.DTOS;

import lombok.Data;

@Data
public class NewUserRecordsDTO extends  UpdatedUserRecordsDTO{
	private int user_uniqid;
}

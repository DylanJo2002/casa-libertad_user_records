package com.casalibertad.user_records.DTOS;

import lombok.Data;
import lombok.EqualsAndHashCode;


public class NewUserRecordsDTO extends  UpdatedUserRecordsDTO{
	private int user_uniqid;

	public int getUser_uniqid() {
		return user_uniqid;
	}

	public void setUser_uniqid(int user_uniqid) {
		this.user_uniqid = user_uniqid;
	}
	
}

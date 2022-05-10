package com.casalibertad.user_records.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "casa_libertad_log")
@Data
public class LogEntity {
	@Id
	private String uniqid;
	@Column(name = "message")
	private String message;
	
	@Column(name = "error_date", insertable = false)
	private String errorDate;
	
}

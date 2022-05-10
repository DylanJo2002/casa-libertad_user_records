package com.casalibertad.user_records.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "casa_libertad_user_records")
public class UserRecordsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uniqid;
	
	@ManyToOne
	@JoinColumn(name = "user_uniqid")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name = "court_record_uniqid")
	private CourtRecordEntity record;
	
	@ManyToOne
	@JoinColumn(name = "selection")
	private SelectionRecordEntity selection;
}

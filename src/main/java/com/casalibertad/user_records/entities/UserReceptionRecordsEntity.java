package com.casalibertad.user_records.entities;

import java.util.Date;

import javax.persistence.Column;
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
@Table(name = "casa_libertad_user_reception_records")
public class UserReceptionRecordsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uniqid;
	
	@ManyToOne
	@JoinColumn(name = "user_uniqid")
	private UserEntity user;
	
	@Column(name = "freedom_date")
	private Date freedomDate;

	@Column(name = "months_sentence")
	private int monthsSentence;
	
	@ManyToOne
	@JoinColumn(name = "prison_establishment_id")
	private PrisonEstablishmentEntity prisonEstablishment;
	
	@Column(name = "another_prison_establishment")
	private String otherPrisonEstablishment;
	
	@ManyToOne
	@JoinColumn(name = "legal_status_id")
	private LegalStatusEntity legalStatus;
	
	@Column(name = "apprehended_teenager")
	private int apprehendedTeenager;
	
	@Column(name = "apprehended_adult")
	private int apprehendedAdult;
	
	@Column(name = "actual_process")
	private Character actualProcess;
	
}

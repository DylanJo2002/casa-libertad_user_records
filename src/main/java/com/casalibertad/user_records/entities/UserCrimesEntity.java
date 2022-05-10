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
@Table(name = "casa_libertad_user_crimes")
public class UserCrimesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uniqid;
	
	@ManyToOne
	@JoinColumn(name = "user_uniqid")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name = "crime_uniqid")
	private CrimeEntity crime;
}

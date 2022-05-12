package com.casalibertad.user_records.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "casa_libertad_crimes")
public class CrimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uniqid;
	@Column(name = "crime")
	private String crime;
}

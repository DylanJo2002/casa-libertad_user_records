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
	@JoinColumn(name = "record_policia_id")
	private SelectionRecordEntity recordPolicia;
	
	@ManyToOne
	@JoinColumn(name = "record_codigo_id")
	private SelectionRecordEntity recordCodigo;
	
	@ManyToOne
	@JoinColumn(name = "record_sisipec_id")
	private SelectionRecordEntity recordSisipec;
	
	@ManyToOne
	@JoinColumn(name = "record_personeria_id")
	private SelectionRecordEntity recordPersoneria;
	
	@ManyToOne
	@JoinColumn(name = "record_procuraduria_id")
	private SelectionRecordEntity recordProcuraduria;
	
	@ManyToOne
	@JoinColumn(name = "record_contraloria_id")
	private SelectionRecordEntity recordContraloria;
	
	@ManyToOne
	@JoinColumn(name = "record_rama_id")
	private SelectionRecordEntity recordRama;
}

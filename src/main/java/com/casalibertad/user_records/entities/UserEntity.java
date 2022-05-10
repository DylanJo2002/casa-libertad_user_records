package com.casalibertad.user_records.entities;

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
@Table(name = "casa_libertad_users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uniqid;
	
	@ManyToOne
	@JoinColumn(name = "document_type")
	private DocumentTypeEntity documentType;
	
	@Column(name = "document_number")
	private String documentNumber;
	
	@Column(name = "names_user")
	private String namesUser;
	
	@Column(name = "first_last_name")
	private String firstLastName;
	
	@Column(name = "secound_last_name")
	private String secoundLastName;
	
	@Column(name = "phone_1")
	private long phone1;
	
	@Column(name = "phone_2", nullable = true)
	private Long phone2;
}

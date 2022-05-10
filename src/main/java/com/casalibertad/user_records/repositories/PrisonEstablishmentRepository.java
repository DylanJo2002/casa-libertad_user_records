package com.casalibertad.user_records.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casalibertad.user_records.entities.PrisonEstablishmentEntity;

@Repository
public interface PrisonEstablishmentRepository extends JpaRepository<PrisonEstablishmentEntity, Integer> {
	public PrisonEstablishmentEntity findByUniqid(int uniqid);
}

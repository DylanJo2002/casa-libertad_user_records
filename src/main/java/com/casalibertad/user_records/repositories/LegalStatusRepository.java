package com.casalibertad.user_records.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casalibertad.user_records.entities.LegalStatusEntity;

@Repository
public interface LegalStatusRepository extends JpaRepository<LegalStatusEntity, Integer> {
	public LegalStatusEntity findByUniqid(int uniqid);
}

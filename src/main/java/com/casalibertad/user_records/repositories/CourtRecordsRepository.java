package com.casalibertad.user_records.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casalibertad.user_records.entities.CourtRecordEntity;

@Repository
public interface CourtRecordsRepository extends JpaRepository<CourtRecordEntity, Integer> {
	public CourtRecordEntity findByUniqid(int uniqid);
}

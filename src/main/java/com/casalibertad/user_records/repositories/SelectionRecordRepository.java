package com.casalibertad.user_records.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casalibertad.user_records.entities.SelectionRecordEntity;

@Repository
public interface SelectionRecordRepository extends JpaRepository<SelectionRecordEntity, Integer> {
	public SelectionRecordEntity findByUniqid(int uniqid);
}

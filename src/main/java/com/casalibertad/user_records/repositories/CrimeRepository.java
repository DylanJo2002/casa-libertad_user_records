package com.casalibertad.user_records.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casalibertad.user_records.entities.CrimeEntity;

@Repository
public interface CrimeRepository extends JpaRepository<CrimeEntity, Integer> {
	public CrimeEntity findByUniqid(int uniqid);
}

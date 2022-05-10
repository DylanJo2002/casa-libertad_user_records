package com.casalibertad.user_records.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casalibertad.user_records.entities.LogEntity;


@Repository
public interface LogRepository extends JpaRepository<LogEntity, String>{
	

}

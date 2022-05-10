package com.casalibertad.user_records.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casalibertad.user_records.entities.UserEntity;
import com.casalibertad.user_records.entities.UserRecordsEntity;

@Repository
public interface UserRecordRepository extends JpaRepository<UserRecordsEntity, Integer> {
	public List<UserRecordsEntity> findByUser(UserEntity user);
}

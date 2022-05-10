package com.casalibertad.user_records.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casalibertad.user_records.entities.UserEntity;
import com.casalibertad.user_records.entities.UserReceptionRecordsEntity;

@Repository
public interface UserReceptionRecordsRepository extends JpaRepository<UserReceptionRecordsEntity, Integer> {
	public UserReceptionRecordsEntity findByUser(UserEntity user);
}

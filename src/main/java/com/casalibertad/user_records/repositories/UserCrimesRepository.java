package com.casalibertad.user_records.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casalibertad.user_records.entities.UserCrimesEntity;
import com.casalibertad.user_records.entities.UserEntity;

public interface UserCrimesRepository extends JpaRepository<UserCrimesEntity, Integer> {
	public List<UserCrimesEntity> findByUser(UserEntity user);
}

package com.casalibertad.user_records.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casalibertad.user_records.entities.UserCrimesEntity;
import com.casalibertad.user_records.entities.UserEntity;

@Repository
public interface UserCrimesRepository extends JpaRepository<UserCrimesEntity, Integer> {
	public List<UserCrimesEntity> findByUser(UserEntity user);
}

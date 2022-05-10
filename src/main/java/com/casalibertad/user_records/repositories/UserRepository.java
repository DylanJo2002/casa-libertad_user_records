package com.casalibertad.user_records.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casalibertad.user_records.entities.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	public UserEntity findByUniqid(int uniqid);
}

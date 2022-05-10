package com.casalibertad.user_records.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.casalibertad.user_records.entities.UserCrimesEntity;
import com.casalibertad.user_records.entities.UserEntity;

@Repository
public interface UserCrimesRepository extends JpaRepository<UserCrimesEntity, Integer> {
	public List<UserCrimesEntity> findByUser(UserEntity user);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM casa_libertad_user_crimes WHERE user_uniqid = ?1",
			nativeQuery = true)
	public void removeUserCrimes(int userId);
}

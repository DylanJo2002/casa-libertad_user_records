package com.casalibertad.user_records.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.user_records.entities.UserCrimesEntity;
import com.casalibertad.user_records.entities.UserEntity;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.repositories.UserCrimesRepository;

@Service
public class UserCrimesService {
	@Autowired
	private UserService userService;
	@Autowired
	private UserCrimesRepository crimesRepository;
	
	public List<UserCrimesEntity> getUserCrimesEntities(int userId) throws NotFoundException{
		UserEntity userEntity = userService.getUserEntity(userId);
		return crimesRepository.findByUser(userEntity);
	}
}

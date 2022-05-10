package com.casalibertad.user_records.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.user_records.entities.UserEntity;
import com.casalibertad.user_records.entities.UserRecordsEntity;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.repositories.UserRecordRepository;

@Service
public class UserRecordService {
	
	@Autowired
	private UserService userService;
	private UserRecordRepository recordRepository;
	
	public List<UserRecordsEntity> getUserRecordEntity(int userId) throws NotFoundException {
		UserEntity userEntity = userService.getUserEntity(userId);
		return recordRepository.findByUser(userEntity);		
	}

}

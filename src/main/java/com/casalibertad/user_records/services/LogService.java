package com.casalibertad.user_records.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.user_records.entities.LogEntity;
import com.casalibertad.user_records.repositories.LogRepository;




@Service
public class LogService {

	@Autowired
	private LogRepository logRepository;
	
	public void createLog(String uniqid, String message) {
		LogEntity logEntity = new LogEntity();
		logEntity.setUniqid(uniqid);
		logEntity.setMessage(message);
		logRepository.save(logEntity);
	}
}

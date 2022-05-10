package com.casalibertad.user_records.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.user_records.entities.CrimeEntity;
import com.casalibertad.user_records.enums.ErrorMessageEnum;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.loggin.ExceptionLoggin;
import com.casalibertad.user_records.repositories.CrimeRepository;


@Service
public class CrimeService {
	
	@Autowired
	private CrimeRepository crimeRepository;
	@Autowired
	private ExceptionLoggin exceptionLoggin;
	
	public CrimeEntity getCrimeEntity(int uniqid) throws NotFoundException {
		
		CrimeEntity crimeEntity = crimeRepository.findByUniqid(uniqid);		
		if(crimeEntity == null) {
			String cause = String.format("Does not exist a Crime with id %d", uniqid);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			
			throw new NotFoundException(message);
		}
			
		return crimeEntity;
	}
	
}

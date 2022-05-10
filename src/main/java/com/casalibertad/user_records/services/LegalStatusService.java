package com.casalibertad.user_records.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.user_records.entities.LegalStatusEntity;
import com.casalibertad.user_records.enums.ErrorMessageEnum;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.loggin.ExceptionLoggin;
import com.casalibertad.user_records.repositories.LegalStatusRepository;

@Service
public class LegalStatusService {
	@Autowired
	private LegalStatusRepository legalStatusRepository;
	@Autowired
	private ExceptionLoggin exceptionLoggin;
	
	public LegalStatusEntity getLegalStatusEntity(int uniqid) throws NotFoundException{
		LegalStatusEntity legalStatusEntity = legalStatusRepository.findByUniqid(uniqid);
		
		if(legalStatusEntity == null) {
			String cause = String.format("Does not exist a Legal status with id %d", uniqid);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			throw new NotFoundException(message);
		}
		
		return legalStatusEntity;
	}
}

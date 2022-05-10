package com.casalibertad.user_records.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.user_records.entities.PrisonEstablishmentEntity;
import com.casalibertad.user_records.enums.ErrorMessageEnum;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.loggin.ExceptionLoggin;
import com.casalibertad.user_records.repositories.PrisonEstablishmentRepository;

@Service
public class PrisonEstablishmentService {
	@Autowired
	private PrisonEstablishmentRepository prisonEstablishmentRepository;
	@Autowired
	private ExceptionLoggin exceptionLoggin;
	
	public PrisonEstablishmentEntity getPrisonEstablishmentEntity(int uniqid) throws NotFoundException{
		PrisonEstablishmentEntity prisonEstablishmentEntity = prisonEstablishmentRepository.findByUniqid(uniqid);
		
		if(prisonEstablishmentEntity == null) {
			String cause = String.format("Does not exist a Prison establishment with id %d", uniqid);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			throw new NotFoundException(message);
		}
		
		return prisonEstablishmentEntity;
	}
}

package com.casalibertad.user_records.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.user_records.entities.CourtRecordEntity;
import com.casalibertad.user_records.enums.ErrorMessageEnum;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.loggin.ExceptionLoggin;
import com.casalibertad.user_records.repositories.CourtRecordsRepository;

@Service
public class CourtRecordService {
	@Autowired
	private CourtRecordsRepository courtRecordsRepository;
	@Autowired
	private ExceptionLoggin exceptionLoggin;
	
	public CourtRecordEntity getCourtRecordEntity(int uniqid) throws NotFoundException{
		CourtRecordEntity courtREntity = courtRecordsRepository.findByUniqid(uniqid);
		
		if(courtREntity == null) {
			String cause = String.format("Does not exist a Court Record with id %d", uniqid);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			
			throw new NotFoundException(message);
		}
		
		return courtREntity;
	}
}

package com.casalibertad.user_records.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.user_records.entities.SelectionRecordEntity;
import com.casalibertad.user_records.enums.ErrorMessageEnum;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.loggin.ExceptionLoggin;
import com.casalibertad.user_records.repositories.SelectionRecordRepository;

@Service
public class SelectionRecordService {
	@Autowired
	private SelectionRecordRepository selectionRecordRepository;
	@Autowired
	private ExceptionLoggin exceptionLoggin;
	
	public SelectionRecordEntity getSelectionRecordEntity(int uniqid) throws NotFoundException{
		SelectionRecordEntity selectionRecordEntity = selectionRecordRepository.findByUniqid(uniqid);
		
		if(selectionRecordEntity == null) {
			String cause = String.format("Does not exist a Selection Record with id %d", uniqid);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			
			throw new NotFoundException(message);
		}
		
		return selectionRecordEntity;
	}
}

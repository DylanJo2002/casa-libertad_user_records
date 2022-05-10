package com.casalibertad.user_records.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.user_records.DTOS.NewCourtRecordDTO;
import com.casalibertad.user_records.DTOS.NewUserRecordsDTO;
import com.casalibertad.user_records.DTOS.UserRecordsDTO;
import com.casalibertad.user_records.entities.UserEntity;
import com.casalibertad.user_records.entities.UserRecordsEntity;
import com.casalibertad.user_records.enums.ErrorMessageEnum;
import com.casalibertad.user_records.exceptions.ConflictException;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.loggin.ExceptionLoggin;
import com.casalibertad.user_records.repositories.UserRecordRepository;

@Service
public class UserRecordService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRecordRepository recordRepository;
	@Autowired
	private ExceptionLoggin exceptionLoggin;
	@Autowired
	private SelectionRecordService selectionService;
	
	public UserRecordsEntity getUserRecordEntity(int userId) throws NotFoundException {
		UserEntity userEntity = userService.getUserEntity(userId);
		return recordRepository.findByUser(userEntity);		
	}
	
	public UserRecordsEntity createUserRecordEntity(int userId, NewCourtRecordDTO courtRecord) 
		throws NotFoundException, ConflictException {
		UserEntity userEntity = userService.getUserEntity(userId);
		UserRecordsEntity userRecordsEntity = recordRepository.findByUser(userEntity);
		
		if(userRecordsEntity != null) {
			String cause = String.format("There are already a user record for user with id %d", userId);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.ConflictException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			throw new ConflictException(message);
		}
		
		userRecordsEntity = new UserRecordsEntity();
		
		userRecordsEntity.setUser(userEntity);
		userRecordsEntity.setRecordPolicia(selectionService
				.getSelectionRecordEntity(courtRecord.getRecord_policia_id()));
		
		userRecordsEntity.setRecordCodigo(selectionService
				.getSelectionRecordEntity(courtRecord.getRecord_codigo_id()));
		
		userRecordsEntity.setRecordSisipec(selectionService
				.getSelectionRecordEntity(courtRecord.getRecord_sisipec_id()));
		
		userRecordsEntity.setRecordPersoneria(selectionService
				.getSelectionRecordEntity(courtRecord.getRecord_personeria_id()));
		
		userRecordsEntity.setRecordProcuraduria(selectionService
				.getSelectionRecordEntity(courtRecord.getRecord_procuraduria_id()));
		
		userRecordsEntity.setRecordContraloria(selectionService
				.getSelectionRecordEntity(courtRecord.getRecord_contraloria_id()));
		
		userRecordsEntity.setRecordRama(selectionService
				.getSelectionRecordEntity(courtRecord.getRecord_rama_id()));
		
		return recordRepository.save(userRecordsEntity);
	}
	

}

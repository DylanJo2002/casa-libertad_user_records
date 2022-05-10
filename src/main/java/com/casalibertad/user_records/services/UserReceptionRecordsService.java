package com.casalibertad.user_records.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.user_records.DTOS.CrimeDTO;
import com.casalibertad.user_records.DTOS.NewCourtRecordDTO;
import com.casalibertad.user_records.DTOS.NewCrime;
import com.casalibertad.user_records.DTOS.NewUserRecordsDTO;
import com.casalibertad.user_records.entities.LegalStatusEntity;
import com.casalibertad.user_records.entities.PrisonEstablishmentEntity;
import com.casalibertad.user_records.entities.SelectionRecordEntity;
import com.casalibertad.user_records.entities.UserCrimesEntity;
import com.casalibertad.user_records.entities.UserRecordsEntity;
import com.casalibertad.user_records.enums.ErrorMessageEnum;
import com.casalibertad.user_records.exceptions.ConflictException;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.loggin.ExceptionLoggin;

@Service
public class UserReceptionRecordsService {
	@Autowired
	private UserReceptionRecordsService userReceptionRecordsService;
	@Autowired
	private ExceptionLoggin exceptionLoggin;
	@Autowired
	private SelectionRecordService selectionRecordService;
	@Autowired
	private UserRecordService userRecordService;
	@Autowired
	private UserCrimesService userCrimesService;
	@Autowired
	private LegalStatusService legalStatusService;
	@Autowired
	private PrisonEstablishmentService prisoEstablishmentService;
	
	
	public boolean isValidNewCourtRecordDTO(int userId, NewCourtRecordDTO courtRecordDTO) throws NotFoundException {
		boolean isValid = true;

		/*Valide if there is a court user record*/
		UserRecordsEntity userRecordsEntity = userRecordService.getUserRecordEntity(userId);
		
		if(userRecordsEntity != null) {
			String cause = String.format("Does not exist a User Court Record for user whit id %d", userId);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			
			throw new NotFoundException(message);
		}
		
		/*Valide if court record DTO is valid*/

		boolean record_policia_id = isValidSelectRecord(courtRecordDTO.getRecord_policia_id());
		boolean record_codigo_id = isValidSelectRecord(courtRecordDTO.getRecord_codigo_id());
		boolean record_sisipec_id = isValidSelectRecord(courtRecordDTO.getRecord_sisipec_id());
		boolean record_personeria_id = isValidSelectRecord(courtRecordDTO.getRecord_personeria_id());
		boolean record_procuraduria_id = isValidSelectRecord(courtRecordDTO.getRecord_procuraduria_id());
		boolean record_contraloria_id = isValidSelectRecord(courtRecordDTO.getRecord_contraloria_id());
		boolean record_rama_id = isValidSelectRecord(courtRecordDTO.getRecord_rama_id());

		isValid = record_policia_id && record_codigo_id && record_sisipec_id
				&& record_personeria_id && record_procuraduria_id && record_contraloria_id
				&& record_rama_id;
				
		return isValid;
	}
	
	public boolean isValidSelectRecord(int uniqid) throws NotFoundException {
		SelectionRecordEntity selectionRecordEntity =  selectionRecordService.getSelectionRecordEntity(uniqid);
		return selectionRecordEntity != null;
	}


	public boolean isValidNewUserCrimesDTO(int userId) throws NotFoundException, ConflictException {
		
		boolean isValid = true;
		
		List<UserCrimesEntity> userCrimes = userCrimesService.getUserCrimesEntities(userId);
		
		if(userCrimes.size() > 0) {
			String cause = String.format("There are already user crimes for user id %d", userId);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.ConflictException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			throw new ConflictException(message);
		}
		
		return isValid;
	}

	public boolean isValidNewUserReceptionRecord(NewUserRecordsDTO newUserRecordsDTO) 
			throws NotFoundException, ConflictException {
		boolean isValid = true;
		
		boolean isValidCourtRecord = isValidNewCourtRecordDTO(newUserRecordsDTO.getUser_uniqid(),newUserRecordsDTO.getCourt_records_id());
		boolean isValidCrimes = isValidNewUserCrimesDTO(newUserRecordsDTO.getUser_uniqid());
		LegalStatusEntity legalStatusEntity = legalStatusService.getLegalStatusEntity(newUserRecordsDTO.getLegal_status_id());
		PrisonEstablishmentEntity prisEntity = prisoEstablishmentService.getPrisonEstablishmentEntity(newUserRecordsDTO.getPrison_establishment_id());
	
		if(legalStatusEntity == null || prisEntity == null) {
			isValid = false;
		}
		
		return isValid;
	}
}

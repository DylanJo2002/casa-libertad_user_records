package com.casalibertad.user_records.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casalibertad.user_records.DTOS.NewCourtRecordDTO;
import com.casalibertad.user_records.entities.SelectionRecordEntity;
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
	
//	public CourtRecordEntity getCourtRecordEntity(int uniqid) throws NotFoundException{
//		CourtRecordEntity courtREntity = courtRecordsRepository.findByUniqid(uniqid);
//		
//		if(courtREntity == null) {
//			String cause = String.format("Does not exist a Court Record with id %d", uniqid);
//			String id = exceptionLoggin.getUUID();
//			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause
//					,this.getClass().toString());
//			exceptionLoggin.saveLog(message, id);
//			
//			throw new NotFoundException(message);
//		}
//		
//		return courtREntity;
//	}
	
	public boolean isValidNewCourtRecordDTO(NewCourtRecordDTO courtRecordDTO) throws NotFoundException {
		boolean isValid = true;
		
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
		if(uniqid < 0) {
			return false;
		}
		SelectionRecordEntity selectionRecordEntity =  selectionRecordService.getSelectionRecordEntity(uniqid);
		return selectionRecordEntity != null;
	}
}

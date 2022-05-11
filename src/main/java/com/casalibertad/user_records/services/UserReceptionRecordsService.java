package com.casalibertad.user_records.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.user_records.DTOS.LegalStatusDTO;
import com.casalibertad.user_records.DTOS.NewCourtRecordDTO;
import com.casalibertad.user_records.DTOS.NewCrime;
import com.casalibertad.user_records.DTOS.NewUserRecordsDTO;
import com.casalibertad.user_records.DTOS.PrisonEstablishmentDTO;
import com.casalibertad.user_records.DTOS.UpdatedUserRecordsDTO;
import com.casalibertad.user_records.DTOS.UserRecordsDTO;
import com.casalibertad.user_records.entities.LegalStatusEntity;
import com.casalibertad.user_records.entities.PrisonEstablishmentEntity;
import com.casalibertad.user_records.entities.SelectionRecordEntity;
import com.casalibertad.user_records.entities.UserCrimesEntity;
import com.casalibertad.user_records.entities.UserEntity;
import com.casalibertad.user_records.entities.UserReceptionRecordsEntity;
import com.casalibertad.user_records.entities.UserRecordsEntity;
import com.casalibertad.user_records.enums.ErrorMessageEnum;
import com.casalibertad.user_records.exceptions.ConflictException;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.loggin.ExceptionLoggin;
import com.casalibertad.user_records.repositories.UserReceptionRecordsRepository;

@Service
public class UserReceptionRecordsService {
	@Autowired
	private UserReceptionRecordsRepository userReceptionRecordsRepository;
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
	@Autowired
	private UserService userService;
	@Autowired
	private CrimeService crimeService;

	public boolean isValidNewCourtRecordDTO(int userId, NewCourtRecordDTO courtRecordDTO) throws NotFoundException, ConflictException {
		boolean isValid = true;

		/*Valide if there is a court user record*/
		UserRecordsEntity userRecordsEntity = userRecordService.getUserRecordEntity(userId);
		
		if(userRecordsEntity != null) {
			String cause = String.format("There is already an User Court Record for user with id %d", userId);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.ConflictException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			
			throw new ConflictException(message);
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
		
		UserEntity userEntity = userService.getUserEntity(newUserRecordsDTO.getUser_uniqid());
		UserReceptionRecordsEntity userReceptionRecordsEntity = userReceptionRecordsRepository.findByUser(userEntity);

		if(userReceptionRecordsEntity != null) {
			String cause = String.format("There is already a User Reception Record for user whit id %d", newUserRecordsDTO.getUser_uniqid());
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.ConflictException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			
			throw new ConflictException(message);
		}
		
		if(newUserRecordsDTO.getMonths_sentence() < 0) {
			String cause = String.format("Months of sentence could not be %d", newUserRecordsDTO.getMonths_sentence());
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.ConflictException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			
			throw new ConflictException(message);
		}
		
		boolean isValidCourtRecord = isValidNewCourtRecordDTO(newUserRecordsDTO.getUser_uniqid(),newUserRecordsDTO.getCourt_records_id());
		boolean isValidCrimes = isValidNewUserCrimesDTO(newUserRecordsDTO.getUser_uniqid());
		LegalStatusEntity legalStatusEntity = legalStatusService.getLegalStatusEntity(newUserRecordsDTO.getLegal_status_id());
		PrisonEstablishmentEntity prisEntity = prisoEstablishmentService.getPrisonEstablishmentEntity(newUserRecordsDTO.getPrison_establishment_id());
		
		if(legalStatusEntity == null || prisEntity == null) {
			isValid = false;
		}
		
		return isValid;
	}

	public UserReceptionRecordsEntity createUserRecords(NewUserRecordsDTO userRecordsDTO) throws NotFoundException, ConflictException, ParseException {
		
		UserReceptionRecordsEntity userReceptionRecordsEntity = null;
		
		if(isValidNewUserReceptionRecord(userRecordsDTO)) {
			
			userReceptionRecordsEntity = new UserReceptionRecordsEntity();
			
			UserEntity userEntity = userService.getUserEntity(userRecordsDTO.getUser_uniqid());
			LegalStatusEntity legalStatusEntity = legalStatusService.getLegalStatusEntity(userRecordsDTO.getLegal_status_id());
			PrisonEstablishmentEntity prisEntity = prisoEstablishmentService.getPrisonEstablishmentEntity(userRecordsDTO.getPrison_establishment_id());
			
			userReceptionRecordsEntity.setUser(userEntity);
			userReceptionRecordsEntity.setFreedomDate(DateFormat.getDateInstance().parse((userRecordsDTO.getFreedom_date())));
			userReceptionRecordsEntity.setMonthsSentence(userRecordsDTO.getMonths_sentence());
			userReceptionRecordsEntity.setPrisonEstablishment(prisEntity);
			userReceptionRecordsEntity.setOtherPrisonEstablishment(userRecordsDTO.getAnother_prison_establishment());
			userReceptionRecordsEntity.setLegalStatus(legalStatusEntity);
			userReceptionRecordsEntity.setApprehendedTeenager(userRecordsDTO.getApprehended_teenager());
			userReceptionRecordsEntity.setApprehendedAdult(userRecordsDTO.getApprehended_adult());
			userReceptionRecordsEntity.setActualProcess(userRecordsDTO.getActual_process());
			
			userReceptionRecordsRepository.save(userReceptionRecordsEntity);
			
			createUserCrimesEntities(userEntity.getUniqid(), userRecordsDTO.getCrimes());
			userRecordService.createUserRecordEntity(userEntity.getUniqid(), userRecordsDTO.getCourt_records_id());
		}
		
		return userReceptionRecordsEntity;
	}

	public void valideCrimeExistence(List<NewCrime> crimes) {
		crimeService.valideCrimeExistence(crimes);
	}

	public List<UserCrimesEntity> createUserCrimesEntities(int userId, List<NewCrime> newCrimeDTO)
			throws NotFoundException{
		List<UserCrimesEntity> userCrimesEntities = null;

		userCrimesService.removeUserCrimesEntities(userId);

		if(newCrimeDTO != null) {
			valideCrimeExistence(newCrimeDTO);
			userCrimesEntities =  userCrimesService.createUserCrimesEntities(userId, newCrimeDTO);
		}
		return userCrimesEntities;

	}

	public UserReceptionRecordsEntity updateUserReceptionRecords(int userId, UpdatedUserRecordsDTO updateUserRecordsDTO)
			throws NotFoundException, ConflictException, ParseException {
		
		UserEntity userEntity = userService.getUserEntity(userId);

		UserReceptionRecordsEntity userReceptionRecordsEntity = userReceptionRecordsRepository.findByUser(userEntity);

		if(userReceptionRecordsEntity == null) {
			String cause = String.format("There is not a User Reception Record for user whit id %d", userId);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			
			throw new NotFoundException(message);
		}
		
		if(isValidUpdatedUserReceptionRecord(userId, updateUserRecordsDTO)) {
			
			LegalStatusEntity legalStatusEntity = legalStatusService.getLegalStatusEntity(updateUserRecordsDTO.getLegal_status_id());
			PrisonEstablishmentEntity prisEntity = prisoEstablishmentService.getPrisonEstablishmentEntity(updateUserRecordsDTO.getPrison_establishment_id());
			
			userReceptionRecordsEntity.setUser(userEntity);
			userReceptionRecordsEntity.setFreedomDate(DateFormat.getDateInstance().parse((updateUserRecordsDTO.getFreedom_date())));
			userReceptionRecordsEntity.setMonthsSentence(updateUserRecordsDTO.getMonths_sentence());
			userReceptionRecordsEntity.setPrisonEstablishment(prisEntity);
			userReceptionRecordsEntity.setOtherPrisonEstablishment(updateUserRecordsDTO.getAnother_prison_establishment());
			userReceptionRecordsEntity.setLegalStatus(legalStatusEntity);
			userReceptionRecordsEntity.setApprehendedTeenager(updateUserRecordsDTO.getApprehended_teenager());
			userReceptionRecordsEntity.setApprehendedAdult(updateUserRecordsDTO.getApprehended_adult());
			userReceptionRecordsEntity.setActualProcess(updateUserRecordsDTO.getActual_process());
			
			userReceptionRecordsRepository.save(userReceptionRecordsEntity);
			
			createUserCrimesEntities(userEntity.getUniqid(), updateUserRecordsDTO.getCrimes());
			userRecordService.updateUserRecordEntity(userId, updateUserRecordsDTO.getCourt_records_id());
		}
		
		return userReceptionRecordsEntity;
	}
	
	public boolean isValidUpdatedUserReceptionRecord(int userId, UpdatedUserRecordsDTO updateUserRecordsDTO) 
			throws NotFoundException, ConflictException {
		boolean isValid = true;
		
		UserEntity userEntity = userService.getUserEntity(userId);
		UserReceptionRecordsEntity userReceptionRecordsEntity = userReceptionRecordsRepository.findByUser(userEntity);

		if(userReceptionRecordsEntity == null) {
			String cause = String.format("There is not a User Reception Record for user whit id %d", userId);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			
			throw new NotFoundException(message);
		}
		
		if(updateUserRecordsDTO.getMonths_sentence() < 0) {
			String cause = String.format("Months of sentence could not be %d", updateUserRecordsDTO.getMonths_sentence());
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.ConflictException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			
			throw new ConflictException(message);
		}
		
		boolean isValidCourtRecord = isValidUpdatedCourtRecordDTO(userId,updateUserRecordsDTO.getCourt_records_id());
		LegalStatusEntity legalStatusEntity = legalStatusService.getLegalStatusEntity(updateUserRecordsDTO.getLegal_status_id());
		PrisonEstablishmentEntity prisEntity = prisoEstablishmentService.getPrisonEstablishmentEntity(updateUserRecordsDTO.getPrison_establishment_id());
		
		if(legalStatusEntity == null || prisEntity == null) {
			isValid = false;
		}
		
		return isValid;
	}

	public boolean isValidUpdatedCourtRecordDTO(int userId, NewCourtRecordDTO courtRecordDTO) throws NotFoundException, ConflictException {
		boolean isValid = true;

		/*Valide if there is a court user record*/
		UserRecordsEntity userRecordsEntity = userRecordService.getUserRecordEntity(userId);
		
		if(userRecordsEntity == null) {
			String cause = String.format("There is not an User Court Record for user with id %d", userId);
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

	public UserRecordsDTO mapToUserReceptionRecordDTO(UserReceptionRecordsEntity receptionRecordsEntity) {
		
		UserRecordsDTO userRecordsDTO = new UserRecordsDTO();
		
		if(receptionRecordsEntity != null) {
			PrisonEstablishmentEntity establishmentEntity = receptionRecordsEntity.getPrisonEstablishment();
			LegalStatusEntity legalStatusEntity = receptionRecordsEntity.getLegalStatus();
			
			LegalStatusDTO legalStatusDTO = new LegalStatusDTO();
			PrisonEstablishmentDTO prisonEstablishmentDTO = new PrisonEstablishmentDTO();
			
			if(legalStatusEntity != null) {
				legalStatusDTO.setUniqid(receptionRecordsEntity.getLegalStatus().getUniqid());
				legalStatusDTO.setStatus(receptionRecordsEntity.getLegalStatus().getStatus());
			}
			
			userRecordsDTO.setLegal_status(legalStatusDTO);

			
			if(establishmentEntity != null) {
				prisonEstablishmentDTO.setUniqid(establishmentEntity.getUniqid());
				prisonEstablishmentDTO.setPrison(establishmentEntity.getName());
			}
			
			userRecordsDTO.setPrison_establishment(prisonEstablishmentDTO);
			
			userRecordsDTO.setUser_uniqid(receptionRecordsEntity.getUniqid());
			userRecordsDTO.setFreedom_date(receptionRecordsEntity.getFreedomDate());
			userRecordsDTO.setMonths_sentence(receptionRecordsEntity.getMonthsSentence());
			userRecordsDTO.setAnother_prison_establishment(receptionRecordsEntity.getOtherPrisonEstablishment());
			userRecordsDTO.setApprehended_teenager(receptionRecordsEntity.getApprehendedTeenager());
			userRecordsDTO.setApprehended_adult(receptionRecordsEntity.getApprehendedAdult());
			userRecordsDTO.setActual_process(receptionRecordsEntity.getActualProcess());
			
			userRecordsDTO.setCrimes(userCrimesService.mapToCrimesDTO(receptionRecordsEntity.getUser()));
			userRecordsDTO.setCourtRecords(userRecordService.mapToCourtRecordDTO(receptionRecordsEntity.getUser()));
		}
		
		return userRecordsDTO;
	}

	public UserRecordsDTO getUserReceptionRecordDTO(int user_id) throws NotFoundException {
		UserEntity userEntity = userService.getUserEntity(user_id);
		return mapToUserReceptionRecordDTO(userReceptionRecordsRepository.findByUser(userEntity));
	}
}

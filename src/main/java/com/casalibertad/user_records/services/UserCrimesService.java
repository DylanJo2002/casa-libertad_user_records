package com.casalibertad.user_records.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.user_records.DTOS.CrimeDTO;
import com.casalibertad.user_records.DTOS.NewCrime;
import com.casalibertad.user_records.entities.CrimeEntity;
import com.casalibertad.user_records.entities.UserCrimesEntity;
import com.casalibertad.user_records.entities.UserEntity;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.repositories.UserCrimesRepository;

@Service
public class UserCrimesService {
	@Autowired
	private UserService userService;
	@Autowired
	private UserCrimesRepository crimesRepository;
	@Autowired
	private CrimeService crimeService;
	
	public List<UserCrimesEntity> getUserCrimesEntities(int userId) throws NotFoundException{
		UserEntity userEntity = userService.getUserEntity(userId);
		return crimesRepository.findByUser(userEntity);
	}
	
	public List<UserCrimesEntity> createUserCrimesEntities(int userId, List<NewCrime> newCrimeDTO) throws NotFoundException {		
		UserEntity userEntity = userService.getUserEntity(userId);
		List<UserCrimesEntity> userCrimesEntities = new ArrayList<UserCrimesEntity>();
		
		
		newCrimeDTO.forEach(crime -> {
				userCrimesEntities.add(createCrimeEntity(crime.getExternal_uniqid(), userEntity));
		});
		
		
		return userCrimesEntities;
	}
	
	public UserCrimesEntity createCrimeEntity(String crimeExternalId, UserEntity user) {
		CrimeEntity crimeEntity = crimeService.getCrimeEntityByExternalId(crimeExternalId);
		UserCrimesEntity userCrimesEntity = new UserCrimesEntity();
		userCrimesEntity.setCrime(crimeEntity);
		userCrimesEntity.setUser(user);
		
		return crimesRepository.save(userCrimesEntity);
	}
	
	public void removeUserCrimesEntities(int userId) {
		crimesRepository.removeUserCrimes(userId);
	}

	public List<CrimeDTO> mapToCrimesDTO(UserEntity userEntity){
		final List<CrimeDTO> crimeDTOs = new ArrayList<CrimeDTO>();
		
		List<UserCrimesEntity> userCrimesEntities = crimesRepository.findByUser(userEntity);
		
		if(userCrimesEntities.size() > 0) {
			userCrimesEntities.forEach(userCrime -> {
				CrimeEntity crimeEntity = userCrime.getCrime();
				CrimeDTO crimeDTO = new CrimeDTO();
				
				crimeDTO.setCrime(crimeEntity.getCrime());
				crimeDTO.setExternal_uniqid(crimeEntity.getExternalUniqid());
				crimeDTO.setUniqid(crimeEntity.getUniqid());
				
				crimeDTOs.add(crimeDTO);
			});
		}
		return crimeDTOs.size() == 0 ? null : crimeDTOs;
	}
}

package com.casalibertad.user_records.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.user_records.DTOS.NewCrime;
import com.casalibertad.user_records.entities.CrimeEntity;
import com.casalibertad.user_records.repositories.CrimeRepository;


@Service
public class CrimeService {
	
	@Autowired
	private CrimeRepository crimeRepository;
	
	public CrimeEntity getCrimeEntityById(int uniqid) {
		
		CrimeEntity crimeEntity = crimeRepository.findByUniqid(uniqid);		
			
		return crimeEntity;
	}
	
	public CrimeEntity getCrimeEntityByExternalId(String id) {
		
		CrimeEntity crimeEntity = crimeRepository.findByExternalUniqid(id);		
			
		return crimeEntity;
	}
	
	public void valideCrimeExistence(List<NewCrime> newCrime) {
		newCrime.forEach(crime -> {
			CrimeEntity crimeEntity = getCrimeEntityByExternalId(crime.getExternal_uniqid());
			
			if(crimeEntity == null) {
				crimeEntity = new CrimeEntity();
				crimeEntity.setExternalUniqid(crime.getExternal_uniqid());
				crimeEntity.setCrime(crime.getCrime());
			}else {
				crimeEntity.setCrime(crime.getCrime());
			}
			crimeRepository.save(crimeEntity);
		});
	}
}

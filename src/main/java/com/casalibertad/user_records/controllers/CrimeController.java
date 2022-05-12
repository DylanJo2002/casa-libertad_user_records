package com.casalibertad.user_records.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casalibertad.user_records.DTOS.CrimesDTO;
import com.casalibertad.user_records.services.CrimeService;

@RestController
@RequestMapping("/crimes")
public class CrimeController {
	
	@Autowired
	CrimeService crimeService;
	
	@GetMapping
	public ResponseEntity<CrimesDTO> getCrimes(){
		CrimesDTO crimes = crimeService.getCrimesDTO();
		ResponseEntity<CrimesDTO> responseEntity 
			= new ResponseEntity<CrimesDTO>(crimes, HttpStatus.OK);
		
		return responseEntity;
	}

}

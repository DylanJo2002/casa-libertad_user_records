package com.casalibertad.user_records.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casalibertad.user_records.DTOS.NewUserRecordsDTO;
import com.casalibertad.user_records.DTOS.UpdatedUserRecordsDTO;
import com.casalibertad.user_records.DTOS.UserRecordsDTO;
import com.casalibertad.user_records.services.UserReceptionRecordsService;

@RestController
@RequestMapping("records")
public class UserRecordController {
	
	@Autowired
	private UserReceptionRecordsService receptionRecordsService;
	
	@GetMapping("/{user_id}")
	public ResponseEntity<UserRecordsDTO> getUserRecordsDTO(@PathVariable int user_id){
		return null;
	}
	
	@PostMapping
	public ResponseEntity<UserRecordsDTO> createUserRecordsDTO(@RequestBody NewUserRecordsDTO newUserRecordsDTO){
		return null;
	}
	
	@PutMapping("/{user_id}")
	public ResponseEntity<UserRecordsDTO> getUserRecordsDTO(@PathVariable int user_id,
			@RequestBody UpdatedUserRecordsDTO updatedUserRecordsDTO){
		return null;
	}
	
}

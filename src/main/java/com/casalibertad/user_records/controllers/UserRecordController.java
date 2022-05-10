package com.casalibertad.user_records.controllers;

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

@RestController
@RequestMapping("records")
public class UserRecordController {
	
	@GetMapping("/{user_id}")
	public ResponseEntity<UserRecordsDTO> getUserRecordsDTO(@PathVariable int userId){
		return null;
	}
	
	@PostMapping
	public ResponseEntity<UserRecordsDTO> createUserRecordsDTO(@RequestBody NewUserRecordsDTO newUserRecordsDTO){
		return null;
	}
	
	@PutMapping("/{user_id}")
	public ResponseEntity<UserRecordsDTO> getUserRecordsDTO(@PathVariable int userId,
			@RequestBody UpdatedUserRecordsDTO updatedUserRecordsDTO){
		return null;
	}
	
}

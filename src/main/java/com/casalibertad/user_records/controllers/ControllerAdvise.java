package com.casalibertad.user_records.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.casalibertad.user_records.DTOS.ErrorDTO;
import com.casalibertad.user_records.enums.ErrorMessageEnum;
import com.casalibertad.user_records.exceptions.ConflictException;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.loggin.ExceptionLoggin;

@RestControllerAdvice
@RequestMapping("error")
public class ControllerAdvise {

	@Autowired
	private ExceptionLoggin exceptionLoggin;
	
	@ExceptionHandler(Throwable.class) 
	public ResponseEntity<ErrorDTO> exceptionHandler(Throwable ex) {
		String id = exceptionLoggin.getUUID();
		String message = exceptionLoggin.buildMessage(ErrorMessageEnum.InternalError,id
				,ex.getMessage(), this.getClass().toString());
		exceptionLoggin.saveLog(message,id);
		ErrorDTO errorDTO = new ErrorDTO(message);
		return new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NotFoundException.class) 
	public ResponseEntity<ErrorDTO> exceptionHandler(NotFoundException ex) {
		ErrorDTO errorDTO = new ErrorDTO(ex.getMessage());
		return new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ConflictException.class) 
	public ResponseEntity<ErrorDTO> exceptionHandler(ConflictException ex) {
		ErrorDTO errorDTO = new ErrorDTO(ex.getMessage());
		return new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.CONFLICT);
	}
}

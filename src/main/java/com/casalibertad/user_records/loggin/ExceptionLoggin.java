package com.casalibertad.user_records.loggin;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.casalibertad.user_records.enums.ErrorMessageEnum;
import com.casalibertad.user_records.services.LogService;


@Component
@Scope("singleton")
public class ExceptionLoggin {
	
	@Autowired
	private LogService logService;
	
	public String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	public String buildMessage(ErrorMessageEnum message, String id, String cause, String classname) {
		StringBuilder builder = new StringBuilder(ErrorMessageEnum
				.getKey(message))
				.append(id.concat("."))
				.append(" ".concat(cause))
				.append(". ".concat(classname));

		return builder.toString();
	}
	
	public void saveLog(String message ,String id) {
		logService.createLog(id, message);
	}
	
}

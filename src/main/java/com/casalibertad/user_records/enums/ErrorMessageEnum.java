package com.casalibertad.user_records.enums;

public enum ErrorMessageEnum {
	InternalError("Internal Error Server. Report error with id: "),
	NotFoundException("The resource was not found. Report error with id: "),
	ConflictException("There is a conlfict. Report error with id: ");

	public String message;
	
	private ErrorMessageEnum(String message) {
		this.message = message;
	}

	public static String getKey(ErrorMessageEnum errorMessage) {
		return errorMessage.getMessage();
	}
	
	public String getMessage() {
		return this.message;
	}
	
}

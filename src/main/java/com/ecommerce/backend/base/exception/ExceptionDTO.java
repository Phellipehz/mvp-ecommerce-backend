package com.ecommerce.backend.base.exception;

public class ExceptionDTO {

	private String code;
	private String message;
	private String exception;

	public ExceptionDTO() {
		super();
	}

	public ExceptionDTO(String code) {
		super();
		this.code = code;
	}

	public ExceptionDTO(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public ExceptionDTO(String code, String message, Exception exception) {
		super();
		this.code = code;
		this.message = message;
		this.exception = exception.toString();
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getException() {
		return exception;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

}


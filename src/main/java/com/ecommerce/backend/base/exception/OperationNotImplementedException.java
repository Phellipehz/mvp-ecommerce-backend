package com.ecommerce.backend.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OperationNotImplementedException extends Exception {

	public OperationNotImplementedException(){
		super("Operation not implemented yet");
	}
		
}
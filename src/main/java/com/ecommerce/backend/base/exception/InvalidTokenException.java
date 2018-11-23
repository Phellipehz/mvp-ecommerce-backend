package com.ecommerce.backend.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidTokenException extends Exception {

	public InvalidTokenException(){
		super("Invalid token provided");
	}
	
}

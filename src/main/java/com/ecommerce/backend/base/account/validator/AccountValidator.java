package com.ecommerce.backend.base.account.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ecommerce.backend.base.account.model.Account;

public class AccountValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg, Errors arg1) {
		Account account = (Account) arg;
		if(account.getId() != null){
			
		}
		
		if(account.getType() != null){
			
		}
		
	}

}

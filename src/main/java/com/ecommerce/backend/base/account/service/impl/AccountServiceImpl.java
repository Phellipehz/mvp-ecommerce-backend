package com.ecommerce.backend.base.account.service.impl;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.backend.base.account.model.Account;
import com.ecommerce.backend.base.account.persistence.AccountRepository;
import com.ecommerce.backend.base.account.service.AccountService;
import com.ecommerce.backend.base.exception.OperationNotImplementedException;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired(required=true) 
	AccountRepository aRepository;
	
	@Autowired(required=true) 
	PasswordEncoder enc;

	@Override
	public Account create(Account account) {
		account.setPassword( enc.encode(account.getPassword()) );
		return aRepository.save(account);
	}

	@Override
	public Account findById(Long id) throws OperationNotImplementedException {
		throw new OperationNotImplementedException();
	}

	@Override
	public List<Account> listAll() throws OperationNotImplementedException {
		throw new OperationNotImplementedException();
	}

	@Override
	public Boolean delete(Long id) throws OperationNotImplementedException {
		throw new OperationNotImplementedException();
	}
		
}


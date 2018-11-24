package com.ecommerce.backend.base.account.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.backend.base.account.model.Account;
import com.ecommerce.backend.base.account.persistence.AccountRepository;
import com.ecommerce.backend.base.account.service.AccountService;

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
	public Account findById(Long id){
		return aRepository.getOne(id);
	}

	@Override
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public List<Account> listAll(){
		return aRepository.findAll();
	}

	@Override
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public Boolean delete(Long id){
		aRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public Account update(Account account) {
		//TODO Verificar se nao está alterando id propositalmente
		return aRepository.save(account);
	}

	@Override
	public Account update(Long id, Account account) {
		return aRepository.save(account);
	}
		
}


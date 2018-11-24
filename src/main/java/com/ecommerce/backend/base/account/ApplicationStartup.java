package com.ecommerce.backend.base.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommerce.backend.base.account.model.Account;
import com.ecommerce.backend.base.account.persistence.AccountRepository;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
	
	@Autowired(required=true)
	AccountRepository acRepository;
	
	BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		Account account = new Account();
		account.setAdmin();
		account.setEmail("admin@admin.com");
		account.setPassword(enc.encode("Admin!@#"));
		
		Account accountVerify = acRepository.findByEmail(account.getEmail());
		if(accountVerify == null){
			acRepository.save(account);
		}
	}
}


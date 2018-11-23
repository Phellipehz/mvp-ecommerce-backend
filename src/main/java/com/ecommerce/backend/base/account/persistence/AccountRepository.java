package com.ecommerce.backend.base.account.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.base.account.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{	
	
	public Account findByEmailAndPassword(String email, String password);
	public Account findByEmail(String email);
	
}
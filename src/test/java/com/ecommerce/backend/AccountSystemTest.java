package com.ecommerce.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecommerce.backend.base.account.model.Account;
import com.ecommerce.backend.base.authentication.models.JwtAuthenticationResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountSystemTest extends BaseSystemTest{
	
	@Test
    public void createClientAccount(){
		Account account = new Account();
    	account.setClient();
    	
    	ResponseEntity<Account> responseEntity = 
        		restTemplate.postForEntity("/account/singup/client", account, Account.class);
    	Account response = responseEntity.getBody();
        assertNotNull(response.getEmail());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
}

package com.ecommerce.backend;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecommerce.backend.base.account.model.Account;
import com.ecommerce.backend.base.account.service.AccountService;
import com.ecommerce.backend.base.authentication.models.JwtAuthenticationRequest;
import com.ecommerce.backend.base.authentication.models.JwtAuthenticationResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationSystemTest extends BaseSystemTest {

	Account account;
	
	@Before
	public void createAccount(){
		Account account = new Account();
		account.setEmail("email@email.com");
		account.setPassword("password");
		account.setClient();
		this.beforeCreateRandomAccount(account);
	}
	
	@Test
    public void authenticate() {
		JwtAuthenticationRequest request = new JwtAuthenticationRequest();
		request.setEmail("email@email.com");
		request.setPassword("password");
    	
        ResponseEntity<JwtAuthenticationResponse> responseEntity = 
        		restTemplate.postForEntity("/authentication", request, JwtAuthenticationResponse.class);
        JwtAuthenticationResponse response = responseEntity.getBody();
	    assertNotNull(response.getToken());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}

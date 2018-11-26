package com.ecommerce.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import com.ecommerce.backend.base.account.service.AccountService;
import com.ecommerce.backend.base.authentication.models.JwtAuthenticationRequest;
import com.ecommerce.backend.base.authentication.models.JwtAuthenticationResponse;

public class BaseSystemTest {
	
	@Autowired
    TestRestTemplate restTemplate;
	
	@Autowired
	AccountService accountService;
	
	public String beforeAuthenticate(String username, String password) throws Exception{
		JwtAuthenticationRequest request = new JwtAuthenticationRequest();
		request.setEmail(username);
		request.setPassword(password);
    	
        ResponseEntity<JwtAuthenticationResponse> responseEntity = 
        		restTemplate.postForEntity("/authentication", request, JwtAuthenticationResponse.class);
        JwtAuthenticationResponse response = responseEntity.getBody();
	    return response.getToken();
	}
	
}

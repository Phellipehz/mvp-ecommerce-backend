package com.ecommerce.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import com.ecommerce.backend.base.account.model.Account;
import com.ecommerce.backend.base.account.service.AccountService;
import com.ecommerce.backend.base.authentication.models.JwtAuthenticationRequest;
import com.ecommerce.backend.base.authentication.models.JwtAuthenticationResponse;

public class BaseSystemTest {
	
//	MockMvc mvc;
//	@InjectMocks AuthenticationRestController controller;
//	@Autowired WebApplicationContext context;
//	
	@Autowired
    TestRestTemplate restTemplate;
	
	@Autowired
	AccountService accountService;
	
	public Account beforeCreateRandomAccount(Account account){
		String endpoint = "";
		
		if(account.isClient()){
			endpoint = "/account/singup/client";
		}
		else if(account.isAdmin()){
			endpoint = "/account/singup/admin";
		}
		
		ResponseEntity<Account> responseEntity = 
        		restTemplate.postForEntity(endpoint, account, Account.class);
        Account response = responseEntity.getBody();
        return response;
	}
	
	public String beforeAuthenticate(String username, String password) throws Exception{
		JwtAuthenticationRequest request = new JwtAuthenticationRequest();
		request.setEmail(username);
		request.setPassword(password);
    	
        ResponseEntity<JwtAuthenticationResponse> responseEntity = 
        		restTemplate.postForEntity("/authentication", request, JwtAuthenticationResponse.class);
        JwtAuthenticationResponse response = responseEntity.getBody();
	    return response.getToken();
        
//		mvc = MockMvcBuilders.webAppContextSetup(context).build();
//
//	    String body = "{\"email\":\"" + username + "\","  
//	    				+ "\"password\":\"" + password + "\""
//	    				+ "}";
//		
//	    MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/authentication")
//	            .content(body).contentType(MediaType.APPLICATION_JSON))
//	            .andExpect(status().isOk()).andReturn();
//
//	    String response = result.getResponse().getContentAsString();
//	    
//	    JSONObject my_obj = new JSONObject(response);
//	    return my_obj.getString("token");
	}
	
}

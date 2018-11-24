package com.ecommerce.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.validation.Valid;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecommerce.backend.application.model.Order;
import com.ecommerce.backend.base.account.model.Account;
import com.ecommerce.backend.base.authentication.models.JwtAuthenticationResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderSystemTest extends BaseSystemTest {

	String tokenClient;
	String tokenAdmin;
	
	Account accountClient = new Account();
	Account accountAdmin = new Account();
	
	@Before
	public void before() throws Exception{
		
		accountClient = beforeCreateRandomAccount(accountClient);
		accountAdmin = beforeCreateRandomAccount(accountAdmin);
		
		//TODO Criar produto
		
		tokenClient = beforeAuthenticate(accountClient.getEmail(), accountClient.getPassword());
		tokenAdmin = beforeAuthenticate(accountAdmin.getEmail(), accountAdmin.getPassword());
	}
	
	@Test
	public void createOrder(){
		Order order = new Order();
		order.setAccount(accountClient);
		
		restTemplate.headForHeaders("Authentication", tokenClient);
	
		ResponseEntity<Order> responseEntity = 
	    		restTemplate.postForEntity("/order", order, Order.class);
		Order response = responseEntity.getBody();
	    assertNotNull(response);
	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void listOrders(){
		
		restTemplate.headForHeaders("Authentication", tokenAdmin);
		
		ResponseEntity<List> responseEntity = 
	    		restTemplate.getForEntity("/order", List.class);
	    List<Order> response = responseEntity.getBody();
	    assertNotNull(response);
	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
}

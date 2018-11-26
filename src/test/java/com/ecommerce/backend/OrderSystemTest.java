package com.ecommerce.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecommerce.backend.application.model.Order;
import com.ecommerce.backend.application.model.OrderItem;
import com.ecommerce.backend.application.model.Product;
import com.ecommerce.backend.base.account.model.Account;
import com.ecommerce.backend.base.authentication.models.JwtAuthenticationResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderSystemTest extends BaseSystemTest {

	String tokenClient;
	String tokenAdmin;
	Product testProduct;
	
	@Before
	public void before() throws Exception{
		Product product = new Product();
    	product.setName("Relógio Bvlgary IronMan Réplica");
    	product.setCategory("Relogio");
    	product.setAmount(100L);
    	product.setPhoto("PHOTO");
    	product.setShortDescription("ShortDescript");
    	product.setDescription("Long Description");
    	   	
		this.tokenAdmin = this.beforeAuthenticate("admin@admin.com", "Admin!@#");
		this.tokenClient = this.beforeAuthenticate("client@client.com", "Client!@#");
    	   	
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Authorization", this.tokenAdmin);
    	HttpEntity<Product> request = new HttpEntity<Product>(product, headers);
    	
    	ResponseEntity<Product> responseEntity = 
    			restTemplate.postForEntity("/product", request, Product.class);
    	testProduct = responseEntity.getBody();
	}
	
	@Test
	public void createOrder(){
		//Given
		//===================================================================
		Order order = new Order();
		List<OrderItem> products = new ArrayList<>();
		
		Product product = new Product();
		product.setId(1L);
		
		OrderItem item = new OrderItem();
		item.setProduct(product);
		item.setAmount(testProduct.getId());
		
		products.add(item);
		order.setProducts(products);
		
		HttpHeaders headers = new HttpHeaders();
    	headers.set("Authorization", this.tokenClient);
    	HttpEntity<Order> request = new HttpEntity<Order>(order, headers);
    	
    	//When
    	//===================================================================
		ResponseEntity<Order> responseEntity = 
	    		restTemplate.postForEntity("/order", request, Order.class);
		Order response = responseEntity.getBody();
		
		//Then
		//===================================================================
	    assertNotNull(response);
	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void listOrders(){
		//When
    	//===================================================================
		ResponseEntity<List> responseEntity = 
	    		restTemplate.getForEntity("/order", List.class);
	    List<Order> response = (List<Order>) responseEntity.getBody();
	    
	    //Then
	  	//===================================================================
	    assertNotNull(response);
	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
}

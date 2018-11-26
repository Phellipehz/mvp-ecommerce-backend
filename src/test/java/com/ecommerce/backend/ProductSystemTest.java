package com.ecommerce.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.ecommerce.backend.application.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductSystemTest extends BaseSystemTest {

	String token = "";
	
	@Before
	public void before() throws Exception{
		this.token = this.beforeAuthenticate("admin@admin.com", "Admin!@#");
	}
	
	@Test
    public void createProduct(){
    	Product product = new Product();
    	product.setName("Relógio Bvlgary IronMan Réplica");
    	product.setCategory("Relogio");
    	product.setAmount(10L);
    	product.setPhoto("PHOTO");
    	product.setShortDescription("ShortDescript");
    	product.setDescription("Long Description");
    	   	
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Authorization", this.token);
    	HttpEntity<Product> request = new HttpEntity<Product>(product, headers);
    	
    	ResponseEntity<Product> responseEntity = 
    			restTemplate.postForEntity("/product", request, Product.class);
    	
    	Product response = responseEntity.getBody();
        assertNotNull(response);
        System.out.println(responseEntity.getStatusCode());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
	@Test
    public void listProducts(){
    	ResponseEntity<List> responseEntity = 
        		restTemplate.getForEntity("/product", List.class);
        List<Product> response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
	@Test
    public void getProduct(){
    	ResponseEntity<Product> responseEntity = 
        		restTemplate.getForEntity("/product/1", Product.class);
        Product response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
	@Test
    public void updateProduct(){
    	Product product = new Product();
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Authorization", this.token);
    	HttpEntity<Product> request = new HttpEntity<Product>(product, headers);
    	
    	ResponseEntity<Product> responseEntity = 
        		restTemplate.postForEntity("/product/1", request, Product.class);
    	
        Product response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
	@Test
    public void deleteProduct(){
		
		HttpHeaders headers = new HttpHeaders();
    	headers.set("Authorization", this.token);
    	HttpEntity<Product> request = new HttpEntity<Product>(null, headers);
        
    	restTemplate.delete("/product/1", request, Void.class);
       
        ResponseEntity<Product> responseEntity = 
        		restTemplate.getForEntity("/product/1", Product.class);
        Product response = responseEntity.getBody();
        assertNull(response);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
	
	@Test
    public void listProductsByName(){
    	ResponseEntity<List> responseEntity = 
        		restTemplate.getForEntity("/product/search/Relógio", List.class);
        List<Product> response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
	   
}

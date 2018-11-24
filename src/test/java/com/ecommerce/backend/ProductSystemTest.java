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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecommerce.backend.application.model.Product;
import com.ecommerce.backend.base.authentication.models.JwtAuthenticationResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductSystemTest extends BaseSystemTest {

	String token = "";
	
	@Before
	public void before() throws Exception{
		this.beforeAuthenticate("", "");
	}
	
	@Test
    public void createProduct(){
    	Product product = new Product();
    	product.setName("Relógio Bvlgary IronMan Réplica");
    	
    	ResponseEntity<Product> responseEntity = 
        		restTemplate.postForEntity("/authentication", product, Product.class);
    	Product response = responseEntity.getBody();
        assertNotNull(response.getName());
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
    	ResponseEntity<JwtAuthenticationResponse> responseEntity = 
        		restTemplate.getForEntity("/product/1", JwtAuthenticationResponse.class);
        JwtAuthenticationResponse response = responseEntity.getBody();
        assertNotNull(response.getToken());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
	@Test
    public void updateProduct(){
    	Product product = new Product();
    	
    	ResponseEntity<JwtAuthenticationResponse> responseEntity = 
        		restTemplate.postForEntity("/product/1", product, JwtAuthenticationResponse.class);
        JwtAuthenticationResponse response = responseEntity.getBody();
        assertNotNull(response.getToken());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
	@Test
    public void deleteProduct(){
//    	ResponseEntity<Void> responseEntity = 
//        		restTemplate.delete("/product/1", Void.class);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
	
	@Test
    public void listProductsByName(){
    	ResponseEntity<List> responseEntity = 
        		restTemplate.getForEntity("/search/{term}", List.class);
        List<Product> response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
	
	@Test
    public void listProductsByCategory(){
    	ResponseEntity<List> responseEntity = 
        		restTemplate.getForEntity("/category/{category}", List.class);
        List<Product> response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
	
    // ========================================================
    //  Fluxos Alternativos
    // ========================================================
    
}

package com.ecommerce.backend.application.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.application.model.Product;
import com.ecommerce.backend.application.service.ProductService;

@RestController
@RequestMapping(value ="/product", produces = "application/json")
public class ProductController {
	
	@Autowired(required=true)
	ProductService pService;

	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){		
		Product rProduct = pService.create(product);
		return new ResponseEntity<>(rProduct, HttpStatus.OK);
	}
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> listProducts(){
		List<Product> rProduct = pService.listAll();
		return new ResponseEntity<>(rProduct, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
		Product rProduct = pService.findById(id);
		return new ResponseEntity<>(rProduct, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody Product product){
		Product rProduct = pService.update(id, product);
		return new ResponseEntity<>(rProduct, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
		Boolean product = pService.delete(id);
		if(product == Boolean.TRUE){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(path = "/search/{term}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> findProductsByName(@PathVariable("term") String term){
		List<Product> rProduct = pService.findByNameOrCategory(term);
		return new ResponseEntity<>(rProduct, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/category", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getAllCategories(){
		List<String> rProduct = pService.getAllCategories();
		return new ResponseEntity<>(rProduct, HttpStatus.OK);
	}
	
}

package com.ecommerce.backend.application.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ecommerce.backend.application.model.Product;
import com.ecommerce.backend.application.persistence.ProductRepository;

@Component
public class ProductValidator implements Validator {

	@Autowired(required = true)
	ProductRepository pRepository;
	
	@Override
	public boolean supports(Class<?> arg0) {
		return Product.class.equals(arg0);
	}

	@Override
	public void validate(Object arg, Errors e) {
		
		// Evitar que alguem intencionalmente mude o 
		// id de um produto
		
		Product product = (Product) arg;
		if(product.getId() != null){
			 e.rejectValue("id", "IdNotAllowed");
		}
		
	}

}

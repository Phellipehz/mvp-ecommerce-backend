package com.ecommerce.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ecommerce.backend.application.validator.ProductValidator;
import com.ecommerce.backend.base.account.validator.AccountValidator;

@SpringBootApplication
public class MvpEcommerceBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvpEcommerceBackendApplication.class, args);
	}

}

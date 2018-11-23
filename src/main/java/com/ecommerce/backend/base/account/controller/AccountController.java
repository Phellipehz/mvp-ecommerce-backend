package com.ecommerce.backend.base.account.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.base.account.service.AccountService;

@RestController
@RequestMapping(value ="/v1/account", produces = "application/json")
public class AccountController {
	
	@Autowired(required = true) 
	AccountService aService;

}

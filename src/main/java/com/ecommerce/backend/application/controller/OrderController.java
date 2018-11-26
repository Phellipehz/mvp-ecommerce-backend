package com.ecommerce.backend.application.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.application.model.Order;
import com.ecommerce.backend.application.service.OrderService;

@RestController
@RequestMapping(value ="/order", produces = "application/json")
public class OrderController {

	@Autowired(required=true)
	OrderService oService;
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> listOrders(){
		List<Order> rOrder = oService.listAll();
		return new ResponseEntity<>(rOrder, HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<Order> createOrder(@RequestBody Order order){	
		Order rOrder = oService.create(order);
		return new ResponseEntity<>(rOrder, HttpStatus.OK);
	}
	
}

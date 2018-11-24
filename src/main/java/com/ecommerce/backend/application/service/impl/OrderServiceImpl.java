package com.ecommerce.backend.application.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.application.model.Order;
import com.ecommerce.backend.application.persistence.OrderRepository;
import com.ecommerce.backend.application.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired(required = true)
	OrderRepository oRepository;
	
	@Override
	public Order create(Order order) {
		return oRepository.save(order);
	}

	@Override
	public Order findById(Long id) {
		return oRepository.getOne(id);
	}

	@Override
	public List<Order> listAll() {
		return oRepository.findAll();
	}

	@Override
	public Order update(Order order) {
		return oRepository.save(order);
	}

	@Override
	public Order update(Long id, Order t) {
		return oRepository.save(t);
	}

	@Override
	public Boolean delete(Long id) {
		oRepository.deleteById(id);
		return Boolean.TRUE;
	}

}

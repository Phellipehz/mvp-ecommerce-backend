package com.ecommerce.backend.application.service.impl;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.application.model.Order;
import com.ecommerce.backend.application.model.OrderItem;
import com.ecommerce.backend.application.model.Product;
import com.ecommerce.backend.application.persistence.OrderItemRepository;
import com.ecommerce.backend.application.persistence.OrderRepository;
import com.ecommerce.backend.application.persistence.ProductRepository;
import com.ecommerce.backend.application.service.OrderService;
import com.ecommerce.backend.base.account.model.Account;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired(required = true)
	OrderRepository oRepository;
	
	@Autowired(required = true)
	ProductRepository pRepository;
	
	@Autowired(required = true)
	OrderItemRepository oiRepository;
	
	@Override
	public Order create(Order order) {
		// FIXME - Falhas conhecidas no projeto
		// E se nao tiver mais produtos?
		// E se der ruim? Vai cancelar a transaction?  
		// E se o usuario (mal intencionado) mandar dados do produto?
		
		Account account = (Account)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		for(OrderItem item : order.getProducts() ){
			Product product = item.getProduct();
			Long amount = item.getAmount();
			
			product = pRepository.getOne(product.getId());
			product.setAmount( product.getAmount() - amount );
			
			//oiRepository.save(item);
			pRepository.save(product);
		}
		
		order.setAccount(account);
		return oRepository.save(order);
	}

	@Override
	public Order findById(Long id) {
		return oRepository.getOne(id);
	}

	@Override
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
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

package com.ecommerce.backend.application.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.application.model.Product;
import com.ecommerce.backend.application.persistence.ProductRepository;
import com.ecommerce.backend.application.service.ProductService;
import com.ecommerce.backend.base.exception.OperationNotImplementedException;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired(required=true)
	ProductRepository pRepository;
	
	@Override
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public Product create(Product product){
		return pRepository.save(product);
	}

	@Override
	public Product findById(Long id){
		return pRepository.getOne(id);
	}

	@Override
	public List<Product> listAll(){
		return pRepository.findAll();
	}

	@Override
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public Boolean delete(Long id){
		pRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public Product update(Product t) {
		//TODO Verificar se nao está alterando id propositalmente
		return pRepository.save(t);
	}

	@Override
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public Product update(Long id, Product t) {
		//TODO Verificar se nao está alterando id propositalmente
		Product product = pRepository.getOne(id);
		return pRepository.save(t);
	}

	@Override
	public List<Product> findByName(String term) {
		return pRepository.findByNameOrCategoryLike(term);
	}

	@Override
	public List<Product> findByCategory(String category) {
		return pRepository.findByCategoryLike(category);
	}

	@Override
	public List<String> getAllCategories() {
		return pRepository.getAllCategories();
	}
	
}

package com.ecommerce.backend.application.service;

import java.util.List;

import com.ecommerce.backend.application.model.Product;
import com.ecommerce.backend.base.service.CRUDService;

public interface ProductService extends CRUDService<Product> {
	List<Product> findByNameOrCategory(String term);
	List<String> getAllCategories();
}

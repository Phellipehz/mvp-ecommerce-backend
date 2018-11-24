package com.ecommerce.backend.application.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.application.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findByCategoryLike(String category);
	List<Product> findByNameOrCategoryLike(String term);
}

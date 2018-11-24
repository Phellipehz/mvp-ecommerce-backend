package com.ecommerce.backend.application.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.application.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findByCategoryLike(String category);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE %:term% OR p.category LIKE %:term%")
	List<Product> findByNameOrCategoryLike(String term);
	
	@Query("SELECT p.category FROM Product p GROUP BY p.category")
	List<String> getAllCategories();
}

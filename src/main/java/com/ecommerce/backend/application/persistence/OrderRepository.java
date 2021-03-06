package com.ecommerce.backend.application.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.application.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
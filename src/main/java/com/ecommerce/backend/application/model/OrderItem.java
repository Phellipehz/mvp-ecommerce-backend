package com.ecommerce.backend.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	public Long id;    
	
	@OneToOne
	Product product;
	
	@Min(1)
	Long amount;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Long getAmount() {
		return amount;
	}
	
	public void setAmount(Long amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", product=" + product + ", amount=" + amount + "]";
	}
	
	
	
}

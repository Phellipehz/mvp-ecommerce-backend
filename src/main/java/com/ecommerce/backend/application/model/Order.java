package com.ecommerce.backend.application.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.ecommerce.backend.base.account.model.Account;
import com.ecommerce.backend.base.account.model.BaseEntity;

@Entity(name="OrderItens")
public class Order extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	public Long id;    
	
//	@ElementCollection
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	List<OrderItem> products; //<Produto, Quantidade>
	
	@OneToOne
	Account account;
	
	public List<OrderItem> getProducts() {
		return products;
	}

	public void setProducts(List<OrderItem> products) {
		this.products = products;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}

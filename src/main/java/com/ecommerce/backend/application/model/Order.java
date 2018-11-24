package com.ecommerce.backend.application.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import com.ecommerce.backend.base.account.model.Account;
import com.ecommerce.backend.base.account.model.BaseEntity;

@Entity(name="OrderItens")
public class Order extends BaseEntity {
	
	@OneToOne
	Product product;
	
	@Min(1)
	Long amount;
	
	@OneToOne
	Account account;
	
	ShippingMode shippingMode;
	
	PaymentMode paymentMode;

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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ShippingMode getShippingMode() {
		return shippingMode;
	}

	public void setShippingMode(ShippingMode shippingMode) {
		this.shippingMode = shippingMode;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}
	
}

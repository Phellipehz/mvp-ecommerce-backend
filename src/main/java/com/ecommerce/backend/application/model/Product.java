package com.ecommerce.backend.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ecommerce.backend.base.account.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	public Long id;    
	
	@NotNull
	@Size(min=2, message="Name should have at least 2 characters")
	@JsonProperty(value = "name")
	String name;
	
	@NotNull
	Double value;
	
	@NotNull
	@Size(min=2, message="Description should have at least 2 characters")
	String description;
	
	@NotNull
	@Column(name="photo", columnDefinition="LONGTEXT")
	String photo;
	
	@NotNull
	@Min(1)
	Long amount;
	
	@Size(min=2, max = 20, message="Description should have at least 2 characters, and up to 20")
	String category;
	
	@Size(min=2, max = 100, message="Description should have at least 2 characters, and up to 100")
	String shortDescription;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getValue() {
		return value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", value=" + value + ", description=" + description + ", photo=" + photo
				+ ", amount=" + amount + ", category=" + category + ", shortDescription=" + shortDescription + "]";
	}
	
	
	
	
	
}

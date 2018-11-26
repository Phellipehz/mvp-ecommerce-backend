package com.ecommerce.backend.base.account.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Account extends BaseEntity implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	public Long id;    
	
	@Size(min = 5, message = "O email tem que ter no minimo 5 caracteres!")
	@Column(name = "email", nullable = false, unique = true)
	@JsonProperty(value = "email")
	private String email;
	
	@Size(min = 4, message = "A senha tem que ter no minimo 4 caracteres!")
	@Column(name = "password", nullable = false)
	String password;
	
	@Size(min = 2, message = "O nome tem que ter no minimo 2 caracteres!")
	@Column(name = "name", nullable = false)
	String name;
	
	@Column(name = "enabled", nullable = false)
	@JsonIgnore
	boolean enabled = true;
	
	@Column(name = "type", updatable = false)
	@JsonIgnore
	String type; 
	
	public Account(){
		this.enabled = true;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean isClient(){
		return "CLIENT".equals(this.type); 
	}
	
	public Boolean isAdmin(){
		return "ADMINISTRATOR".equals(this.type); 
	}
	
	public void setClient() {
		this.type = "CLIENT";
	}
	
	public void setAdmin() {
		this.type = "ADMINISTRATOR";
	}	
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@JsonIgnore
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(this.type));
		return authorities;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}


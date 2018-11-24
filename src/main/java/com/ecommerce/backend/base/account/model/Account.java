package com.ecommerce.backend.base.account.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account extends BaseEntity implements UserDetails{

	@Size(min = 5, message = "O email tem que ser entre 5 e 30 caracteres!")
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Size(min = 6, message = "A senha tem que ser entre 6 e 30 caracteres!")
	@Column(name = "password", nullable = false)
	String password;
	
	@Column(name = "enabled", nullable = false)
	boolean enabled = true;
	
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
		return "ADMIN".equals(this.type); 
	}
	
	public void setClient() {
		this.type = "CLIENT";
	}
	
	public void setAdmin() {
		this.type = "ADMIN";
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
	
}


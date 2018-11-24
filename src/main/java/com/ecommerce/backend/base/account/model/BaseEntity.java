package com.ecommerce.backend.base.account.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	public Long id;     
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone="Brazil/East", locale = "pt-BR")
	@Column(name = "creation_time", nullable = false)
	private Date creationTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone="Brazil/East", locale = "pt-BR")
	@Column(name = "modification_time", nullable = false)
	private Date modificationTime;

	@PrePersist
	public void prePersist() {
		Date now = new Date();
		this.creationTime = now;
		this.modificationTime = now;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@PreUpdate
	public void preUpdate() {
		this.modificationTime = new Date();
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}
	
}

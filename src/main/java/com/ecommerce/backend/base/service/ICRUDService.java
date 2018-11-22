package com.ecommerce.backend.base.service;

import java.util.List;

public interface ICRUDService<T> {
	public T create(T t);
	public T findById(Long id);
	public List<T> listAll();
	public Boolean delete(Long id);
}

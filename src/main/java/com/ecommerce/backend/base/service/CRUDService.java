package com.ecommerce.backend.base.service;

import java.util.List;

public interface CRUDService<T> {
	public T create(T t);
	public T findById(Long id);
	public List<T> listAll();
	public T update(T t);
	public T update(Long id, T t);
	public Boolean delete(Long id);
}

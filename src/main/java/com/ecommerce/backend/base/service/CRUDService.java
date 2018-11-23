package com.ecommerce.backend.base.service;

import java.util.List;

import com.ecommerce.backend.base.exception.OperationNotImplementedException;

public interface CRUDService<T> {
	public T create(T t) throws OperationNotImplementedException;
	public T findById(Long id) throws OperationNotImplementedException;
	public List<T> listAll() throws OperationNotImplementedException;
	public Boolean delete(Long id) throws OperationNotImplementedException;
}

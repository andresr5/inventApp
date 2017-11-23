package com.example.repository;

import java.io.Serializable;
import java.util.List;

import com.example.DTO.Producto;

public interface CRUDService<E> {
	
	E save(E entity);

	E getById(Serializable id);

	List<E> getAll();

	void delete(Serializable id);
	
	


}

package com.example.repository;

import java.io.Serializable;
import java.util.List;

import com.example.DTO.Producto;

public interface CRUDServiceCategoria<Categoria> {
	Categoria save(Categoria entity);

	Categoria getById(Serializable id);

	List<Categoria> getAll();

	void delete(Serializable id);
	
	void deleteAll();
	
	List<Producto> getProductosById(int i);
	
	int countRegisters();
	
	int getLastId();
	

	
}

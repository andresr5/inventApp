package com.example.repository;

import java.io.Serializable;
import java.util.List;


public interface CRUDServiceProducto<Producto>{

	Producto save(Producto entity);

	Producto getById(Serializable id);

	List<Producto> getAll();
	
	void delete(Serializable id);
	
	void deleteAll();

    List<Producto> findProductsByCategoria(int i);	
    
    int updateCategoria(int idProducto, int idCategoria);

	public int getLastId();
    
	
	
}

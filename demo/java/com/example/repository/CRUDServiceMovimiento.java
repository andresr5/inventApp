package com.example.repository;

import java.io.Serializable;
import java.util.List;

public interface CRUDServiceMovimiento<Movimiento> {

	Movimiento save(Movimiento entity);

	Movimiento getById(Serializable id);

	List<Movimiento> getAll();
	
	void delete(Serializable id);
	
	void deleteAll();
	
	int getLastId();
	
	List<Movimiento> getRepProductosPrecioCantidadUnitario();
}

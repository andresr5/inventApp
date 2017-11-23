package com.example.repository;

import java.io.Serializable;
import java.util.List;

interface CRUDServiceTipoMovimiento<TipoMovimiento> {

	TipoMovimiento save(TipoMovimiento entity);

	TipoMovimiento getById(Serializable id);

	List<TipoMovimiento> getAll();
	
	void delete(Serializable id);
	
	void deleteAll();
	
}

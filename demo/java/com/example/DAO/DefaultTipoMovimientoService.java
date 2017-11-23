package com.example.DAO;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.TipoMovimiento;
import com.example.repository.TipoMovimientoRepository;
import com.example.repository.TipoMovimientoService;

@Service
public class DefaultTipoMovimientoService implements TipoMovimientoService{
	
	
	@Autowired
	private TipoMovimientoRepository tipoMovimientoRepository;

	@Override
	public TipoMovimiento save(TipoMovimiento entity) {
		return tipoMovimientoRepository.save(entity);
	}

	@Override
	public TipoMovimiento getById(Serializable id) {
		// TODO Auto-generated method stub
		return tipoMovimientoRepository.findOne((int)id);
	}

	@Override
	public List<TipoMovimiento> getAll() {
		// TODO Auto-generated method stub
		return tipoMovimientoRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		tipoMovimientoRepository.delete((int)id);
		
	}

	@Override
	public void deleteAll() {
		tipoMovimientoRepository.deleteAll();
		
	}

	
	

}

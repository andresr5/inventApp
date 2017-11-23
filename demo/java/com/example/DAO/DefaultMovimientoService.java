package com.example.DAO;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.Movimiento;
import com.example.repository.MovimientoRepository;
import com.example.repository.MovimientoService;

@Service
public class DefaultMovimientoService implements MovimientoService{

	@Autowired
	private MovimientoRepository movimientoRepository;

	@Override
	public Movimiento save(Movimiento entity) {
		return movimientoRepository.save(entity);
	}

	@Override
	public Movimiento getById(Serializable id) {
		return movimientoRepository.findOne((int)id);
	}

	@Override
	public List<Movimiento> getAll() {
		return movimientoRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		movimientoRepository.delete((int)id);

	}

	@Override
	public void deleteAll() {
		movimientoRepository.deleteAll();

	}

	@Override
	public int getLastId() {
		return movimientoRepository.getLastId();
	}

	@Override
	public List<Movimiento> getRepProductosPrecioCantidadUnitario() {
		return movimientoRepository.getRepProductosPrecioCantidadUnitario();
	}

}

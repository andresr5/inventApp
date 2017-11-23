package com.example.DAO;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.Producto;
import com.example.repository.ProductoRepository;
import com.example.repository.ProductoService;

@Service
public class DefaultProductoService implements ProductoService{
    
	@Autowired
	private ProductoRepository productoRepository;
	
	
	@Override
	public Producto save(Producto entity) {
		return productoRepository.save(entity);
	}

	@Override
	public Producto getById(Serializable id) {
		// TODO Auto-generated method stub
		return productoRepository.findOne((int)id);
	}

	@Override
	public List<Producto> getAll() {
		// TODO Auto-generated method stub
		return productoRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		productoRepository.delete((int)id);
		
	}

	@Override
	public List<Producto> findProductsByCategoria(int i) {
		return productoRepository.findProductsByCategoria(i);
	}

	@Override
	public void deleteAll() {
		productoRepository.deleteAll();		
	}

	@Override
	public int updateCategoria(int idProducto, int idCategoria) {
		
		System.out.println("idProducto in categoriaaaa ");
		System.out.println(idProducto);
		System.out.println("idCategoria in categoriaaaa ");
		System.out.println(idCategoria);
		return productoRepository.updateCategoria(idProducto, idCategoria);
	}

	@Override
	public int getLastId() {
		System.out.println("getting last id ------------------------------------------------------------------->");
		System.out.println(productoRepository.getLastId());
		return productoRepository.getLastId();
	}

	
	

}

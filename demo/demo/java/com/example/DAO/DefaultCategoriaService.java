package com.example.DAO;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.Categoria;
import com.example.DTO.Producto;
import com.example.repository.CategoriaRepository;
import com.example.repository.CategoriaService;


@Service
public class DefaultCategoriaService implements CategoriaService{
    

	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	
	@Override
	public Categoria save(Categoria entity) {
		//categoriaRepository.count();
		//entity.setId((int)categoriaRepository.count());
		return categoriaRepository.save(entity);
	}

	@Override
	public Categoria getById(Serializable id) {
		// TODO Auto-generated method stub
		return categoriaRepository.findOne((int)id);
	}

	@Override
	public List<Categoria> getAll() {
		// TODO Auto-generated method stub
		return categoriaRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		categoriaRepository.delete((int) id);
		
	}
	
	
	public List<Producto> getProductosById(int id){
		return categoriaRepository.getProductosById(id);
	}

	@Override
	public void deleteAll() {
		categoriaRepository.deleteAll();
		
	}

	@Override
	public int countRegisters() {
		
		return (int) categoriaRepository.count();
	}

	@Override
	public int getLastId() {
		return categoriaRepository.getLastId();
	}
	
	
	


}

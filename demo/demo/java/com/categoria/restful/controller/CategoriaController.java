package com.categoria.restful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.Categoria;
import com.example.repository.CategoriaService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaService catService;

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Categoria> getCategoria(@PathVariable("id") int id){
		Categoria categoria = catService.getById(id);
		if(categoria == null) {
			return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Categoria>(categoria,HttpStatus.OK);
		}
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Categoria>> getAllCategorias(){
		List<Categoria> categorias =  catService.getAll();
		if(categorias.isEmpty()) {
			return new ResponseEntity<List<Categoria>>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Categoria>>(categorias,HttpStatus.OK);
		}
	}


	@RequestMapping(consumes = "application/json", method = RequestMethod.POST )
	public ResponseEntity<Categoria> addCategoria(@RequestBody Categoria categoria) {
		
		catService.save(categoria);
		return new ResponseEntity<Categoria>(categoria, HttpStatus.CREATED);
	}


	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> updateCategoria(@RequestBody Categoria categoria){
		System.out.println("-------------------------------> CATEGORIA");
		System.out.println(categoria);
		Categoria categoriaActual =  catService.getById(categoria.getId());
		System.out.println("-------------------------------> CATEGORIA actual");
		System.out.println(categoriaActual);
		if(categoriaActual== null) {
			return new ResponseEntity<String>("No se encontro la categoria con Id: "+categoria.getId(),HttpStatus.NOT_FOUND);
		}
        
		categoriaActual.setNombre(categoria.getNombre());
		categoriaActual.setDescripcion(categoria.getDescripcion());

		catService.save(categoriaActual);
		return new ResponseEntity<Categoria>(categoriaActual,HttpStatus.OK); 
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCategoria(@PathVariable("id") int id) {

		Categoria categoria = catService.getById(id);
		if (categoria == null) {
			return new ResponseEntity<String>("No existe un usuario con ese Id",
					HttpStatus.NOT_FOUND);
		}
		catService.delete(id);
		return new ResponseEntity<Categoria>(HttpStatus.NO_CONTENT);
	}



	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Categoria> deleteAll() {
		catService.deleteAll();
		return new ResponseEntity<Categoria>(HttpStatus.NO_CONTENT);
	}

     
	@RequestMapping(value="/count", method= RequestMethod.GET)
	public ResponseEntity<?> countRegisters(){
	   return new ResponseEntity<Integer>(catService.countRegisters(),HttpStatus.OK);
	
	}
	
	@RequestMapping(value="/getLastId", method= RequestMethod.GET)
	public ResponseEntity<?> getLastId(){
	   return new ResponseEntity<Integer>(catService.getLastId(),HttpStatus.OK);
	
	}
	
	





}





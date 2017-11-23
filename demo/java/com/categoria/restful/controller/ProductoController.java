package com.categoria.restful.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.Producto;
import com.example.repository.ProductoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	ProductoService prodService;


	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Producto> getProducto(@PathVariable("id") int id){
		System.out.println("-------------------------->");
		System.out.println(id);
		Producto producto = prodService.getById(id);
		if(producto == null) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Producto>(producto,HttpStatus.OK);
		}
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Producto>>getAllProductos(){
		List<Producto> productos = prodService.getAll();
		if(productos.isEmpty()) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NO_CONTENT);
		}else{
			return new ResponseEntity<List<Producto>>(productos,HttpStatus.OK);
		}
	} 


	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Producto> addproducto(@RequestBody Producto producto){
		System.out.println("adddproducto");
		System.out.println("producto --- >>> ");
		System.out.println(producto);
		System.out.println("categoria  ---->>>>");

		System.out.println(producto.getCategoria());
		prodService.save(producto);
		//prodService.updateCategoria(producto.getId(), producto.getCategoria().getId());
		return new ResponseEntity<Producto>(producto,HttpStatus.CREATED);
	}


	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> updateProducto(@RequestBody Producto producto){
		Producto productoActual = prodService.getById(producto.getId()); 
		if(productoActual == null) {
			return new ResponseEntity<String>("No se encontro el producto con id: "+producto.getId(),
					HttpStatus.NOT_FOUND);
		}
		System.out.println("Producto to update ----------------------->");
		System.out.println(producto);
		productoActual.setNombre(producto.getNombre());
		productoActual.setPrecio(producto.getPrecio());
		productoActual.setDescripcion(producto.getDescripcion());
		productoActual.setCodigo(producto.getCodigo());
		productoActual.setCategoria(producto.getCategoria());

		prodService.save(productoActual);
		if(producto.getCategoria()!=null) {
			prodService.updateCategoria(producto.getId(), producto.getCategoria().getId());
		}
		return new ResponseEntity<Producto>(productoActual,HttpStatus.OK);

	}

	@RequestMapping(value="/productosByCategoria/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getProductosById(@PathVariable("id") int id){
		List<Producto> producto = prodService.findProductsByCategoria(id);
		if(producto.isEmpty()) {
			List<String> error = new ArrayList<String>();
			error.add("No se encontraron productos asociados a la categoria ");
			return new ResponseEntity<List<String>>(error,HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<Producto>>(producto,HttpStatus.OK);
		}
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProducto(@PathVariable("id") int id) {

		Producto producto= prodService.getById(id);
		if (producto == null) {
			return new ResponseEntity<String>("No existe un producto con ese Id",
					HttpStatus.NOT_FOUND);
		}
		prodService.delete(id);
		return new ResponseEntity<Producto>(HttpStatus.NO_CONTENT);
	}


	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Producto> deleteAll() {

		prodService.deleteAll();
		return new ResponseEntity<Producto>(HttpStatus.NO_CONTENT);
	}


	@RequestMapping(value="/categoria", method=RequestMethod.PUT)
	public ResponseEntity<?> updateCategoria(HttpEntity<String> httpEntity ){

		if(httpEntity == null) {
			return new ResponseEntity<String>("Debe ingresar un producto y categoria ",HttpStatus.BAD_REQUEST);
		}


		if(httpEntity.getBody() == null) {
			return new ResponseEntity<String>("Debe ingresar un producto y categoria ",HttpStatus.BAD_REQUEST);
		}

		String jsonString = httpEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode actualObj = mapper.readTree(jsonString);
			if(actualObj.get("idProducto")==null) {
				return new ResponseEntity<String>("Debe ingresar un producto ",HttpStatus.BAD_REQUEST);
			}
			if(actualObj.get("idCategoria")==null) {
				return new ResponseEntity<String>("Debe ingresar un producto ",HttpStatus.BAD_REQUEST);
			}
			int idProducto =(int)actualObj.get("idProducto").asInt();
			int idCategoria =(int) actualObj.get("idCategoria").asInt();
			prodService.updateCategoria(idProducto, idCategoria);


		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Se ha actualizado el producto ",HttpStatus.OK);
	}

	@RequestMapping(value="/getLastId", method= RequestMethod.GET)
	public ResponseEntity<?> getLastId(){
		System.out.println("consulto aquiasfdasegtawe ");
		return new ResponseEntity<Integer>(prodService.getLastId(),HttpStatus.OK);

	}


}

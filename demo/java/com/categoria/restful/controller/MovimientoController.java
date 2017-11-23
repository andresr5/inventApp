package com.categoria.restful.controller;

import java.util.ArrayList;
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

import com.example.DTO.Movimiento;
import com.example.DTO.Producto;
import com.example.repository.MovimientoService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/movimiento")
public class MovimientoController {

	   @Autowired
	   MovimientoService movService;
	   
	   
	   @RequestMapping(value="/{id}", method=RequestMethod.GET)
		public ResponseEntity<?> getMovimiento(@PathVariable("id") int id){
			Movimiento movimiento = movService.getById(id);
			if(movimiento == null) {
				return new ResponseEntity<Movimiento>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<Movimiento>(movimiento,HttpStatus.OK);
			}
		}

		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<?>getAllMovimientos(){
			List<Movimiento> movimientos = movService.getAll();
			if(movimientos.isEmpty()) {
				return new ResponseEntity<String>("No movimientos ",HttpStatus.NO_CONTENT);
			}else{
				return new ResponseEntity<List<Movimiento>>(movimientos,HttpStatus.OK);
			}
		} 
	   
	   

		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Movimiento> addMovimiento(@RequestBody Movimiento movimiento){
			System.out.println("movimiento get producto  ---->>>>");
			System.out.println(movimiento);
			
			System.out.println("movimiento get producto  ---->>>>");
			System.out.println(movimiento.getProducto());
			System.out.println("movimiento get tipomovimiento ---->>>>");
			System.out.println(movimiento.getTipoMovimiento());
			movService.save(movimiento);
			return new ResponseEntity<Movimiento>(movimiento,HttpStatus.CREATED);
		}

		@RequestMapping(value="/{id}",  method=RequestMethod.PUT)
		public ResponseEntity<?> updateMovimiento(@PathVariable("id") int id,@RequestBody Movimiento movimiento){
			Movimiento movimientoActual = movService.getById(id); 
			if(movimientoActual == null) {
				return new ResponseEntity<String>("No se encontro el movimiento con id: "+id,
						HttpStatus.NOT_FOUND);
			}
			System.out.println("Producto to update ----------------------->");
			System.out.println(movimiento);
			movimientoActual.setCantidad(movimiento.getCantidad());
			movimientoActual.setProducto(movimiento.getProducto());
			movimientoActual.setFecha(movimiento.getFecha());

			movService.save(movimientoActual);

			return new ResponseEntity<Movimiento>(movimientoActual,HttpStatus.OK);
		}

   
		@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteMovimiento(@PathVariable("id") int id) {

			Movimiento movimiento = movService.getById(id);
			if (movimiento == null) {
				return new ResponseEntity<String>("No existe un producto con ese Id",
						HttpStatus.NOT_FOUND);
			}
			movService.delete(id);
			return new ResponseEntity<Movimiento>(HttpStatus.NO_CONTENT);
		}

		@RequestMapping(value="/getLastId", method= RequestMethod.GET)
		public ResponseEntity<?> getLastId(){
			return new ResponseEntity<Integer>(movService.getLastId(),HttpStatus.OK);
		}
		
		@RequestMapping(value="/getProductosCantidad", method= RequestMethod.GET)
		public ResponseEntity<?>getRepProductosPrecioCantidadUnitario(){
			return new ResponseEntity<List<Movimiento>>(movService.getRepProductosPrecioCantidadUnitario(),HttpStatus.OK);
		}
		
		@RequestMapping(value="/getProductosCantidad", method= RequestMethod.GET)
		public ResponseEntity<?>get(){
			return new ResponseEntity<List<Movimiento>>(movService.getProductosCantidad(),HttpStatus.OK);
		}
		
	   
	
}

package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.DTO.Movimiento;

	
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>{
	
	
	@Query("SELECT MAX(m.id) FROM Movimiento m")
	int getLastId();
	
    
	// Reporte Cantidad Productos precio unitario precio
	@Query("SELECT c.nombre, sum(m.cantidad), sum(m.cantidad) * (p.precio) FROM Movimiento m JOIN m.producto p JOIN p.categoria c GROUP BY c.nombre")
	List<Movimiento> getRepProductosPrecioCantidadUnitario();
    
    
    // Reporte cantidad Productos Categoria Valor	
	@Query("SELECT p.nombre as nombre, SUM(m.cantidad),p.precio, SUM(m.cantidad) * p.precio as cantidad FROM Movimiento m JOIN m.producto p  GROUP BY p.nombre ")	
    List<Movimiento> getRepCategoriaProductosValor();
    
    
    //@Query(" SELECT p.nombre as nombre, SUM")
   
    
    


    
}

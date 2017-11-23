package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.DTO.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
	List<Producto>  findProductsByCategoria(int i);
    
    @Modifying
    @Transactional
    @Query(" Update Producto p SET p.categoria.id = :idCategoria where p.id = :idProducto  ")
    int updateCategoria(@Param("idProducto") int idProducto,@Param("idCategoria") int idCategoria);
    
    
    
    
    @Query(" SELECT MAX(id) FROM Producto p ")
    int getLastId();
    
   /* @Modifying
    @Transactional
    @Query(" insert into Producto p (id,codigo,descripcion,nombre,precio,tipo,categoria.id) VALUES (:id,:codigo,:descripcion,:nombre,:precio,:tipo,:categoriaId) ")
    int createProducto(@Param(":id") int id, @Param("codigo") String codigo, @Param("descripcion") String descripcion,
    		@Param("nombre") String nombre, @Param("precio")int precio , @Param("categoriaId") int categoriaId);*/
    
}

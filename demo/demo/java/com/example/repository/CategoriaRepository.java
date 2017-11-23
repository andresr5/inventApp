package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.DTO.Categoria;
import com.example.DTO.Producto;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
      List<Producto> getProductosById(int id);
      
      @Query(" SELECT MAX(c.id) FROM Categoria c ")
      int getLastId();
}

package com.example.DTO;



import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@Table(name="categoria")
@NamedQueries(
@NamedQuery(name="Categoria.getProductosById", query=" Select p FROM Producto p, Categoria c where p.categoria = c.id and c.id = ?1")
		)
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name="descripcion")
	private String descripcion;
   
	@Column(name="nombre",unique=true,nullable=false)
	private String nombre;

	//bi-directional many-to-one association to Producto
	@OneToMany
	@JoinColumn(name="categoria_id",referencedColumnName="id")
	@JsonIgnore
	private List<Producto> productos;

	public Categoria() {
	}
	
	public Categoria(int id, String nombre, String descripcion) {
		
		this.nombre = nombre;
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public Categoria (String nombre, String descripcion) {
		
		this.nombre = nombre;
		this.descripcion = descripcion;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setCategoria(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setCategoria(null);

		return producto;
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(" Id: ").append(this.id).append(" Nombre: ").append(this.nombre).append(" Descripcion:  ").append(this.descripcion);
		return sb.toString();
	}
	

}
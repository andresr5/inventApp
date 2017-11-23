package com.example.DTO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.Query;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@Table(name="producto")
@NamedQueries({
	@NamedQuery(name="Producto.findProductsByCategoria",
			query=" Select p FROM Producto p, Categoria c where p.categoria = c.id and c.id = ?1 ")
	
})	

public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String codigo;

	private String descripcion;

	private String nombre;

	private double precio;

	//bi-directional many-to-one association to Movimiento
	@JsonIgnore
	@OneToMany(mappedBy="producto")
	private List<Movimiento> movimientos;

	//bi-directional many-to-one association to Categoria
	@ManyToOne(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
	@JoinColumn(name="categoria_id")
	private Categoria categoria;

	public Producto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<Movimiento> getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Movimiento addMovimiento(Movimiento movimiento) {
		getMovimientos().add(movimiento);
		movimiento.setProducto(this);

		return movimiento;
	}

	public Movimiento removeMovimiento(Movimiento movimiento) {
		getMovimientos().remove(movimiento);
		movimiento.setProducto(null);

		return movimiento;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(" Id: ").append(this.id).append(" Nombre: ").append(this.nombre).append(" Codigo:  ").append(this.codigo).append(" Precio: ").append(this.precio)
		.append(" Descripcion:  ").append(this.descripcion).append(" Categoria de Producto: ").append(this.categoria);
		return sb.toString();
	}


}
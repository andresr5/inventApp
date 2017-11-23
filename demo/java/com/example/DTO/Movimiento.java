package com.example.DTO;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the movimiento database table.
 * 
 */
@Entity
public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String cantidad;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String user;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="fk_producto")
	private Producto producto;

	//bi-directional many-to-one association to TipoMovimiento
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="fk_tipo_movimiento")
	private TipoMovimiento tipoMovimiento;

	public Movimiento() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public TipoMovimiento getTipoMovimiento() {
		return this.tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(" Id: ").append(this.id).append(" Fecha: ").append(this.fecha).append(" Cantidad: ").append(this.cantidad).append(" producto: ")
		.append(this.producto).append(" Tipo Movimiento: ").append(this.tipoMovimiento);
		return sb.toString();
	}

	

}
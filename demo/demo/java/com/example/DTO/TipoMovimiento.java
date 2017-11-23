package com.example.DTO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_movimiento database table.
 * 
 */
@Entity
@Table(name="tipo_movimiento")
public class TipoMovimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private int id;

	private String nombre;

	//bi-directional many-to-one association to Movimiento
	@OneToMany(mappedBy="tipoMovimiento")
	private List<Movimiento> movimientos;

	public TipoMovimiento() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Movimiento> getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Movimiento addMovimiento(Movimiento movimiento) {
		getMovimientos().add(movimiento);
		movimiento.setTipoMovimiento(this);

		return movimiento;
	}

	public Movimiento removeMovimiento(Movimiento movimiento) {
		getMovimientos().remove(movimiento);
		movimiento.setTipoMovimiento(null);

		return movimiento;
	}
	
	@Override
	public String toString() {
	    StringBuffer sb = new StringBuffer();
	    sb.append(" Id: ").append(this.id).append(" Nombre: ").append(this.nombre);
	    return sb.toString();
	}

}
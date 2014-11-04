package com.lighthouse.vortex.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the asistencia database table.
 * 
 */
@Embeddable
public class AsistenciaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int cliente;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int evento;

	public AsistenciaPK() {
	}
	
	/**
	 * @param cliente
	 * @param evento
	 */
	public AsistenciaPK(int cliente, int evento) {
		super();
		this.cliente = cliente;
		this.evento = evento;
	}

	public int getCliente() {
		return this.cliente;
	}
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}
	public int getEvento() {
		return this.evento;
	}
	public void setEvento(int evento) {
		this.evento = evento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AsistenciaPK)) {
			return false;
		}
		AsistenciaPK castOther = (AsistenciaPK)other;
		return 
			(this.cliente == castOther.cliente)
			&& (this.evento == castOther.evento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cliente;
		hash = hash * prime + this.evento;
		
		return hash;
	}
}
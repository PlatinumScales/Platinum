package com.lighthouse.vortex.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the credencial database table.
 * 
 */
@Entity
@Table(name="credencial")
@NamedQuery(name="Credencial.findAll", query="SELECT c FROM Credencial c")
public class Credencial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int idJugador;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cliente", nullable=false)
	private Cliente clienteBean;

	//bi-directional many-to-one association to Juego
	@ManyToOne
	@JoinColumn(name="juego", nullable=false)
	private Juego juegoBean;

	public Credencial() {
	}

	public Credencial(int idJugador, Cliente clienteBean, Juego juegoBean) {
		super();
		this.idJugador = idJugador;
		this.clienteBean = clienteBean;
		this.juegoBean = juegoBean;
	}

	public int getIdJugador() {
		return this.idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public Cliente getClienteBean() {
		return this.clienteBean;
	}

	public void setClienteBean(Cliente clienteBean) {
		this.clienteBean = clienteBean;
	}

	public Juego getJuegoBean() {
		return this.juegoBean;
	}

	public void setJuegoBean(Juego juegoBean) {
		this.juegoBean = juegoBean;
	}

}
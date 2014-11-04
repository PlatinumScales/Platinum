package com.lighthouse.vortex.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the asistencia database table.
 * 
 */
@Entity
@Table(name = "asistencia")
@NamedQueries(value = {
		@NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a"),
		@NamedQuery(name = "Asistencia.find", query = "SELECT a FROM Asistencia a "
													+ "WHERE a.clienteBean.idCliente = :CustomerIDPrm "
													+ "AND a.eventoBean.idEvento = :eventIDPrm ")})
public class Asistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AsistenciaPK id;

	
	private int presente = 0;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="cliente", nullable=false, insertable=false, updatable=false)
	private Cliente clienteBean;

	//bi-directional many-to-one association to Evento
	@ManyToOne (cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="evento", nullable=false, insertable=false, updatable=false)
	private Evento eventoBean;

	public Asistencia() {
	}
	
	
	
	/**
	 * @param cliente
	 * @param evento
	 */
	public Asistencia(Cliente cliente, Evento evento) {
		super();
		this.id = new AsistenciaPK(cliente.getIdCliente(), evento.getIdEvento());
		this.clienteBean = cliente;
		this.eventoBean = evento;
	}



	public AsistenciaPK getId() {
		return this.id;
	}

	public void setId(AsistenciaPK id) {
		this.id = id;
	}

	public int getPresente() {
		return this.presente;
	}

	public void setPresente(int presente) {
		this.presente = presente;
	}

	public Cliente getClienteBean() {
		return this.clienteBean;
	}

	public void setClienteBean(Cliente clienteBean) {
		this.clienteBean = clienteBean;
	}

	public Evento getEventoBean() {
		return this.eventoBean;
	}

	public void setEventoBean(Evento eventoBean) {
		this.eventoBean = eventoBean;
	}

}
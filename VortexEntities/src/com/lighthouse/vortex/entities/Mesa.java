package com.lighthouse.vortex.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the mesas database table.
 * 
 */
@Entity
@Table(name="mesas")
@NamedQuery(name="Mesa.findAll", query="SELECT m FROM Mesa m")
public class Mesa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idMesas;

	@Column(length=45)
	private String disponible;

	//bi-directional many-to-one association to Evento
	@OneToMany(mappedBy="mesa")
	private List<Evento> eventos;

	public Mesa() {
	}

	public int getIdMesa() {
		return this.idMesas;
	}

	public void setIdMesa(int idMesas) {
		this.idMesas = idMesas;
	}

	public String getDisponible() {
		return this.disponible;
	}

	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Evento addEvento(Evento evento) {
		getEventos().add(evento);
		evento.setMesa(this);

		return evento;
	}

	public Evento removeEvento(Evento evento) {
		getEventos().remove(evento);
		evento.setMesa(null);

		return evento;
	}

}
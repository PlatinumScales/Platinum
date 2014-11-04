package com.lighthouse.vortex.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the juego database table.
 * 
 */
@Entity
@Table(name = "juego")
@NamedQueries(value = {
		@NamedQuery(name = "Juego.findAll", query = "SELECT j FROM Juego j"),
		@NamedQuery(name = "Juego.findByName", query = "SELECT j FROM Juego j WHERE "
													 + "UPPER(j.nombre) LIKE :nombrePrm") })
public class Juego implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idJuego;

	@Column(nullable=false, length=45)
	private String nombre;

	//bi-directional many-to-one association to Credencial
	@OneToMany(mappedBy="juegoBean")
	private List<Credencial> credencials;

	public Juego() {
	}
	
	

	/**
	 * @param nombre
	 */
	public Juego(String nombre) {
		super();
		this.nombre = nombre;
	}

	public int getIdJuego() {
		return this.idJuego;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Credencial> getCredencials() {
		return this.credencials;
	}

	public void setCredencials(List<Credencial> credencials) {
		this.credencials = credencials;
	}

	public Credencial addCredencial(Credencial credencial) {
		getCredencials().add(credencial);
		credencial.setJuegoBean(this);

		return credencial;
	}

	public Credencial removeCredencial(Credencial credencial) {
		getCredencials().remove(credencial);
		credencial.setJuegoBean(null);

		return credencial;
	}

}
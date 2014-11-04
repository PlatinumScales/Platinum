package com.lighthouse.vortex.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the imagen database table.
 * 
 */
@Entity
@Table(name = "imagen")
@NamedQueries(value = {
		@NamedQuery(name = "Imagen.findAll", query = "SELECT i FROM Imagen i"),
		@NamedQuery(name = "Imagen.default", query = "SELECT i FROM Imagen i Where i.idImagen = 0"), 
		@NamedQuery(name = "Imagen.findByUrl", query = "SELECT i FROM Imagen i  Where i.url = :urlPrm" )		})
public class Imagen implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique = true, nullable = false)
	private int idImagen;

	@Column(nullable = false, length = 45)
	private String url;

	// bi-directional many-to-one association to Evento
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "imagenBean")
	private List<Evento> eventos;

	// bi-directional many-to-many association to Producto
	@ManyToMany
	@JoinTable(name = "productos_has_imagenes", joinColumns = { @JoinColumn(name = "imagen", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "producto", nullable = false) })
	private List<Producto> productos;

	public Imagen() {
	}

	/**
	 * @param url
	 */
	public Imagen(String url) {
		super();
		this.url = url;
	}

	public int getIdImagen() {
		return this.idImagen;
	}

	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Evento addEvento(Evento evento) {
		getEventos().add(evento);
		evento.setImagenBean(this);

		return evento;
	}

	public Evento removeEvento(Evento evento) {
		getEventos().remove(evento);
		evento.setImagenBean(null);

		return evento;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}
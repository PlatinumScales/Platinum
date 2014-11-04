package com.lighthouse.vortex.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@Table(name = "categoria")
@NamedQueries(value = {
		@NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
		@NamedQuery(name = "Categoria.findByNombre", query = "SELECT c FROM Categoria c WHERE UPPER(c.nombre) = UPPER(:nombrePrm)")})
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int idCategoria;

	@Column(length = 45)
	private String nombre;

	// bi-directional many-to-one association to Producto
	@OneToMany(mappedBy = "categoriaBean")
	private List<Producto> productos;

	public Categoria() {
	}

	/**
	 * @param nombre
	 */
	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
	}

	public int getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
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
		producto.setCategoriaBean(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setCategoriaBean(null);

		return producto;
	}

}
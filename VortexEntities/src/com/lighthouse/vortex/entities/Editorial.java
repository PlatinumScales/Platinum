package com.lighthouse.vortex.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the editorial database table.
 * 
 */
@Entity
@Table(name="editorial")
@NamedQuery(name="Editorial.findAll", query="SELECT e FROM Editorial e")
public class Editorial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idEditorial;

	@Column(nullable=false, length=45)
	private String nombre;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="editorialBean")
	private List<Producto> productos;

	public Editorial() {
	}
	
	public Editorial(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public int getIdEditorial() {
		return this.idEditorial;
	}

	public void setIdEditorial(int idEditorial) {
		this.idEditorial = idEditorial;
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
		producto.setEditorialBean(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setEditorialBean(null);

		return producto;
	}

}
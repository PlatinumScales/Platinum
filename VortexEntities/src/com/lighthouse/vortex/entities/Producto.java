package com.lighthouse.vortex.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@Table(name="producto")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idProductos;

	private int cantidad;

	@Column(length=45)
	private String descripcion;

	@Column(nullable=false, length=45)
	private String nombre;

	@Column(length=45)
	private String precio;

	//bi-directional many-to-many association to Imagen
	@ManyToMany(mappedBy="productos")
	private List<Imagen> imagens;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="categoria", nullable=false)
	private Categoria categoriaBean;

	//bi-directional many-to-one association to Editorial
	@ManyToOne
	@JoinColumn(name="editorial", nullable=false)
	private Editorial editorialBean;

	public Producto() {
	}
	

	/**
	 * @param nombre
	 * @param cantidad
	 * @param descripcion
	 * @param precio
	 * @param imagens
	 * @param categoriaBean
	 * @param editorialBean
	 */
	public Producto(String nombre, int cantidad, String descripcion,
			String precio, List<Imagen> imagens, Categoria categoria,
			Editorial editorial) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.precio = precio;
		this.imagens = imagens;
		this.categoriaBean = categoria;
		this.editorialBean = editorial;
	}


	public int getIdProductos() {
		return this.idProductos;
	}

	public void setIdProductos(int idProductos) {
		this.idProductos = idProductos;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrecio() {
		return this.precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public List<Imagen> getImagens() {
		return this.imagens;
	}

	public void setImagens(List<Imagen> imagens) {
		this.imagens = imagens;
	}

	public Categoria getCategoriaBean() {
		return this.categoriaBean;
	}

	public void setCategoriaBean(Categoria categoriaBean) {
		this.categoriaBean = categoriaBean;
	}

	public Editorial getEditorialBean() {
		return this.editorialBean;
	}

	public void setEditorialBean(Editorial editorialBean) {
		this.editorialBean = editorialBean;
	}

}
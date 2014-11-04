package com.lighthouse.vortex.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the evento database table.
 * 
 */
@Entity
@Table(name = "evento")
@NamedQueries(value = {
		@NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
		@NamedQuery(name = "Evento.findByID", query = "SELECT e FROM Evento e WHERE e.idEvento = :eventIDPrm"),
		@NamedQuery(name = "Evento.findByCustomer", query = "SELECT e FROM Evento e WHERE e.cliente.idCliente = :customerPrm"), 
		@NamedQuery(name = "Evento.findByDate", query = "SELECT e FROM Evento e WHERE e.fecha = :datePrm")})
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int idEvento;

	@Column(nullable = false)
	private int cupo;

	@Column(nullable = false, length = 200)
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date fecha;

	@Column(nullable = false)
	private int horaFin;

	@Column(nullable = false)
	private int horaInicio;

	@Column(nullable = false, length = 45)
	private String nombre;

	@Column(nullable = false)
	private int publico;

	// bi-directional many-to-one association to Asistencia
	@OneToMany(mappedBy = "eventoBean", cascade = CascadeType.MERGE)
	private List<Asistencia> asistencias;

	// bi-directional many-to-one association to Imagen
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "imagen", nullable = false)
	private Imagen imagenBean;

	// bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name = "creador")
	private Cliente cliente;

	//bi-directional many-to-one association to Mesa
	@ManyToOne
	@JoinColumn(name="mesa")
	private Mesa mesa;
	
	
	public Evento() {
	}

	/**
	 * @param nombre
	 * @param descripcion
	 * @param publico
	 * @param fecha
	 * @param horaInicio
	 * @param horaFin
	 * @param cupo
	 */
	public Evento(String nombre, String descripcion, Date fecha,
			int horaInicio, int horaFin, int cupo) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.cupo = cupo;
	}

	/**
	 * @param nombre
	 * @param descripcion
	 * @param fecha
	 * @param horaInicio
	 * @param horaFin
	 * @param cupo
	 * @param publico
	 * @param creador
	 * @param mesa
	 */
	public Evento(String nombre, String descripcion, Date fecha,
			int horaInicio, int horaFin, int cupo, int publico, Cliente creador, Mesa mesa) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.cupo = cupo;
		this.publico = publico;
		this.cliente = creador;
		this.mesa = mesa;
	}

	public int getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public int getCupo() {
		return this.cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(int horaFin) {
		this.horaFin = horaFin;
	}

	public int getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPublico() {
		return this.publico;
	}

	public void setPublico(int publico) {
		this.publico = publico;
	}

	public List<Asistencia> getAsistencias() {
		return this.asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public Asistencia addAsistencia(Asistencia asistencia) {
		getAsistencias().add(asistencia);
		asistencia.setEventoBean(this);

		return asistencia;
	}

	public Asistencia removeAsistencia(Asistencia asistencia) {
		getAsistencias().remove(asistencia);
		asistencia.setEventoBean(null);

		return asistencia;
	}

	public Imagen getImagenBean() {
		return this.imagenBean;
	}

	public void setImagenBean(Imagen imagenBean) {
		this.imagenBean = imagenBean;
	}

	public Mesa getMesa() {
		return this.mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Cliente getCreador() {
		return this.cliente;
	}

	public void setCreador(Cliente cliente) {
		this.cliente = cliente;
	}

}
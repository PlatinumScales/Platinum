package com.lighthouse.vortex.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name="cliente")
@NamedQueries(value = {
		@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c"),
		@NamedQuery(name="Cliente.findByID", query="SELECT c FROM Cliente c WHERE c.idCliente = :idClientePrm"),
		@NamedQuery(name="Cliente.findByPlayerID", query="SELECT cli FROM Cliente cli, Credencial cre "
													   + "WHERE cli.idCliente = cre.clienteBean.idCliente "
													   + "AND cre.idJugador = :idJugadorPrm"),
		@NamedQuery(name="Cliente.findByName", query="SELECT c FROM Cliente c "
												   + "WHERE UPPER(c.nombre) LIKE :nombrePrm"),
		@NamedQuery(name="Cliente.findByEvent", query="SELECT c FROM Cliente c, Asistencia a "
												   + "WHERE c.idCliente = a.clienteBean.idCliente "
												   + "AND a.eventoBean.idEvento = :idEventoPrm"),
		})
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idCliente;

	private int exp;

	private int gold;

	@Column(length=45)
	private String nombre;

	//bi-directional many-to-one association to Asistencia
	@OneToMany(mappedBy="clienteBean")
	private List<Asistencia> asistencias;

	//bi-directional many-to-one association to Credencial
	@OneToMany(mappedBy="clienteBean")
	private List<Credencial> credencials;
	
	//bi-directional many-to-one association to Evento
	@OneToMany(mappedBy="cliente")
	private List<Evento> eventos;

	public Cliente() {
	}

	
	/**
	 * @param nombre
	 */
	public Cliente(String nombre) {
		super();
		this.exp = 0;
		this.gold = 0;
		this.nombre = nombre;
	}


	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getExp() {
		return this.exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getGold() {
		return this.gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Asistencia> getAsistencias() {
		return this.asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public Asistencia addAsistencia(Asistencia asistencia) {
		getAsistencias().add(asistencia);
		asistencia.setClienteBean(this);

		return asistencia;
	}

	public Asistencia removeAsistencia(Asistencia asistencia) {
		getAsistencias().remove(asistencia);
		asistencia.setClienteBean(null);

		return asistencia;
	}

	public List<Credencial> getCredencials() {
		return this.credencials;
	}

	public void setCredencials(List<Credencial> credencials) {
		this.credencials = credencials;
	}

	public Credencial addCredencial(Credencial credencial) {
		getCredencials().add(credencial);
		credencial.setClienteBean(this);

		return credencial;
	}

	public Credencial removeCredencial(Credencial credencial) {
		getCredencials().remove(credencial);
		credencial.setClienteBean(null);

		return credencial;
	}
	
	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Evento addEvento(Evento evento) {
		getEventos().add(evento);
		evento.setCreador(this);

		return evento;
	}

	public Evento removeEvento(Evento evento) {
		getEventos().remove(evento);
		evento.setCreador(null);

		return evento;
	}

}
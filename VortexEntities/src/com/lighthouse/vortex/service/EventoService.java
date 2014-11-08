package com.lighthouse.vortex.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import com.lighthouse.vortex.entities.Cliente;
import com.lighthouse.vortex.entities.Evento;

public class EventoService extends EntityService<Evento> implements
		Serializable {
	private static final long serialVersionUID = 1L;

	// @NamedQuery(name = "Evento.findByID", query =
	// "SELECT e FROM Evento e where e.idEvento = :eventIDPrm")
	public Evento findByID(int customerID) {
		try {
			TypedQuery<Evento> query = em.createNamedQuery("Evento.findByID",
					Evento.class);
			query.setParameter("eventIDPrm", customerID);
			return query.getSingleResult();
		} catch (Exception e) {
			javax.persistence.NoResultException nre = new NoResultException();
			e.addSuppressed(nre);
			return null;
		}
	}

	// @NamedQuery(name = "Evento.findByCustomer", query =
	// "SELECT e FROM Evento e WHERE e.cliente.idCliente = :customerPrm")
	public List<Evento> getCustomerReservations(Cliente c) {
		TypedQuery<Evento> query = em.createNamedQuery("Evento.findByCustomer",
				Evento.class);
		query.setParameter("customerPrm", c.getIdCliente());
		return query.getResultList();
	}

	// @NamedQuery(name = "Evento.findByDate", query =
	// "SELECT e FROM Evento e WHERE e.fecha = :datePrm")
	public List<Evento> findByDate(Date date) {
		
		TypedQuery<Evento> query = em.createNamedQuery("Evento.findByDate", Evento.class);
		query.setParameter("datePrm", date, TemporalType.DATE);
		return query.getResultList();
	}

	// @NamedQuery(name = "Evento.findComing", query =
	// "SELECT e FROM Evento e WHERE e.fecha >= :datePrm")
	public List<Evento> findComing() {
		Date date = new Date();
		TypedQuery<Evento> query = em.createNamedQuery("Evento.findComing", Evento.class);
		query.setParameter("datePrm", date, TemporalType.DATE);
		return query.getResultList();
	}

}

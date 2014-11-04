package com.lighthouse.vortex.service;

import java.io.Serializable;

import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.lighthouse.vortex.entities.Juego;

public class JuegoService extends EntityService<Juego> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Juego findByID(int idJuego) {
		try {
			TypedQuery<Juego> query = em.createNamedQuery("Juego.findID",Juego.class);
			query.setParameter("idJuegoPrm", idJuego);
			return query.getSingleResult();
		} catch (Exception e) {
			javax.persistence.NoResultException nre = new NoResultException();
			e.addSuppressed(nre);
			return null;
		}
	}
	
//	@NamedQuery(name = "Juego.findByName", query = "SELECT j FROM Juego j WHERE UPPER(j.nombre) LIKE :nombrePrm")
	public Juego findByName(String name){
		try {
			TypedQuery<Juego> query = em.createNamedQuery("Juego.findByName",Juego.class);
			query.setParameter("nombrePrm", "%" + name.toUpperCase() + "%");
			return query.getSingleResult();
		} catch (Exception e) {
			System.out.println(this.getClass().toString() + " not found");
			javax.persistence.NoResultException nre = new NoResultException();
			e.addSuppressed(nre);
			return null;
		}
		
	}

}

package com.lighthouse.vortex.service;

import java.io.Serializable;

import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.lighthouse.vortex.entities.Asistencia;



public class AsistenciaService extends EntityService<Asistencia> implements Serializable {
	private static final long serialVersionUID = 1L;
	
//	@NamedQuery(name = "Asistencia.find", query = "SELECT a FROM Asistencia a "
//			+ "WHERE a.clienteBean.idCliente = :CustomerIDPrm "
//			+ "AND a.eventoBean.idEvento = :eventIDPrm ")
	public Asistencia get( int customerID, int eventID){
		try {
			TypedQuery<Asistencia> query = em.createNamedQuery("Asistencia.find", Asistencia.class);
			query.setParameter("CustomerIDPrm", customerID);
			query.setParameter("eventIDPrm", eventID);
			return query.getSingleResult();
		} catch (Exception e) {
			javax.persistence.NoResultException nre = new NoResultException();
			e.addSuppressed(nre);
			return null;
		}	
	}
	
}

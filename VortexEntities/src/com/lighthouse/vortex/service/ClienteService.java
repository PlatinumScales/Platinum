package com.lighthouse.vortex.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.lighthouse.vortex.entities.Cliente;
import com.lighthouse.vortex.entities.Evento;


public class ClienteService extends EntityService<Cliente> implements Serializable {
	private static final long serialVersionUID = 1L;
	
//	@NamedQuery(name="Cliente.findByID", query="SELECT c FROM Cliente c WHERE c.idCliente = :idClientePrm"),
	public Cliente findByID(int customerID){
		try {
			TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findByID", Cliente.class);
			query.setParameter("idClientePrm",customerID );
			return query.getSingleResult();
		} catch (Exception e) {
			javax.persistence.NoResultException nre = new NoResultException();
			e.addSuppressed(nre);
			return null;
		}	
	}
	
//	@NamedQuery(name="Cliente.findByPlayerID", query="SELECT cli FROM Cliente cli, Credencial cre "
//			   + "WHERE cli.idCliente = cre.clienteBean.idCliente "
//			   + "AND cre.idJugador = :idJugadorPrm"),
	public Cliente findByPlayerID(int playerID){
		try {
			TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findByPlayerID", Cliente.class);
			query.setParameter("idJugadorPrm",playerID );
			return query.getSingleResult();
		} catch (Exception e) {
			javax.persistence.NoResultException nre = new NoResultException();
			e.addSuppressed(nre);
			return null;
		}	
	}
	
//	@NamedQuery(name="Cliente.findByName", query="SELECT c FROM Cliente c "
//			   + "WHERE UPPER(c.nombre) LIKE :nombrePrm")
	public List<Cliente> findByName(String name){
			TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findByName", Cliente.class);
			name = "%" + name.toUpperCase() + "%"; 
			query.setParameter("nombrePrm", name );
			return query.getResultList();
	}
	
//	@NamedQuery(name="Cliente.findByEvent", query="SELECT c FROM Cliente c, Asistencia a "
//			   + "WHERE c.idCliente = a.clienteBean.idCliente "
//			   + "AND a.eventoBean.idEvento = :idEventoPrm")
	public List<Cliente> getAttendance(int eventID){
		TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findByEvent", Cliente.class);
		query.setParameter("idEventoPrm", eventID);
		return query.getResultList();
	}
}

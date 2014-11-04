package com.lighthouse.vortex.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.lighthouse.vortex.entities.Categoria;


public class CategoriaService extends EntityService<Categoria> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
//	@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
	public List<Categoria> getCategories(){
		TypedQuery<Categoria> query = em.createNamedQuery("Categoria.findAll", Categoria.class);
		return query.getResultList();
	}
	
//	@NamedQuery(name = "Categoria.findByNombre", query = "SELECT c FROM Categoria c WHERE UPPER(c.nombre) = UPPER(:nombrePrm)")
	public Categoria getCategory(String name){
		try {
			TypedQuery<Categoria> query = em.createNamedQuery("Categoria.findByNombre", Categoria.class);
			query.setParameter("nombrePrm", name );
			return query.getSingleResult();
		} catch (Exception e) {
			javax.persistence.NoResultException nre = new NoResultException();
			e.addSuppressed(nre);
			return null;
		}
		
	}
 
}
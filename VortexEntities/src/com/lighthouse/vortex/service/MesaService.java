package com.lighthouse.vortex.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

import com.lighthouse.vortex.entities.Mesa;

public class MesaService extends EntityService<Mesa> implements Serializable {
	private static final long serialVersionUID = 1L;
	
//	@NamedQuery(name="Mesa.findAll", query="SELECT m FROM Mesa m")
	public List<Mesa> findAll(){
		TypedQuery<Mesa> query = em.createNamedQuery("Mesa.findAll", Mesa.class);
		return query.getResultList();
		
	}
	
}
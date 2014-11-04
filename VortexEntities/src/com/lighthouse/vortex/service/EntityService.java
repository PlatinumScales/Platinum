package com.lighthouse.vortex.service;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public abstract class EntityService<E> implements Serializable {
	private static final long serialVersionUID = 1L;
	protected EntityManagerFactory emf;
	protected EntityManager em;
		
	public void  startEntityManager(String persistanceUnit){
		emf = Persistence.createEntityManagerFactory(persistanceUnit);
		em = emf.createEntityManager();
	}
	
	public void closeEntityManager(){
		em.close();
		emf.close();
	}
	
	public void insert(E item){
		em.getTransaction().begin();
		em.persist(item);
		em.flush();
		em.getTransaction().commit();
	}
	
	public void update(E item){
		em.getTransaction().begin();
		em.merge(item);
		em.flush();
		em.getTransaction().commit();
	}
	
	public void delete(E item){
		em.getTransaction().begin();
		em.remove(item);
		em.flush();
		em.getTransaction().commit();
	}

	public EntityManager getEm() {
		return em;
	}


	
}
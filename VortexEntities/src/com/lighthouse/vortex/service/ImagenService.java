package com.lighthouse.vortex.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.lighthouse.vortex.entities.Imagen;

public class ImagenService extends EntityService<Imagen> implements Serializable {
	private static final long serialVersionUID = 1L;
	
//	@NamedQuery(name = "Imagen.findAll", query = "SELECT i FROM Imagen i")
	public List<Imagen> getImagenes(){
		TypedQuery<Imagen> query = em.createNamedQuery("Imagen.findAll", Imagen.class);
		return query.getResultList();
	}
	
//	@NamedQuery(name="Imagen.Default", query="SELECT i FROM Imagen i Where i.idImagen = 0") 
	public Imagen getDefaultImage(){
		try {
			TypedQuery<Imagen> query = em.createNamedQuery("Imagen.default", Imagen.class);
			System.out.println("************************************");
			return query.getSingleResult();
		} catch (Exception e) {
			
			javax.persistence.NoResultException nre = new NoResultException();
			e.addSuppressed(nre);
			return null;
		}
		
	}
	
//	@NamedQuery(name = "Imagen.findByUrl", query = "SELECT i FROM Imagen i  Where i.url = :urlPrm" )
	public Imagen getImage(String url){
		try {
			TypedQuery<Imagen> query = em.createNamedQuery("Imagen.findByUrl", Imagen.class);
			query.setParameter("urlPrm",url );
			return query.getSingleResult();
		} catch (Exception e) {
			javax.persistence.NoResultException nre = new NoResultException();
			e.addSuppressed(nre);
			return null;
		} 
	}
	
}
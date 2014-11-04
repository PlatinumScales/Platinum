package com.lighthouse.vortex.control;

import java.util.List;

import com.lighthouse.vortex.entities.Categoria;
import com.lighthouse.vortex.service.CategoriaService;


public class ProductManagemet {
	static String persistanceUnit = "VortexEntities";
	
	public List<Categoria> getCategories(){
		try {
			CategoriaService cs = new CategoriaService();
			cs.startEntityManager(persistanceUnit);
			return cs.getCategories();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void addCategory(String name ){
		CategoriaService cats = new CategoriaService();
		Categoria cat = new Categoria(name);
		cats.startEntityManager(persistanceUnit);
		cats.insert(cat);
	}
	 
	public Categoria getCategory(String name){
		try {  
			CategoriaService cs = new CategoriaService();
			cs.startEntityManager(persistanceUnit);
			return cs.getCategory(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

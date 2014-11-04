package com.lighthouse.vortex.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lighthouse.vortex.control.ProductManagemet;
import com.lighthouse.vortex.entities.Categoria;


//http://localhost:8080/VortexResources/mobile/resources
@Path("/resources")
public class Resources {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getSomething(){
		return "resources";
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTest(){
		return "HELLO_ IM OK!";
	}
	
	@GET
	@Path("/categories")
	@Produces(MediaType.TEXT_XML)
	public String getCategories(){
		String xmlOut = "<Catalog>";
		ProductManagemet pm = new ProductManagemet();
		for (Categoria cat: pm.getCategories() ) {
			xmlOut += "<Category>" + cat.getNombre() +  "</Category>" ;
		}
		xmlOut = xmlOut + "</Catalog>";
		return xmlOut;
	}
	
	
	
	
	
}
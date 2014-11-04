package com.lighthouse.vortex.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lighthouse.vortex.control.EventManagement;
import com.lighthouse.vortex.entities.Evento;

//http://localhost:8080/VortexResources/Events
@Path("/Events")
public class EventResources {
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTest(){
		return "HELLO IM IN Events!";
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public String getAll(){
		EventManagement em = new EventManagement();
		for (Evento e : em.getComing()) {
			
		}
		return "asd";
	}
	
}






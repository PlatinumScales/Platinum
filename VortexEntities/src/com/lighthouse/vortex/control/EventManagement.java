package com.lighthouse.vortex.control;

import java.util.Date;
import java.util.List;

import com.lighthouse.vortex.entities.Asistencia;
import com.lighthouse.vortex.entities.Cliente;
import com.lighthouse.vortex.entities.Evento;
import com.lighthouse.vortex.entities.Imagen;
import com.lighthouse.vortex.entities.Mesa;
import com.lighthouse.vortex.service.AsistenciaService;
import com.lighthouse.vortex.service.ClienteService;
import com.lighthouse.vortex.service.EventoService;
import com.lighthouse.vortex.service.ImagenService;
import com.lighthouse.vortex.service.MesaService;

public class EventManagement {
	static final String persistanceUnit = "VortexEntities";
	static final int maxReservations = 14 ;
	

	//Creates a Vortex Event
	public boolean createVortexEvent(String nombre, String descripcion,
			Date fecha, int horaInicio, int horaFin, int cupo, Imagen imagen) {
		try {
			EventoService es = new EventoService();
			es.startEntityManager(persistanceUnit);

			ImagenService is = new ImagenService();
			is.startEntityManager(persistanceUnit);

			Evento ev = new Evento(nombre, descripcion, fecha, horaInicio,
					horaFin, cupo);

			if (imagen == null) {
				imagen = is.getDefaultImage();
				imagen.setIdImagen(is.getDefaultImage().getIdImagen());
			}

			ev.setImagenBean(imagen);
			es.update(ev);
			es.closeEntityManager();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//Creates a Customer Event
	public boolean customerReservation(int idCliente, String nombre, 
			String descripcion, int publico, Date fecha, int horaInicio,
			int horaFin, int cupo, String imagenUrl) {

		ClienteService cs = new ClienteService();
		cs.startEntityManager(persistanceUnit);
		Cliente cliente = cs.findByID(idCliente);

		EventoService es = new EventoService();
		es.startEntityManager(persistanceUnit);
		
		Mesa table = checkAvailability(fecha, horaInicio, horaFin);

		if (es.getCustomerReservations(cliente).size() < maxReservations && table != null) {
			System.out.println("in inner if");
			Evento evento = new Evento(nombre, descripcion, fecha, horaInicio,
					horaFin, cupo, publico, cliente, table);

			ImagenService is = new ImagenService();
			is.startEntityManager(persistanceUnit);
			if (imagenUrl == null) { // No image url provided
				evento.setImagenBean(is.getDefaultImage());
			} else { // Image URl provided
				if (is.getImage(imagenUrl) == null) { // No image found on DB
														// with that URL
					Imagen imagen = new Imagen(imagenUrl);
					imagen.setIdImagen(is.getImagenes().size() + 1);
					is.insert(imagen);
					evento.setImagenBean(imagen);

				} else { // Image Found on DB
					evento.setImagenBean(is.getImage(imagenUrl));
				}
			}

			AsistenciaService as = new AsistenciaService();
			as.startEntityManager(persistanceUnit);
			
			cliente.addEvento(evento);
			
			es.insert(evento);
			es.getEm().refresh(evento);

			Asistencia asistencia = new Asistencia(cliente, evento);
			
			evento.addAsistencia(asistencia);
			cliente.addAsistencia(asistencia);

			as.update(asistencia);

			as.closeEntityManager();
			is.closeEntityManager();
			cs.closeEntityManager();
			es.closeEntityManager();

			return true;
		} else {

			return false;
		}

	}

	//User reserves seat on a event 
	public boolean reserveAttendance(int customerID, int eventID){
		
		ClienteService cs = new ClienteService();
		cs.startEntityManager(persistanceUnit);
		Cliente cliente = cs.findByID(customerID);
		
		EventoService es = new EventoService();
		es.startEntityManager(persistanceUnit);
		
		Evento evento = es.findByID(eventID);
		
		if (cs.getAttendance(eventID).size()<evento.getCupo()) {
			Asistencia asistencia = new Asistencia(cliente, evento);
			evento.addAsistencia(asistencia);
			cliente.addAsistencia(asistencia);
			
			AsistenciaService as = new AsistenciaService();
			as.startEntityManager(persistanceUnit);
			
			as.update(asistencia);
			
			as.closeEntityManager();
			cs.closeEntityManager();
			es.closeEntityManager();
			
			return true;
		} else {
			return false;
		}
	}
	
	//Returns the first table available for a reservation, null if there is none
	public Mesa checkAvailability(Date date, int startTime,  int endTime){
		
		MesaService ms = new MesaService();
		ms.startEntityManager(persistanceUnit);
		List<Mesa> mesas= ms.findAll();
		
		EventoService es = new EventoService();
		es.startEntityManager(persistanceUnit);
		List<Evento> eventos = es.findByDate(date);
		
		boolean rejected;
		
		for (Evento e : eventos ) {
			rejected = false;
			
			if (e.getCreador()!= null) {
				
				if (startTime>=e.getHoraInicio()&& startTime<e.getHoraFin()) {
					rejected = true;
				}
				if (endTime>e.getHoraInicio()&& endTime<=e.getHoraFin()) {
					rejected = true;
				}
				
				if (startTime>=e.getHoraInicio()&& endTime<=e.getHoraFin()) {
					rejected = true;
				}
				
				if (rejected) {
					System.out.println("Removiendo mesa ID " + e.getMesa().getIdMesa());
					for (Mesa m : mesas) {
						if (m.getIdMesa()== e.getMesa().getIdMesa()) {
							mesas.remove(m);
							break;
						}
					}
				}
			}
		}
		es.closeEntityManager();
		ms.closeEntityManager();
		
		if (mesas.isEmpty()) {
			return null;
		} else {
			return mesas.get(0);
			
		}
	}
	
	//Manager use: Confirms a customer attended the event reservation not necessary 
	public boolean confirmAttendance(int customerID, int eventID){
		AsistenciaService as = new AsistenciaService();
		as.startEntityManager(persistanceUnit);
		Asistencia asistencia = as.get(customerID, eventID);
		
		if (asistencia!= null) {
			asistencia.setPresente(1);
			as.update(asistencia);
		} else {
			ClienteService cs = new ClienteService();
			cs.startEntityManager(persistanceUnit);
			Cliente cliente = cs.findByID(customerID);
			
			EventoService es = new EventoService();
			es.startEntityManager(persistanceUnit);
			
			Evento evento = es.findByID(eventID);
			
			asistencia = new Asistencia(cliente, evento);
			asistencia.setPresente(1);
			evento.addAsistencia(asistencia);
			cliente.addAsistencia(asistencia);
			as.update(asistencia);
		}
		return true;
	}
	
	//Returns coming events (from Today)
	public List<Evento> getComing(){
		EventoService es = new EventoService();
		es.startEntityManager(persistanceUnit);
		return es.findComing();
	}
}

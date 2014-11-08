package com.lighthouse.vortex.resources;

import java.io.InputStream;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.xml.sax.InputSource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lighthouse.vortex.control.EventManagement;
import com.lighthouse.vortex.entities.Evento;

//http://localhost:8080/VortexResources/Events
@Path("/Events")
public class EventResources {

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTest() {
		return "HELLO IM IN Events!";
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public String getAll() {
		String ouput = "<Calendar>";
		EventManagement em = new EventManagement();
		for (Evento e : em.getComing()) {
			ouput = ouput + "" + "<Evento>" + "<idEvento>" + e.getIdEvento()
					+ "</idEvento>" + "<nombre>" + e.getNombre() + "</nombre>"
					+ "<descripcion>" + e.getDescripcion() + "</descripcion>"
					+ "<fecha>" + e.getFecha() + "</fecha>" + "<horaInicio>"
					+ e.getHoraInicio() + "</horaInicio>" + "<horaFin>"
					+ e.getHoraFin() + "</horaFin>" + "<cupo>"
					+ e.getAsistencias().size() + "/" + e.getCupo() + "</cupo>"
					+ "<imagen>" + e.getImagenBean().getUrl() + "</imagen>"
					+ "<mesa>" + e.getMesa().getIdMesa() + "</mesa>"
					+ "</Evento>";
		}
		return ouput + "</Calendar>";
	}

	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	public Void createCustomerReservation(String input) {
		XPath xpath = XPathFactory.newInstance().newXPath();
		try {
			InputSource source = new InputSource(new StringReader(input));
			Document document =(Document) xpath.evaluate("/", source, XPathConstants.NODE);
			
			String exp = "/Eventos/Evento[1]";	

		

		
			EventManagement em = new EventManagement();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			// em.customerReservation(idCliente, nombre, descripcion, publico,
			// fecha, horaInicio, horaFin, cupo, imagenUrl)
			System.out.println(xpath.evaluate(exp+"/nombre", document));

			// em.customerReservation(
			// Integer.parseInt(nodeList.item(0).getFirstChild().getNodeValue())
			// ,
			// nodeList.item(1).getFirstChild().getNodeValue(),
			// nodeList.item(2).getFirstChild().getNodeValue(),
			// Integer.parseInt(nodeList.item(3).getFirstChild().getNodeValue()),
			// formatter.parse(nodeList.item(4).getFirstChild().getNodeValue()),
			// Integer.parseInt(nodeList.item(5).getFirstChild().getNodeValue()),
			// Integer.parseInt(nodeList.item(6).getFirstChild().getNodeValue()),
			// Integer.parseInt(nodeList.item(7).getFirstChild().getNodeValue()),
			// nodeList.item(8).getFirstChild().getNodeValue());
			//
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

}

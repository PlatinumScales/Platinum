package com.lighthouse.vortex.tester;

import java.util.Date;
import java.util.List;

import javassist.expr.NewArray;

import com.lighthouse.vortex.control.CustomerManagement;
import com.lighthouse.vortex.control.EventManagement;
import com.lighthouse.vortex.control.ProductManagemet;
import com.lighthouse.vortex.entities.Categoria;
import com.lighthouse.vortex.entities.Cliente;
import com.lighthouse.vortex.entities.Credencial;
import com.lighthouse.vortex.entities.Evento;
import com.lighthouse.vortex.entities.Juego;
import com.lighthouse.vortex.entities.Producto;
import com.lighthouse.vortex.service.CategoriaService;
import com.lighthouse.vortex.service.ClienteService;
import com.lighthouse.vortex.service.CredencialService;
import com.lighthouse.vortex.service.EventoService;
import com.lighthouse.vortex.service.JuegoService;

public class EntityTester {
	static String persistanceUnit = "VortexEntities";

	public static void main(String[] args) {
//		ClienteService cs = new ClienteService();
//		cs.startEntityManager(persistanceUnit);
//		Cliente c = new Cliente();
//		c.setNombre("Pedro");
//		cs.insert(c);
		
//		JuegoService  js = new JuegoService();
//		js.startEntityManager(persistanceUnit);
//		Juego j = new Juego();
//		j.setNombre("Magic");
//		js.insert(j);
		
//		ClienteService cs = new ClienteService();
//		cs.startEntityManager(persistanceUnit);
//		Cliente c = cs.findByID(1);
//		System.out.println(c.getNombre());
//		
//		JuegoService  js = new JuegoService();
//		js.startEntityManager(persistanceUnit);
//		Juego j = js.findByID(1);
//		System.out.println(j.getNombre());
//		
//		CredencialService cds = new CredencialService();
//		cds.startEntityManager(persistanceUnit);
//		Credencial cre = new Credencial();
//		cre.setIdJugador(1200481562);
//		cre.setCliente(c);
//		cre.setJuego(j);
//		
//		j.addCredencial(cre);	
//		c.addCredencial(cre);
//		cs.update(c);
//		js.update(j);
//		cds.insert(cre);
		
//		ProductManagemet pm = new ProductManagemet();
//		for (Categoria cat: pm.getCategories()) {
//			System.out.println(cat.getNombre());
//			for (Producto producto : cat.getProductos()) {
//				System.out.println("--"+ producto.getNombre());
//				
//			}
//		}
		
//		ProductManagemet pm = new ProductManagemet();
//		Categoria cat = pm.getCategory("tcg");
//		System.out.println(cat.getNombre());
//		for (Producto producto : cat.getProductos()) {
//			System.out.println("--" + producto.getNombre());
//
//		}
//		
		EventManagement em = new EventManagement();
//		System.out.println(em.customerReservation(2, "Reservacion de Prueba6", "Reservacion de Prueba6", 0, new Date(), 4, 9, 1, "una pagina ahi"));
		em.confirmAttendance(3, 6);
		
		
//		CustomerManagement cm = new CustomerManagement();
//		System.out.println(cm.registerCustomer(3, "Marcos"));
//		System.out.println(cm.getCustomerById(1).getNombre());
//		System.out.println(cm.getCustomer("Marcos").get(0).getIdCliente() + "found") ;
		
//		System.out.println(cm.addPlayerID(1, 100, "Magic"));
//		cm.addGold(1, 100);
		
		/*TODO:
		 *
		 */
		
	}

}

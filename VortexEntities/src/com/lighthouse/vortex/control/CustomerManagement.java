package com.lighthouse.vortex.control;

import java.util.List;

import com.lighthouse.vortex.entities.Cliente;
import com.lighthouse.vortex.entities.Credencial;
import com.lighthouse.vortex.entities.Juego;
import com.lighthouse.vortex.service.ClienteService;
import com.lighthouse.vortex.service.CredencialService;
import com.lighthouse.vortex.service.JuegoService;

public class CustomerManagement {
	static final String persistanceUnit = "VortexEntities";
	
	public boolean registerCustomer(int id, String name){
		try {
			ClienteService cs = new ClienteService();
			cs.startEntityManager(persistanceUnit);
			if (cs.findByID(id)== null) {
				Cliente c = new Cliente(name);
				c.setIdCliente(id);
				cs.update(c);
				cs.closeEntityManager();
				return true;
			} else {
				cs.closeEntityManager();
				return false;				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;			
		}
	}
	
	public Cliente getCustomerById(int id) {
		try {
			ClienteService cs = new ClienteService();
			cs.startEntityManager(persistanceUnit);
			return cs.findByID(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Cliente getCustomerByPlayerID(int id){
		try {
			ClienteService cs = new ClienteService();
			cs.startEntityManager(persistanceUnit);
			return cs.findByPlayerID(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean addPlayerID(int customerID, int playerID, String game){
		ClienteService cs = new ClienteService();
		cs.startEntityManager(persistanceUnit);
		Cliente c = cs.findByID(customerID);
		
		
		JuegoService js = new JuegoService();
		js.startEntityManager(persistanceUnit);
		Juego j = js.findByName(game);
		
		CredencialService cres = new CredencialService();
		cres.startEntityManager(persistanceUnit);
		
		Credencial cre = new Credencial(playerID, c, j) ;
		c.addCredencial(cre);
		j.addCredencial(cre);
		cres.update(cre);
		
		cs.closeEntityManager();
		js.closeEntityManager();
		cres.closeEntityManager();
		return true;
	}
	
	public List<Cliente> getCustomer(String name){
		try {
			ClienteService cs = new ClienteService();
			cs.startEntityManager(persistanceUnit);
			return cs.findByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void addGold(int customerID, int gold){
		ClienteService cs = new ClienteService();
		cs.startEntityManager(persistanceUnit);
		Cliente c = cs.findByID(customerID);
		c.setGold(c.getGold()+gold);
		cs.update(c);
		cs.closeEntityManager();
	}
	
	public boolean addUseGold(int customerID, int gold){
		ClienteService cs = new ClienteService();
		cs.startEntityManager(persistanceUnit);
		Cliente c = cs.findByID(customerID);
		if (c.getGold()>gold) {
			c.setGold(c.getGold()-gold);
			cs.update(c);
			cs.closeEntityManager();
			return true;
		} else {
			return false;
		}		
	}
	
	public void addExp(int customerID, int exp){
		ClienteService cs = new ClienteService();
		cs.startEntityManager(persistanceUnit);
		Cliente c = cs.findByID(customerID);
		c.setExp(c.getExp()+exp);
		cs.update(c);
		cs.closeEntityManager();
	}
	

}

package com.biblioteca.dao;

import java.util.ArrayList;
import java.util.List;

import com.biblioteca.model.Cliente;

public class DaoClienteImpl implements Idao<Cliente>{

	private List<Cliente> clientes = new ArrayList<>();
	
	@Override
	public List<Cliente> getAll() {
		return this.clientes;
	}
	
	@Override
	public void save(Cliente t) {
		clientes.add(t);
	}

	public Cliente getCliente(String nombre) {
		for(Cliente cliente : this.clientes) {
			if(cliente.getNombre().equals(nombre)) {
				return cliente;
			}
		}
		return null;
	}
	
	public void removeCliente(String nombre) {
		for(Cliente cliente : this.clientes) {
			if(cliente.getNombre().equals(nombre)) {
				clientes.remove(cliente);
				return;
			}
		}
		return;
	}

}

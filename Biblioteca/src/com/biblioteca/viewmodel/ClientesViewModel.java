package com.biblioteca.viewmodel;

import java.util.ArrayList;
import java.util.List;

import com.biblioteca.model.Cliente;

public class ClientesViewModel {
	
	private static ClientesViewModel instance = null;
	private List<Cliente> clientes;
	
	
	protected ClientesViewModel() {
		clientes = new ArrayList<>();
	}
	
	public static ClientesViewModel getInstance() {
		if(instance == null) {
			instance = new ClientesViewModel();
		}
		return instance;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}

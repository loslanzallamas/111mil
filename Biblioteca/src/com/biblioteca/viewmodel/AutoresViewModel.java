package com.biblioteca.viewmodel;

import java.util.ArrayList;
import java.util.List;

import com.biblioteca.model.Autor;
import com.biblioteca.repositories.AutorRepository;

public class AutoresViewModel {
	
	private static AutoresViewModel instance = null;
	private List<Autor> autores;
	private static AutorRepository autorRepository;
	
	
	protected AutoresViewModel() {
		//autores = new ArrayList<>();
		autorRepository= new AutorRepository();
		autores = autorRepository.getAutores();
	}
	
	public static AutoresViewModel getInstance() {
		if(instance == null) {
			instance = new AutoresViewModel();
		}
		return instance;
	}

	public List<Autor> getAutores() {
		return autores;
	}
	
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	
	public Autor getAutor(String nombre) {
		for(Autor autor : this.autores) {
			if(autor.getNombre().equals(nombre)) {
				return autor;
			}
		}
		return null;
	}
	
	public void removeAutor(String nombre) {
		for(Autor autor : this.autores) {
			if(autor.getNombre().equals(nombre)) {
				autores.remove(autor);
				return;
			}
		}
		return;
	}
	
	public void deleteAutor(Integer id) {
		
	}
	
	public void updateAutor(Autor autor) {
		
	}

}

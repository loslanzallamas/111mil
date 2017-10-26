package com.biblioteca.dao;

import java.util.ArrayList;
import java.util.List;

import com.biblioteca.model.Libro;

public class DaoLibroImpl implements Idao<Libro>{

	private List<Libro> libros = new ArrayList<>();
	
	@Override
	public List<Libro> getAll() {
		return this.libros;
	}
	
	@Override
	public void save(Libro t) {
		libros.add(t);
	}

	public Libro getLibro(String nombre) {
		for(Libro libro : this.libros) {
			if(libro.getNombre().equals(nombre)) {
				return libro;
			}
		}
		return null;
	}
	
	public void removeLibro(String nombre) {
		for(Libro libro : this.libros) {
			if(libro.getNombre().equals(nombre)) {
				libros.remove(libro);
				return;
			}
		}
		return;
	}

}

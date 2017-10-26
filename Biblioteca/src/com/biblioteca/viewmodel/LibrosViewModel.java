package com.biblioteca.viewmodel;

import java.util.ArrayList;
import java.util.List;

import com.biblioteca.model.Libro;

public class LibrosViewModel {
	
	private static LibrosViewModel instance = null;
	private List<Libro> libros;
	
	
	protected LibrosViewModel() {
		libros = new ArrayList<>();
	}
	
	public static LibrosViewModel getInstance() {
		if(instance == null) {
			instance = new LibrosViewModel();
		}
		return instance;
	}

	public List<Libro> getLibros() {
		return libros;
	}
	
	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

}

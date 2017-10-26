package com.biblioteca.gui;

import java.util.List;

import com.biblioteca.model.Autor;
import com.biblioteca.viewmodel.AutoresViewModel;

public class consoleBiblioteca {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AutoresViewModel autoresViewModel = AutoresViewModel.getInstance();
		
		List<Autor> autores = autoresViewModel.getAutores();
		
		for (Autor autor : autores) {
			System.out.println(autor.getNombre() + ", " + autor.getNacionalidad() + ", " + autor.getFechaNac());
		}
	}

}

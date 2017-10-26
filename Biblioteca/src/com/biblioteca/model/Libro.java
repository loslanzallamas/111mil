package com.biblioteca.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.biblioteca.exception.NoHayCopiaDisponibleException;

public class Libro {

	private Integer id;
	private String nombre;
	private TipoLibro tipo;
	private String editorial;
	private int anio;
	private Autor autor;
	private List<Copia> copias = new ArrayList<>();
	
	public Libro() {
	}
	
	public Libro(String nombre, TipoLibro tipo, String editorial, int anio, Autor autor, List<Copia> copias) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.editorial = editorial;
		this.anio = anio;
		this.autor = autor;
		this.copias = copias;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TipoLibro getTipo() {
		return tipo;
	}
	public void setTipo(TipoLibro tipo) {
		this.tipo = tipo;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public List<Copia> getCopias() {
		return copias;
	}
	public void setCopias(List<Copia> copias) {
		this.copias = copias;
	}
	
	/*
	public void addCopia(Copia copia) {
		if(copias.contains(copia)){
			System.out.println("Esta copia ya esta cargada");
			return;
		}
		this.copias.add(copia);
		copia.setLibro(this);
	}
	*/
	
	public boolean addCopia(Copia copia) {
		Optional<Copia> optCopia = copias.stream().filter(c -> c.getIdentificador().equals(copia.getIdentificador())).findAny();
		if(!optCopia.isPresent()) {
			copia.setLibro(this);
			copias.add(copia);
			return true;
		}
		return false;
	}
	
	/*
	public boolean removeCopia(Copia copia) {
		if(copias.contains(copia)) {
			return copias.remove(copia);
		}else {
			return false;
		}
	}
	*/
	
	public boolean removeCopia(Copia copia) {
		boolean resultado = copias.remove(copia);
		copia.setLibro(null);
		return resultado;
	}
	
	public boolean removeCopia(Integer id) {
		Optional<Copia> optCopia = copias.stream().filter(c->c.getId().equals(id)).findFirst();
		if(optCopia.isPresent()) {
			return removeCopia(optCopia.get());
		}
		return false;
	}
	
	public Copia prestarCopia() throws NoHayCopiaDisponibleException{
		for(Copia c: this.copias) {
			if(c.getEstado().equals(Estado.En_biblioteca)) {
				c.setEstado(Estado.Prestado);
				return c;
			}
		}
		return null;
	}
	
	public void devolverCopia(Copia copia) {
		for(Copia c: this.copias) {
			if(c.getIdentificador().equals(copia.getIdentificador())) {
				c.setEstado(Estado.En_biblioteca);
				return;
			}
		}
		System.out.println("El identificador no corresponde a una copia del libro");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Id: " + id + "Título: " + nombre;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		Libro libro = (Libro) obj;
		return Objects.equals(id, libro.id);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 35;
	}
	
}

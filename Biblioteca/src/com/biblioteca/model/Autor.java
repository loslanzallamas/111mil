package com.biblioteca.model;

import java.time.LocalDate;
import java.util.Objects;

public class Autor {

	private Integer id;
	private String nombre;
	private String nacionalidad;
	private LocalDate fechaNac;
	
	public Autor() {
	}
	
	public Autor(String nombre, String nacionalidad, LocalDate fechaNac) {
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.fechaNac = fechaNac;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public LocalDate getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	@Override
	public String toString() {
		return nombre;
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
		Autor autor = (Autor) obj;
		return Objects.equals(id, autor.id);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 34;
	}
	
}

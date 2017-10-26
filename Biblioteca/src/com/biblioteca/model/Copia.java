package com.biblioteca.model;

public class Copia {
	private Integer id;
	private Estado estado;
	private String identificador;
	private Libro libro;

	public Copia() {
	
	}
	
	public Copia(Estado estado, String identificador) {
		super();
		this.estado = estado;
		this.identificador = identificador;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}

}

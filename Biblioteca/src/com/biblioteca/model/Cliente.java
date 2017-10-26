package com.biblioteca.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private Integer id;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;
	private Multa multa;
	private List<Prestamo> prestamos = new ArrayList<>();
	
	public Cliente() {
	
	}
	
	public Cliente(String nombre, String apellido, String direccion, String telefono) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Multa getMulta() {
		return multa;
	}
	
	public void setMulta(Multa multa) {
		this.multa = multa;
	}
	
	public List<Prestamo> getPrestamos() {
		return prestamos;
	}
	
	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	
	public void addPrestamo(Prestamo prestamo){
		this.prestamos.add(prestamo);
	}
	
	public void devolverPrestamo(Prestamo prestamo, LocalDate fechaDeDevolucion) {
		prestamos.get(prestamos.indexOf(prestamo)).setFechaDevolucion(fechaDeDevolucion);
	}
	
	public int prestamosActivos() {
		int activos = 0;
		for(Prestamo prestamo : this.prestamos) {
			if(prestamo.getFechaDevolucion() != null) {
				activos++;
			}
		}
		return activos;
	}
	
	/*
	public void devolverCopia(Copia copia, LocalDate fechaDevolucion) {
		for(Prestamo prestamo :this.prestamos) {
			if(prestamo.getCopia().equals(copia)) {
				prestamo.setFechaDevolucion(fechaDevolucion);;
				return;
			}
		}
	}*/
	
	
}

package com.biblioteca.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import com.biblioteca.exception.NoHayCopiaDisponibleException;
import com.biblioteca.exception.TieneMultaException;

public class Biblioteca {

	private List<Cliente> clientes = new ArrayList<>();
	private List<Libro> libros = new ArrayList<>();
	private List<Autor> autores = new ArrayList<>();
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public List<Libro> getLibros() {
		return libros;
	}
	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	
	public void addCliente(Cliente cliente) {
		this.clientes.add(cliente);
	}
	public void removeCliente(Cliente cliente) {
		this.clientes.remove(cliente);
	}
	
	public void addLibro(Libro libro) {
		this.libros.add(libro);
	}
	public void removeLibro(Libro libro) {
		this.libros.remove(libro);
	}
	
	public void prestarLibro(Cliente cliente, Libro libro) throws TieneMultaException, NoHayCopiaDisponibleException, NoSePuedenPrestarMasLibrosException{
		if(!(cliente.prestamosActivos() < 3)) {
		if(!tieneMulta(cliente) ) {
			Copia copia = libro.prestarCopia();
			if(copia != null) {
				Prestamo prestamo = new Prestamo(LocalDate.now(), LocalDate.now().plusDays(14), copia, null);
				cliente.addPrestamo(prestamo);
				System.out.println("Libro prestado");
			}else {
				throw new NoHayCopiaDisponibleException("No hay copias disponibles del libro " + libro.getNombre());
			}
			
		}else {
			throw new TieneMultaException("El cliente " + cliente.getApellido() + " tiene una multa vigente");
		}
		}else {
			throw new NoSePuedenPrestarMasLibrosException("El cliente " + cliente.getApellido() + " ya tiene tres préstamos");
		}
	}
	
	public void devolverLibro(Cliente cliente, Copia copia) {
		Prestamo devolPrestamo = null;
		for(Prestamo prestamo :cliente.getPrestamos()) {
			if(prestamo.getCopia().equals(copia) && prestamo.getFechaDevolucion() == null) {
				devolPrestamo = prestamo;
				break;
			}
		}
		if(devolPrestamo != null) {
			int diasDeMulta = diasMulta(devolPrestamo);
			if(cliente.getMulta() != null) {
				cliente.getMulta().setFechaFin(cliente.getMulta().getFechaFin().plusDays(diasDeMulta));
			}else {
				cliente.setMulta(new Multa(LocalDate.now().plusDays(1), LocalDate.now().plusDays(diasDeMulta + 1)));
			}
			cliente.devolverPrestamo(devolPrestamo, LocalDate.now());
			copia.getLibro().devolverCopia(copia);
		}
		
	}
	
	private boolean tieneMulta(Cliente cliente) {
		if(cliente.getMulta() != null && cliente.getMulta().getFechaFin().isAfter(LocalDate.now())) {
			return true;
		}
		return false;
	}
	
	private int diasMulta(Prestamo prestamo) {
		int diasDeMulta = 0;
		LocalDate fechaLimiteDev = prestamo.getFechaDevolucion().plusDays(30);
		if(fechaLimiteDev.isAfter(LocalDate.now())) {
			Period period = Period.between(fechaLimiteDev, LocalDate.now());
			diasDeMulta = period.getDays()*3;
			return diasDeMulta;
		}
		return diasDeMulta;
	}
	
}

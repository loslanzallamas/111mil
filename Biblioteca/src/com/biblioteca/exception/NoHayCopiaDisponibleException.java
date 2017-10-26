package com.biblioteca.exception;

public class NoHayCopiaDisponibleException extends Exception {

	/**
	 *  Lanza una excepcion si no hay copias disponibles 
	 */
	private static final long serialVersionUID = 1L;

	public NoHayCopiaDisponibleException(String message) {
		super(message);
	}
}

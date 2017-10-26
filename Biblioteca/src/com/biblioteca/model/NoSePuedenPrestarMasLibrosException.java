package com.biblioteca.model;

public class NoSePuedenPrestarMasLibrosException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSePuedenPrestarMasLibrosException(String msg) {
		super(msg);
	}
	
}

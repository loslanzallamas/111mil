package com.biblioteca.utils;

public class Utiles {

	public static boolean validarEntero(String valor) {
		try {
			if(!(Integer.parseInt(valor) > 0)) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}
	
}

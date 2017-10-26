package com.biblioteca.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		Autor a1 = new Autor("Autor 1", "argentino", LocalDate.of(1965, Month.JULY, 9));
		Autor a2 = new Autor("Autor 2", "checo", LocalDate.of(1932, Month.APRIL, 5));
		
		Libro l1 = new Libro("Ficciones", TipoLibro.novela, "Editorial 1", 2005, a1, null);
		Libro l2 = new Libro("Ensayos", TipoLibro.ensayo, "Editorial 2", 2005, a2, null);
		Libro l3 = new Libro("Teatro", TipoLibro.teatro, "Editorial 3", 2010, a1, null);
		
		Random random = new Random(System.nanoTime());
	
		int nroAutores = random.nextInt(9)+1;
		for(int i = 0; i < nroAutores; i++) {
			//Autor a = new Autor("Autor" + 1, "Nacionalidad " + 1, LocalDate(random.nextInt(217)+1800,));
		}
		
		
		for(int i = 0; i < 4; i++ ) {
			Copia copia = new Copia(Estado.En_biblioteca, String.valueOf(random.nextInt(1000000000)));
			
		}
		
		l1.addCopia(new Copia(Estado.En_biblioteca, "c1"));
		l1.addCopia(new Copia(Estado.En_biblioteca, "c2"));
		l1.addCopia(new Copia(Estado.En_biblioteca, "c3"));
		
		l2.addCopia(new Copia(Estado.En_reparacion, "c1"));
		l2.addCopia(new Copia(Estado.En_reparacion, "c2"));
		
		Cliente c1 = new Cliente("Cliente 1", "Uno", "Calle 123", "4556465456");
		Cliente c2 = new Cliente("Cliente 2", "Dos", "Calle 1234", "448725276");
		
	}
	
}

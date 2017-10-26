package com.biblioteca.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.biblioteca.gui.Biblioteca;
import com.biblioteca.model.Autor;

public class DaoAutorImpl implements Idao<Autor>{

	private List<Autor> autores = new ArrayList<>();
	
	@Override
	public List<Autor> getAll() {
		
		Session session =  Biblioteca.getSessionFactory().openSession();
		session.beginTransaction();
		
		CriteriaQuery<Autor> query = session.getCriteriaBuilder().createQuery(Autor.class);
		query.select(query.from(Autor.class));
		autores = session.createQuery(query).list();
		session.getTransaction().commit();
		session.close();
		return this.autores;
	}
	
	@Override
	public void save(Autor t) {
		Session session =  Biblioteca.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(t);
		session.getTransaction().commit();
		session.close();
	}

	public Autor getAutor(Integer id) {
		Session session = Biblioteca.getSessionFactory().openSession();
		session.beginTransaction();
		Autor autor = session.get(Autor.class, id);
		session.getTransaction().commit();
		session.close();
		return autor;
	}
	
	public void removeAutor(Integer id) {
		Session session = Biblioteca.getSessionFactory().openSession();
		session.beginTransaction();
		Autor autor = session.get(Autor.class, id);
		session.remove(autor);
		session.getTransaction().commit();
		session.close();
	}

}

package com.biblioteca.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.biblioteca.gui.Biblioteca;

public class DaoImpl<T> implements IGenDAO<T>{
	
    final Class<T> typeParameterClass;

    public DaoImpl(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }
	
	public List<T> getAll() {
		
		Session session =  Biblioteca.getSessionFactory().openSession();
		session.beginTransaction();
		
		CriteriaQuery<T> query = session.getCriteriaBuilder().createQuery(typeParameterClass);
		query.select(query.from(typeParameterClass));
		List<T> entities = session.createQuery(query).list();
		session.getTransaction().commit();
		session.close();
		return entities;
	}
	
	public void save(T t) {
		Session session =  Biblioteca.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(t);
		session.getTransaction().commit();
		session.close();
	}

	public T getById(Integer id) {
		Session session = Biblioteca.getSessionFactory().openSession();
		session.beginTransaction();
		T entity = session.get(typeParameterClass, id);
		session.getTransaction().commit();
		session.close();
		return entity;
	}
	
	public void remove(Integer id) {
		Session session = Biblioteca.getSessionFactory().openSession();
		session.beginTransaction();
		T entity = session.get(typeParameterClass, id);
		session.remove(entity);
		session.getTransaction().commit();
		session.close();
	}

}

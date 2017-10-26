package com.biblioteca.dao;

import java.util.List;

public interface IGenDAO<T> {

	public List<T> getAll();
	
	public void save(T t);
	
	public T getById(Integer id);
	
	public void remove(Integer id);
}

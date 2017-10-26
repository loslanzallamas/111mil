package com.biblioteca.dao;

import java.util.List;

public interface Idao<T> {
	
	public List<T> getAll();
	
	public void save(T t);
	
	
}

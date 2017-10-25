package br.com.inatel.services;

import java.util.List;

public interface GenericDAO <T> {
	public void save(T entity);
	public void remove (T entity);
	public void update(T entity);
	public List<T> all(T entity);
	public T getById(T entity, Long id);	
}

package br.com.inatel.services;

import java.util.List;

public abstract class GenericDAOImp<T> implements GenericDAO<T>{
	private DataSource<T> ds = null;

	public GenericDAOImp() {
		ds = (DataSource<T>) DataSource.getInstance();
	}

	@Override
	public void save(T entity) {
		ds.add(entity);
	}

	@Override
	public void remove(T entity) {
		ds.remove(entity);
	}

	@Override
	public void update(T entity) {
		ds.update(entity);
	}

	@Override
	public List<T> all(T entity) {
		return ds.getAll(entity);
	}

	@Override
	public T getById(T entity, Long id) {
		return ds.getElementById(entity, id);
	}

}

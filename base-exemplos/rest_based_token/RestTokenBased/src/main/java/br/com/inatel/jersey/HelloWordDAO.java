package br.com.inatel.jersey;

import java.util.ArrayList;
import java.util.List;

public class HelloWordDAO {
	
	private static HelloWordDAO helloWord = null;
	private ArrayList<HelloEntity> items;
	private Long id = 0L;
	
	public static HelloWordDAO getInstance() {
		if (helloWord == null) {
			helloWord = new HelloWordDAO();
		}
		return helloWord;
	}
	
	public HelloWordDAO() {
		items = new ArrayList<HelloEntity>();
		for (int i = 1; i <= 10; i++) {
			items.add(new HelloEntity(new Long(i), "Item:" + i));
		}
		id = new Long(items.size()+1);
	}
	
	public HelloEntity getItemById(Long id) {
		HelloEntity item = null;
		for (HelloEntity e : items) {
			if (e.getId().equals(id)) {
				item = e;
				break;
			}
		}
		return item;
	}
	
	public HelloEntity createItem(HelloEntity entity) {
		entity.setId(id);
		items.add(entity);
		id ++;
		return entity;
	}
	
	public HelloEntity updateItem (HelloEntity entityToUpdate) {
		int index = items.indexOf(getItemById(entityToUpdate.getId()));
		items.remove(index);
		items.add(index, entityToUpdate);
		
		return entityToUpdate;
	}
	
	public List getItems() {
		return items;
	}

	public boolean delete(Long id){
		HelloEntity entity = getItemById(id);
		if (entity != null) {
			items.remove(entity);
			return true;
		} else {
			return false;
		}
	}
}

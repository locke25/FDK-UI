package com.jcim.fdk.dao;

import java.util.Date;
import java.util.List;

import com.jcim.fdk.model.EntityBase;

public interface AbstractDao<E extends  EntityBase> {

    /**
     * Factory method for entities.
     * 
     * @return new non-persistent entity.
     */
	public E createEntity();
	
	public E retrieve(long id);
	
	public List<E> retrieveAll();
	
	public List<E> retrieveAllSinceDate(Date date);
	
	public long countEntitys();
	
//	public void update(E entity);
	
	public void delete(long id);	
	
//	public void delete(E entity);
}

package com.pcelta.foobar.helper;
import com.pcelta.foobar.dao.AbstractDAO;


public class DAOHelperTest extends AbstractDAO{

	public void save(Object object) {
		this.persist(object);
	}
	
	public void remove(Object object) {
		super.remove(object);
	}
}

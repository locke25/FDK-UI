package com.jcim.fdk.dao;


import com.jcim.fdk.model.Test;
import com.jcim.fdk.model.Test.TestEnum;


public interface TestDao extends AbstractDao<Test> 
{

	public long create(String testString, boolean testBoolean, TestEnum testEnum);	
	
	public void update(long testId, String testString, boolean testBoolean, TestEnum testEnum);
	
	public Test retrieve(long id);
	
	public void delete(long id);	
	
}

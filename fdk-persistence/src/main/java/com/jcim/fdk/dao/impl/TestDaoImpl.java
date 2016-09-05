package com.jcim.fdk.dao.impl;

import org.springframework.context.ApplicationContext;

import com.jcim.fdk.dao.TestDao;
import com.jcim.fdk.model.Test;
import com.jcim.fdk.model.Test.TestEnum;

public class TestDaoImpl extends AbstractDaoImpl<Test> implements TestDao {

	public TestDaoImpl(ApplicationContext context) {
		super(context);		
	}

	@Override
	public Test createEntity() 
	{
		return new Test();
	}
	
	@Override
	public long create(String testString, boolean testBoolean, TestEnum testEnum){
		
		Test t = createEntity();
		
		t.setTestBoolean(testBoolean);
		t.setTestEnum(testEnum);
		t.setTestString(testString);
		
		long id = super.create(t);
		return id;
	}
	
	public void update(long testId, String testString, boolean testBoolean, TestEnum testEnum) {
		
		Test t = retrieve(testId);		
		
		t.setTestBoolean(testBoolean);
		t.setTestEnum(testEnum);
		t.setTestString(testString);
		
		super.update(t);		
	}	
	
	
}

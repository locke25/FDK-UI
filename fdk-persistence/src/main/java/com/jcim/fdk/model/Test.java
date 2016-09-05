package com.jcim.fdk.model;

import javax.persistence.Entity;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "Test")
public class Test extends EntityBase 
{	

	public enum TestEnum {
		TEST1,
		TEST2,
		TEST3
	}
	
	private String testString;
	
	private boolean testBoolean;
	
	private TestEnum testEnum;

	public String getTestString() {
		return testString;
	}

	public void setTestString(String testString) {
		this.testString = testString;
	}

	public boolean isTestBoolean() {
		return testBoolean;
	}

	public void setTestBoolean(boolean testBoolean) {
		this.testBoolean = testBoolean;
	}

	public TestEnum getTestEnum() {
		return testEnum;
	}

	public void setTestEnum(TestEnum testEnum) {
		this.testEnum = testEnum;
	}

}

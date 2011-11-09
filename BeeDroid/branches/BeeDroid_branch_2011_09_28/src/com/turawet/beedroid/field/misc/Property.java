package com.turawet.beedroid.field.misc;

public class Property
{
	private String	name;
	private String	value;
	
	public Property(String name, String value)
	{
		this.name = name;
		this.value = value;
	}
	
	public Property()
	{
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Object getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
}

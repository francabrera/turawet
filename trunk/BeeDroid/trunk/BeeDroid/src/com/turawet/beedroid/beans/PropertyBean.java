package com.turawet.beedroid.beans;

import java.io.Writer;

/**
 * @class PropertyBean: Represents a PropertyBean
 * 
 * @param id
 *           : The id of the option
 * @param name
 *           : The name of the option
 * @param value
 *           : The value of the option described by the name
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @autor Romén Rodríguez Gil
 * 
 */
public class PropertyBean extends BaseBean
{
	
	/**
	 * @uml.property name="name"
	 */
	private String	name;
	/**
	 * @uml.property name="value"
	 */
	private String	value;
	
	/* Getters && Setters */
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	/* Other methods */
	@Override
	public String toString()
	{
		return name;
	}
	
	@Override
	public void toXml(Writer writer)
	{
	}
	
}
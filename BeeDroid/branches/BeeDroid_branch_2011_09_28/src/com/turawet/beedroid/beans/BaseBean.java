package com.turawet.beedroid.beans;

import java.io.IOException;
import java.io.Writer;

import org.xmlpull.v1.XmlSerializer;

/**
 * @class BaseBean: Base class form the XML elements
 * 
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public abstract class BaseBean
{
	/*
	 * Field group instance bena has no attribute id (has the groupId of the form
	 * group
	 */
	protected int				id;
	
	protected XmlSerializer	serializer;
	
	public BaseBean()
	{
		id = -1;
	}
	
	/* Getters y setters */
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	protected abstract void toXml(Writer writer) throws IllegalArgumentException, IllegalStateException, IOException;
}

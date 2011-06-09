package com.turawet.beedroid.beans;

import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import android.test.suitebuilder.annotation.SmallTest;

/**
 * @class FormBean: Represents a form
 * 
 * @param id: The id of the form
 * @param name: The name of the form
 * @param version: The version of the form
 * 
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public class FormBean extends BaseBean {

	/**
	 * @uml.property  name="name"
	 */
	public String name;
	/**
	 * @uml.property  name="version"
	 */
	public int version;
	
	
	/* Methods */
	
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

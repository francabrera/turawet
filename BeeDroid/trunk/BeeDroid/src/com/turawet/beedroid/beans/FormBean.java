package com.turawet.beedroid.beans;

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

	public Integer id;
	public String name;
	public Integer version;
	
	/*Getters & setters*/
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	
	/* Methods */
	
	@Override
	public String toString()
	{
		return name;
	}
	
	@Override
	public String toXml()
	{
		return null;
	}
}

package com.turawet.beedroid.beans;

/**
 * @class BaseBean: Base class form the XML elements
 * 
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @autor Romén Rodríguez Gil
 * 
 */
public abstract class BaseBean {
	/* Field group instance bena has no attribute id (has the groupId of the form group*/
	public int id;
	
	
	/*Getters y setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
	abstract public String toXml();
}

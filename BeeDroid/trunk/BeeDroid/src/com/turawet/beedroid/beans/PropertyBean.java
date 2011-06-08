package com.turawet.beedroid.beans;

/**
 * @class PropertyBean: Represents a PropertyBean
 * 
 * @param id: The id of the option
 * @param name: The name of the option
 * @param value: The value of the option described by the name
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @autor Romén Rodríguez Gil
 * 
 */
public class PropertyBean extends BaseBean {

	/**
	 * @uml.property  name="id"
	 */
	public int id;
	/**
	 * @uml.property  name="name"
	 */
	public String name;
	/**
	 * @uml.property  name="value"
	 */
	public int value;

		
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public String toXml() {
		return null;
	}

}

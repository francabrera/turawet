package com.turawet.beedroid.beans;

import java.io.Writer;

/**
 * @class FieldOptionBean: Represents a FieldOptionBean
 * 
 * @param id: The id of the option
 * @param label: The label of the option
 * @param value: The value of the option described by the label
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public class FieldOptionBean extends BaseBean {
	
	/**
	 * @uml.property  name="label"
	 */
	public String label;
	/**
	 * @uml.property  name="value"
	 */
	public int value;
	
	
	@Override
	public String toString() {
		return label;
	}
	
	@Override
	public void toXml(Writer writer) {
	}

}

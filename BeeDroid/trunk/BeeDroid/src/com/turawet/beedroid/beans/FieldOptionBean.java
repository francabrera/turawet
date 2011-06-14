package com.turawet.beedroid.beans;

import java.io.Writer;

/**
 * @class FieldOptionBean: Represents a FieldOptionBean
 * 
 * @param id
 *           : The id of the option
 * @param label
 *           : The label of the option
 * @param value
 *           : The value of the option described by the label
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public class FieldOptionBean extends BaseBean
{
	
	/**
	 * @uml.property name="label"
	 */
	private String	label;
	/**
	 * @uml.property name="value"
	 */
	private String	value;
	
	@Override
	public String toString()
	{
		return label;
	}
	
	/**
	 * @return the label
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label)
	{
		this.label = label;
	}

	/**
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	@Override
	public void toXml(Writer writer)
	{
	}
	
}

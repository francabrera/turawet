package com.turawet.beedroid.parser;

import java.util.HashMap;

/**
 * 
 */

/**
 * @author nicopernas
 * 
 */
public class FieldContainer
{
	/**
	 *
	 */
	public static final String			ID			= "id";
	public static final String			REQUIRED	= "required";
	public static final String			LABEL		= "label";
	public static final String			TYPE		= "type";
	public static final String			UNDEF		= "UNDEF";
	/**
	 * 
	 */
	private HashMap<String, String>	hash;
	
	/**
	 * 	
	 */
	public FieldContainer()
	{
		hash = new HashMap<String, String>();
		hash.put(ID, UNDEF);
		hash.put(REQUIRED, String.valueOf(false));
		hash.put(LABEL, UNDEF);
		hash.put(TYPE, UNDEF);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getRequiredToStr()
	{
		return hash.get(REQUIRED);
	}
	
	public boolean getRequired()
	{
		return Boolean.valueOf(hash.get(REQUIRED));
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLabel()
	{
		return hash.get(LABEL);
	}
	
	/**
	 * @param label
	 */
	public void setLabel(String label)
	{
		hash.put(LABEL, label);
	}
	
	/**
	 * @param id
	 */
	public void setId(String id)
	{
		hash.put(ID, id);
	}
	
	/**
	 * @param type
	 */
	public void setType(String type)
	{
		hash.put(TYPE, type);
	}
	
	/**
	 * @param required
	 */
	public void setRequired(boolean required)
	{
		hash.put(REQUIRED, String.valueOf(required));
	}
	
	/**
	 * @param propertyName
	 * @param propertyValue
	 */
	public void addProperty(String propertyName, String propertyValue)
	{
		hash.put(propertyName, propertyValue);
	}
	
	/**
	 * @return
	 */
	public String getType()
	{
		return hash.get(TYPE);
	}
	
	/**
	 * @return
	 */
	public String getId()
	{
		return hash.get(ID);
	}
	
	@Override
	public String toString()
	{
		return hash.toString();
	}
}

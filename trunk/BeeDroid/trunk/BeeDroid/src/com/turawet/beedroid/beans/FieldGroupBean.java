package com.turawet.beedroid.beans;

import java.util.Iterator;
import java.util.List;

/**
 * @class FieldGroupBean: Represents a FieldGroupBean
 * 
 * @param id: The id of the FormField
 * @param label: The label of the FormField
 * @param list: Whether this is a gallery of this group elements or not
 * @param required: Whether this field must be filled or not
 * @param fields: Fields contained in the group
 * @param properties: Properties of the current group
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public class FieldGroupBean extends BaseBean {
	
	// Should we create a groupInstance and a Group (one for description and other for instantiation) 
	/**
	 * @uml.property  name="label"
	 */
	public String label;
	/**
	 * @uml.property  name="required"
	 */
	public boolean required;
	/**
	 * @uml.property  name="list"
	 */
	public boolean list;
	/**
	 * @uml.property  name="properties"
	 */
	private List<PropertyBean> properties;
	// we don't need here the fieldForms because the groupInstance will have the
	// fieldInstances which have their formFiels


	@Override
	public String toString()
	{
		return label;
	}
	
	
	@Override
	public String toXml()
	{
        return null;
	}

}

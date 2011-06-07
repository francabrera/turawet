package com.turawet.beedroid.beans;

import java.util.Iterator;
import java.util.List;

/**
 * @class TextFieldBean: Represents a TextField
 * 
 * @param id: [Inherited] The id of the Field
 * @param value: the text
 * @param formField: The FormField of the current InstanceField
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @autor Romén Rodríguez Gil
 * 
 */
public class TextFieldBean extends GenericInstanceFieldBean {
	public Integer id;
	public String value;

	public TextFieldBean(int order, FormFieldBean formField) {
		super(order, formField);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toXml() {
		// TO-DO- If the id is seted we are updating, not creating a new insance
		String temp = "<instancefield><id/><value>"+value+"</value><order>"+order+"</order>"+formField.toXml()+"</instancefield>";

		return temp;
	}
	
}

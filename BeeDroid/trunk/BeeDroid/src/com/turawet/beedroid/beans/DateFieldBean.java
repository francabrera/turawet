package com.turawet.beedroid.beans;

import java.io.Writer;

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
public class DateFieldBean extends GenericInstanceFieldBean {
	/**
	 * @uml.property  name="dayValue"
	 */
	private int dayValue;
	/**
	 * @uml.property  name="monthValue"
	 */
	private int monthValue;
	/**
	 * @uml.property  name="yearValue"
	 */
	private int yearValue;

	public DateFieldBean(int order, FormFieldBean formField) {
		super(order, formField);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void toXml(Writer writer) {
		// TO-DO- If the id is seted we are updating, not creating a new insance
		String temp = "<instancefield><id/><value>" +
				"<day>"+dayValue+"</day><month>"+monthValue+"</month><year>"+yearValue+"</year>" +
				"</value><order>"+order+"</order>"/*+formField.toXml()*/+"</instancefield>";

	}
	
}

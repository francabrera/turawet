package com.turawet.beedroid.beans;

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
	public Integer id;
	public Integer dayValue;
	public Integer monthValue;
	public Integer yearValue;
	
	@Override
	public String toXml() {
		// TO-DO- If the id is seted we are updating, not creating a new insance
		String temp = "<instancefield><id/><value>" +
				"<day>"+dayValue+"</day><month>"+monthValue+"</month><year>"+yearValue+"</year>" +
				"</value><order>"+order+"</order>"+formField.toXml()+"</instancefield>";

		return temp;
	}
	
}

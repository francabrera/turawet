package com.turawet.beedroid.beans;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import com.turawet.beedroid.constants.Cte.XmlEnumTags;
import com.turawet.beedroid.constants.Cte.XmlTags;

import android.util.Xml;

/**
 * @class TextFieldBean: Represents a TextField
 * 
 * @param instanceFieldId
 *           : [Inherited] The id of the Field
 * @param value
 *           : the text
 * @param formField
 *           : The FormField of the current InstanceField
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public class NumericFieldBean extends GenericInstanceFieldBean
{
	/**
	 * @uml.property name="value"
	 */
	private int	number;
	
	public NumericFieldBean(int order, FormFieldBean formField)
	{
		super(order, formField);
		number = -1;
	}

	/**
	 * @return the text
	 */
	public int getNumber()
	{
		return number;
	}
	
	/**
	 * @param text
	 *           the text to set
	 */
	public void setNumber(int number)
	{
		this.number = number;
	}
		
	@Override
	public void toXml(Writer writer) throws IllegalArgumentException, IllegalStateException, IOException
	{
		serializer = Xml.newSerializer();
		serializer.setOutput(writer);
		serializer.startTag(XmlTags.namespace, XmlEnumTags.field.toString());
		serializer.startTag(XmlTags.namespace, XmlEnumTags.id.toString());
		serializer.endTag(XmlTags.namespace, XmlEnumTags.id.toString());
		
		serializer.startTag(XmlTags.namespace, XmlEnumTags.value.toString());
		serializer.text(String.valueOf(number));
		serializer.endTag(XmlTags.namespace, XmlEnumTags.value.toString());
		serializer.startTag(XmlTags.namespace, XmlEnumTags.order.toString());
		serializer.text(String.valueOf(order));
		serializer.endTag(XmlTags.namespace, XmlEnumTags.order.toString());
		

		serializer.startTag(XmlTags.namespace, XmlEnumTags.formfieldid.toString());
		serializer.text(String.valueOf(getFormField().getId()));
		serializer.endTag(XmlTags.namespace, XmlEnumTags.formfieldid.toString());
		
		serializer.endTag(XmlTags.namespace, XmlEnumTags.field.toString());
		serializer.flush();
	}
}

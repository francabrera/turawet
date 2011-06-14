package com.turawet.beedroid.beans;

import java.io.IOException;
import java.io.Writer;

import android.util.Xml;

import com.turawet.beedroid.constants.Cte.XmlEnumTags;
import com.turawet.beedroid.constants.Cte.XmlTags;

/**
 * @class TextFieldBean: Represents a TextField
 * 
 * @param id
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
public class RadioFieldBean extends GenericInstanceFieldBean
{
	private int value;
	/**
	 * @param order
	 * @param formField
	 */
	public RadioFieldBean(int order, FormFieldBean formField)
	{
		super(order, formField);
		value = -1;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the value
	 */
	public int getValue()
	{
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value)
	{
		this.value = value;
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

		serializer.text(getFormField().getFieldOptions().get(value).getValue());

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

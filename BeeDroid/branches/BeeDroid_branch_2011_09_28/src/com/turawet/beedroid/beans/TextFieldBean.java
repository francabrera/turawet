package com.turawet.beedroid.beans;

import java.io.IOException;
import java.io.Writer;

import com.turawet.beedroid.constants.Cte.XmlEnumTags;
import com.turawet.beedroid.constants.Cte.XmlTags;

import android.util.Xml;

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
public class TextFieldBean extends GenericInstanceFieldBean
{
	/**
	 * @uml.property name="value"
	 */
	private String	text;
	
	public TextFieldBean(int order, FormFieldBean formField)
	{
		super(order, formField);
		text = "";
	}

	/**
	 * @return the text
	 */
	public String getText()
	{
		return text;
	}
	
	/**
	 * @param text
	 *           the text to set
	 */
	public void setText(String text)
	{
		this.text = text;
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
		serializer.text(text);
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

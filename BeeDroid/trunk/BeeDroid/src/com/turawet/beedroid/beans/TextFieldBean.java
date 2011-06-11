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
	
	@Override
	public void toXml(Writer writer) throws IllegalArgumentException, IllegalStateException, IOException
	{
		serializer = Xml.newSerializer();
		serializer.setOutput(writer);
		serializer.startTag(XmlTags.namespace, XmlEnumTags.instancefield.toString());
		String temp = "<instancefield><id/><value>" + text + "</value><order>" + order + "</order>" + /*
																																	 * formField
																																	 * .
																																	 * toXml
																																	 * (
																																	 * writer
																																	 * )
																																	 * +
																																	 */"</instancefield>";
		
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
	
}

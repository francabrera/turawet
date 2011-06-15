package com.turawet.beedroid.beans;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

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
public class DateFieldBean extends GenericInstanceFieldBean
{
	private Date	date;

	/**
	 * @param order
	 * @param formField
	 */
	public DateFieldBean(int order, FormFieldBean formField)
	{
		super(order, formField);
		date = new Date();
	}

	/**
	 * @return the date
	 */
	public Date getDate()
	{
		return date;
	}
	
	/**
	 * @param date
	 *           the date to set
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}
	
	/**
	 * @return the dayValue
	 */
	public int getDay()
	{
		return date.getDate();
	}
	
	/**
	 * @param dayValue
	 *           the dayValue to set
	 */
	public void setDay(int day)
	{
		date.setDate(day);
	}
	
	/**
	 * @return the monthValue
	 */
	public int getMonth()
	{
		return date.getMonth() + 1;
	}
	
	/**
	 * @param monthValue
	 *           the monthValue to set
	 */
	public void setMonth(int month)
	{
		date.setMonth(month);
	}
	
	/**
	 * @return the yearValue
	 */
	public int getYear()
	{
		return date.getYear();
	}
	
	/**
	 * @param yearValue
	 *           the yearValue to set
	 */
	public void setYearValue(int year)
	{
		date.setYear(year);
	}
	
	public String getLocaleDate()
	{
		return date.toLocaleString();
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
		
		serializer.startTag(XmlTags.namespace, XmlEnumTags.day.toString());
		serializer.text(String.valueOf(getDay()));
		serializer.endTag(XmlTags.namespace, XmlEnumTags.day.toString());


		serializer.startTag(XmlTags.namespace, XmlEnumTags.month.toString());
		serializer.text(String.valueOf(getMonth()));
		serializer.endTag(XmlTags.namespace, XmlEnumTags.month.toString());

		
		serializer.startTag(XmlTags.namespace, XmlEnumTags.year.toString());
		serializer.text(String.valueOf(getYear()));
		serializer.endTag(XmlTags.namespace, XmlEnumTags.year.toString());

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

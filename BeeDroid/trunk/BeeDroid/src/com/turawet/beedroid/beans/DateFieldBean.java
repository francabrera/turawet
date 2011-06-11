package com.turawet.beedroid.beans;

import java.io.Writer;
import java.util.Date;

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
	
	public DateFieldBean(int order, FormFieldBean formField)
	{
		super(order, formField);
		date = new Date();
		// TODO Auto-generated constructor stub
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
		return date.getMonth();
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
	public void toXml(Writer writer)
	{
		// TO-DO- If the id is seted we are updating, not creating a new insance
		String temp = "<instancefield><id/><value>" + "<day>" + date.getDate() + "</day><month>" + date.getMonth() + "</month><year>" + date.getYear() + "</year>" + "</value><order>" + order + "</order>"/*
																																																																			 * +
																																																																			 * formField
																																																																			 * .
																																																																			 * toXml
																																																																			 * (
																																																																			 * )
																																																																			 */+ "</instancefield>";
		
	}
	
}

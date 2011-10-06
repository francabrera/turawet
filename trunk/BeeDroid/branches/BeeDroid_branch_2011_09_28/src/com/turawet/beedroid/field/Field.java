package com.turawet.beedroid.field;

import android.content.Context;

import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.dao.Section;
import com.turawet.beedroid.exception.IllegalFieldTypeException;
import com.turawet.beedroid.exception.IllegalValueForFieldException;
import com.turawet.beedroid.field.misc.Properties;
import com.turawet.beedroid.field.misc.Property;
import com.turawet.beedroid.field.view.FieldView;
import com.turawet.beedroid.field.view.FieldViewFactory;

public abstract class Field
{
	protected String		id;
	protected Section		section;
	protected String		label;
	protected boolean		required;
	protected Properties	properties;
	protected FieldView	view;
	
	public Field()
	{
		id = "-1";
		label = "";
		required = false;
		properties = new Properties();
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getId()
	{
		return id;
	}
	
	public Section getSection()
	{
		return section;
	}
	
	public void setSection(Section section)
	{
		this.section = section;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public void setLabel(String label)
	{
		this.label = label;
	}
	
	public void setRequired(boolean required)
	{
		this.required = required;
	}
	
	public boolean isRequired()
	{
		return this.required;
	}
	
	public void addProperty(Property property)
	{
		properties.addProperty(property);
	}
	
	public Property getPropertyAt(int position)
	{
		return properties.getPropertyAt(position);
	}
	
	protected FieldView obtainAViewForThisField(Context context) throws IllegalFieldTypeException
	{
		return FieldViewFactory.makeFormFieldView(context, getType());
	}
	
	public abstract FieldView getFieldAsView(Context context) throws IllegalFieldTypeException;
	
	public abstract void setValue(Object value) throws IllegalValueForFieldException;
	
	public abstract Object getValue();
	
	public abstract FieldType getType();
	
}

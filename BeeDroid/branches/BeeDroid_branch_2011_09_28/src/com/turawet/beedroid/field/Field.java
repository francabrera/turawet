package com.turawet.beedroid.field;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.constants.Cte.XmlEnumTags;
import com.turawet.beedroid.exception.IllegalFieldTypeException;
import com.turawet.beedroid.exception.IllegalValueForFieldException;
import com.turawet.beedroid.exception.NullFieldLabelException;
import com.turawet.beedroid.exception.NullSectionTitleExcpetion;
import com.turawet.beedroid.field.misc.Property;
import com.turawet.beedroid.field.view.FieldView;
import com.turawet.beedroid.field.view.FieldViewFactory;
import com.turawet.beedroid.forms.Section;
import com.turawet.beedroid.xml.XmlConverter;
import com.turawet.beedroid.xml.XmlConvertible;

public abstract class Field implements XmlConvertible
{
	protected String				instanceFieldId;
	protected String				formFieldId;
	protected Section				section;
	protected String				label;
	protected boolean				required;
	protected List<Property>	properties;
	protected FieldView			view;
	protected int					order;
	
	public Field()
	{
		instanceFieldId = "";
		formFieldId = "";
		label = "";
		required = false;
		properties = new ArrayList<Property>();
	}
	
	public void setInstanceFieldId(String id)
	{
		this.instanceFieldId = id;
	}
	
	public String getInstanceFieldId()
	{
		return instanceFieldId;
	}
	
	public String getFormFieldId()
	{
		return formFieldId;
	}
	
	public void setFormFieldId(String formFieldId)
	{
		this.formFieldId = formFieldId;
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
	
	public String getOrder()
	{
		return String.valueOf(order);
	}
	
	public void setOrder(int order)
	{
		this.order = order;
	}
	
	public void addProperty(Property property)
	{
		properties.add(property);
	}
	
	public Property getPropertyAt(int position)
	{
		return properties.get(position);
	}
	
	protected FieldView obtainAViewForThisField(Context context) throws IllegalFieldTypeException
	{
		return FieldViewFactory.makeFormFieldView(context, getType());
	}
	
	final protected void addFormFieldId(XmlConverter converter) throws IllegalArgumentException, IllegalStateException, IOException
	{
		converter.addElement(XmlEnumTags.formfieldid, getFormFieldId());
	}
	
	final protected void addOrder(XmlConverter converter) throws IllegalArgumentException, IllegalStateException, IOException
	{
		converter.addElement(XmlEnumTags.order, getOrder());
	}
	
	final protected void addInstanceFieldId(XmlConverter converter) throws IllegalArgumentException, IllegalStateException, IOException
	{
		converter.addElement(XmlEnumTags.id, getInstanceFieldId());
	}
	
	final protected void addSectionId(XmlConverter converter) throws IllegalArgumentException, IllegalStateException, IOException
	{
		converter.addElement(XmlEnumTags.section, getSection().getId());
	}
	
	final protected void addFieldValue(XmlConverter converter) throws IllegalArgumentException, IllegalStateException, IOException
	{
		converter.addElement(XmlEnumTags.value, getValue());
	}
	
	public void convertToXml(XmlConverter converter) throws IllegalArgumentException, IllegalStateException, IOException
	{
		converter.startElement(XmlEnumTags.field);
		addInstanceFieldId(converter);
		addSectionId(converter);
		addOrder(converter);
		addFormFieldId(converter);
		addFieldValue(converter);
		converter.endElement(XmlEnumTags.field);
	}
	
	public abstract FieldView getFieldAsView(Context context) throws IllegalFieldTypeException, NullSectionTitleExcpetion, NullFieldLabelException;
	
	public abstract String getValue();
	
	public abstract FieldType getType();
	
	public abstract void readValueFromView() throws IllegalValueForFieldException;
}

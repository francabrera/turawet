package com.turawet.beedroid.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.turawet.beedroid.beans.PropertyBean;
import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.constants.Cte.XmlEnumTags;
import com.turawet.beedroid.dao.Form;
import com.turawet.beedroid.dao.Instance;
import com.turawet.beedroid.dao.Section;
import com.turawet.beedroid.exception.IllegalFieldTypeException;
import com.turawet.beedroid.field.Field;
import com.turawet.beedroid.field.FieldFactory;
import com.turawet.beedroid.field.FieldFactoryImpl;
import com.turawet.beedroid.field.OptionField;
import com.turawet.beedroid.field.misc.Option;
import com.turawet.beedroid.field.misc.Property;

public class XmlToInstanceParserHandler extends DefaultHandler
{
	
	private Instance			instance;
	private Section			currentSection;
	private Field				currentField;
	private FieldFactory		fieldFactory;
	private Property			currentProperty;
	
	private StringBuilder	buffer;
	private boolean			insideFieldTag;
	private boolean			buffering;
	private boolean			insideSectionTag;
	private boolean			insidePropertyTag;
	private boolean			insideOptionTag;
	private Option				currentOption;
	
	public void startDocument() throws SAXException
	{
		buffer = new StringBuilder();
		instance = new Instance();
		fieldFactory = new FieldFactoryImpl();
		
		insideSectionTag = false;
		insideFieldTag = false;
		insidePropertyTag = false;
		insideOptionTag = false;
	}
	
	@Override
	public void endDocument() throws SAXException
	{
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String qName, String localName, Attributes attributes) throws SAXException
	{
		if (!insideSectionTag && localName.equals(XmlEnumTags.id.toString()))
		{
			buffering = true;
		}
		else if (!insideSectionTag && localName.equals(XmlEnumTags.geolocalized.toString()))
		{
			instance.makeInstanceGeolocalized();
		}
		else if (localName.equals(XmlEnumTags.section.toString()))
		{
			currentSection = new Section();
			insideSectionTag = true;
		}
		else if (localName.equals(XmlEnumTags.field.toString()))
		{
			createNewFieldByType(attributes);
			insideFieldTag = true;
		}
		else if (insideFieldTag && localName.equals(XmlEnumTags.property.toString()))
		{
			currentProperty = new Property();
			insidePropertyTag = true;
		}
		else if (insideFieldTag && localName.equals(XmlEnumTags.option.toString()))
		{
			currentOption = new Option();
			insideOptionTag = true;
		}
		else if (insideSectionTag && !insideFieldTag && localName.equals(XmlEnumTags.id.toString()))
		{
			buffering = true;
		}
		else if (insideSectionTag && !insideFieldTag && localName.equals(XmlEnumTags.name.toString()))
		{
			buffering = true;
		}
		else if (insideSectionTag && insideFieldTag && localName.equals(XmlEnumTags.id.toString()))
		{
			buffering = true;
		}
		else if (insideSectionTag && insideFieldTag && localName.equals(XmlEnumTags.label.toString()))
		{
			buffering = true;
		}
		else if (insidePropertyTag && localName.equals(XmlEnumTags.name.toString()))
		{
			buffering = true;
		}
		else if (insidePropertyTag && localName.equals(XmlEnumTags.value.toString()))
		{
			buffering = true;
		}
		else if (insideOptionTag && localName.equals(XmlEnumTags.value.toString()))
		{
			buffering = true;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String qName, String localName) throws SAXException
	{
		if (!insideSectionTag && localName.equals(XmlEnumTags.id.toString()))
		{
			instance.setFormId(buffer.toString());
			clearBuffer();
		}
		else if (insideSectionTag && localName.equals(XmlEnumTags.section.toString()))
		{
			instance.addSection(currentSection);
			insideSectionTag = false;
		}
		else if (insideFieldTag && localName.equals(XmlEnumTags.field.toString()))
		{
			currentField.setSection(currentSection);
			instance.addField(currentField);
			insideFieldTag = false;
		}
		else if (insideFieldTag && localName.equals(XmlEnumTags.property.toString()))
		{
			currentField.addProperty(currentProperty);
			insidePropertyTag = false;
		}
		else if (insideSectionTag && !insideFieldTag && localName.equals(XmlEnumTags.id.toString()))
		{
			currentSection.setId(buffer.toString());
			clearBuffer();
		}
		else if (insideSectionTag && !insideFieldTag && localName.equals(XmlEnumTags.name.toString()))
		{
			currentSection.setName(buffer.toString());
			clearBuffer();
		}
		
		else if (insideSectionTag && insideFieldTag && localName.equals(XmlEnumTags.id.toString()))
		{
			currentField.setId(buffer.toString());
			clearBuffer();
		}
		else if (insideFieldTag && !insideOptionTag && localName.equals(XmlEnumTags.label.toString()))
		{
			currentField.setLabel(buffer.toString());
			clearBuffer();
		}
		else if (insideFieldTag && localName.equals(XmlEnumTags.required.toString()))
		{
			currentField.setRequired(true);
		}
		else if (insidePropertyTag && localName.equals(XmlEnumTags.name.toString()))
		{
			currentProperty.setName(buffer.toString());
			clearBuffer();
		}
		else if (insidePropertyTag && !insideOptionTag && localName.equals(XmlEnumTags.value.toString()))
		{
			currentProperty.setValue(buffer.toString());
			clearBuffer();
		}
		else if (insideFieldTag && localName.equals(XmlEnumTags.option.toString()))
		{
			((OptionField) currentField).addOption(currentOption);
			insideOptionTag = false;
		}
		else if (insideFieldTag && insideOptionTag && localName.equals(XmlEnumTags.label.toString()))
		{
			currentOption.setLabel(buffer.toString());
			clearBuffer();
		}
		else if (insideFieldTag && insideOptionTag && localName.equals(XmlEnumTags.value.toString()))
		{
			currentOption.setValue(buffer.toString());
			clearBuffer();
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		if (buffering)
			buffer.append(ch, start, length);
	}
	
	final private void clearBuffer()
	{
		buffering = false;
		buffer.setLength(0);
	}
	
	private void createNewFieldByType(Attributes attributes)
	{
		try
		{
			currentField = fieldFactory.makeField(FieldType.valueOf(attributes.getValue(XmlEnumTags.type.toString())));
		}
		catch (IllegalFieldTypeException e)
		{
			e.printStackTrace();
		}
	}
	
	public Instance getInstance()
	{
		return instance;
	}
}

package com.turawet.beedroid.parser;

/**
 * 
 */

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * @author nicopernas
 * 
 */
public class FormSaxParserHandler extends DefaultHandler
{
	/**
	 *
	 */	
	private ArrayList<FieldContainer>	fieldElements;
	private StringBuilder					buffer;
	private FieldContainer					temporaryField;
	private boolean							buffering;
	private boolean							fieldOpen;
	private String								propertyName;
	private String								propertyValue;
	private boolean							sectionOpen;
	
	@Override
	public void startDocument() throws SAXException
	{
		fieldElements = new ArrayList<FieldContainer>();
		buffer = new StringBuilder();
		
		buffering = false;
		fieldOpen = false;
		sectionOpen = false;
		
		propertyName = null;
		propertyValue = null;
	}
	
	@Override
	public void endDocument() throws SAXException
	{
	}
	
	@Override
	public void startElement(String uri, String qName, String localName, Attributes attributes) throws SAXException
	{
		/*
		 * A section its beign opened
		 */
		if (localName.equalsIgnoreCase("section"))
		{
			sectionOpen = true;
		}
		/*
		 * Tag 'name' of a section it's beign opened
		 */
		else if (sectionOpen && !fieldOpen && localName.equalsIgnoreCase("name"))
		{
			temporaryField = new FieldContainer();
			temporaryField.setType("SECTION");
			buffering = true;
		}
		/*
		 * A new field it's beign opened
		 */
		else if (sectionOpen && localName.equalsIgnoreCase("field"))
		{
			temporaryField = new FieldContainer();
			fieldOpen = true;
		}
		/*
		 * Tags of a field
		 */
		else if (sectionOpen && fieldOpen && (localName.equalsIgnoreCase("id") || localName.equalsIgnoreCase("label") || localName.equalsIgnoreCase("type") || localName.equalsIgnoreCase("name") || localName.equalsIgnoreCase("value")))
		{
			buffering = true;
		}
	}
	
	@Override
	public void endElement(String uri, String qName, String localName) throws SAXException
	{
		/*
		 * Closing section tag
		 */
		if (localName.equalsIgnoreCase("section"))
		{
			sectionOpen = false;
		}
		/*
		 * Tag 'name' of a section. No more data for 'section', we insert him into
		 * the list
		 */
		else if (sectionOpen && !fieldOpen && localName.equalsIgnoreCase("name"))
		{
			temporaryField.setLabel(buffer.toString().trim());
			fieldElements.add(temporaryField);
			clearBuffer();
		}
		/*
		 * Closing 'field' tag . We put him into the list
		 */
		else if (sectionOpen && fieldOpen && localName.equalsIgnoreCase("field"))
		{
			fieldElements.add(temporaryField);
			fieldOpen = false;
		}
		/*
		 * Closing 'id' tag of a field
		 */
		else if (sectionOpen && fieldOpen && localName.equalsIgnoreCase("id"))
		{
			temporaryField.setId(buffer.toString().trim());
			clearBuffer();
		}
		/*
		 * Closing 'label' tag of a field
		 */
		else if (sectionOpen && fieldOpen && localName.equalsIgnoreCase("label"))
		{
			temporaryField.setLabel(buffer.toString().trim());
			clearBuffer();
		}
		/*
		 * Closing 'type' tag of a field
		 */
		else if (sectionOpen && fieldOpen && localName.equalsIgnoreCase("type"))
		{
			temporaryField.setType(buffer.toString().trim());
			clearBuffer();
		}
		/*
		 * If 'required' tag exists, this field it's mandatory
		 */
		else if (sectionOpen && fieldOpen && localName.equalsIgnoreCase("required"))
		{
			temporaryField.setRequired(true);
			clearBuffer();
		}
		/*
		 * Name of a property of a field
		 */
		else if (sectionOpen && fieldOpen && localName.equalsIgnoreCase("name"))
		{
			propertyName = buffer.toString().trim();
			clearBuffer();
		}
		/*
		 * Value of a property of a field. Add the property to the field
		 */
		else if (sectionOpen && fieldOpen && localName.equalsIgnoreCase("value"))
		{
			propertyValue = buffer.toString().trim();
			temporaryField.addProperty(propertyName, propertyValue);
			clearBuffer();
		}
		
	}
	
	/**
	 * SAX call this method when found text between some tags
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		if (buffering)
			buffer.append(ch, start, length);
	}
	
	/**
	 * Clear the buffer and not keeps buffering
	 */
	final private void clearBuffer()
	{
		buffering = false;
		buffer.setLength(0);
	}
	
	/**
	 * @return
	 */
	public List<FieldContainer> getFields()
	{
		return fieldElements;
	}
}

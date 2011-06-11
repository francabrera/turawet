package com.turawet.beedroid.parser;

/**
 * 
 */
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.turawet.beedroid.beans.DateFieldBean;
import com.turawet.beedroid.beans.FormBean;
import com.turawet.beedroid.beans.FormFieldBean;
import com.turawet.beedroid.beans.GenericInstanceFieldBean;
import com.turawet.beedroid.beans.InstanceBean;
import com.turawet.beedroid.beans.PropertyBean;
import com.turawet.beedroid.beans.SectionBean;
import com.turawet.beedroid.beans.TextFieldBean;
import com.turawet.beedroid.constants.Cte.FieldType;

/**
 * @class FormSaxParserHandler: Our Sax Parser Handler
 * 
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public class XmlToBeansParserHandler extends DefaultHandler
{
	/**
	 *
	 */
	/* Result */
	private InstanceBean					instance;
	/* Aux */
	private StringBuilder				buffer;
	private boolean						buffering;
	private boolean						inProperty;
	private boolean						inField;
	private boolean						inSection;
	/* Temp atributes */
	private FormBean						tempForm;
	private SectionBean					tempSection;
	private FormFieldBean				tempFormField;
	private PropertyBean					tempProperty;
	private GenericInstanceFieldBean	tempInstanceField;
	
	@Override
	public void startDocument() throws SAXException
	{
		/* Initializing */
		instance = new InstanceBean();
		tempForm = new FormBean();
		buffer = new StringBuilder();
		buffering = false;
		inField = false;
		inSection = false;
	}
	
	@Override
	public void endDocument() throws SAXException
	{
	}
	
	@Override
	public void startElement(String uri, String qName, String localName, Attributes attributes) throws SAXException
	{
		/* A section is being opened */
		if (localName.equalsIgnoreCase("section"))
		{
			inSection = true;
			tempSection = new SectionBean();
		}
		/* A field is being opened */
		else if (inSection && localName.equalsIgnoreCase("field"))
		{
			inField = true;
			tempFormField = new FormFieldBean();
		}
		/* A field is being opened */
		else if (inSection && inField && localName.equalsIgnoreCase("property"))
		{
			inProperty = true;
			tempProperty = new PropertyBean();
		}
		/*
		 * Internal tags
		 */
		else
		{
			buffering = true;
		}
	}
	
	@Override
	public void endElement(String uri, String qName, String localName) throws SAXException
	{
		/* Closing Meta */
		if (localName.equalsIgnoreCase("meta"))
		{
			instance.setForm(tempForm);
		}
		/* Closing Section */
		if (localName.equalsIgnoreCase("section"))
		{
			inSection = false;
			// NOW WE HAVE TO ADD THE SECTION TO THE INSTANCE
			instance.addSection(tempSection);
		}
		/* Closing Field */
		else if (localName.equalsIgnoreCase("field"))
		{
			inField = false;
			FieldType type;
			try
			{
				type = tempFormField.getType();
			}
			catch (Exception e)
			{
				type = FieldType.TEXT;
			}
			switch (type)
			{
				case TEXT:
					// TO-DO: Arguments. This constructor exists in the
					// GenericInstance, but there seems not to be inheritance
					// the first attribute is the order
					tempInstanceField = new TextFieldBean(1, tempFormField);
					break;
				case DATE:
					tempInstanceField = new DateFieldBean(1, tempFormField); // TO-DO:
																								// Arguments
					break;
			}
			// NOW WE HAVE TO ADD THE NEW INSTANCEFIELD TO THE CURRENT SECTION
			tempSection.addChild(tempInstanceField);
			
		}
		/* Closing Property */
		if (localName.equalsIgnoreCase("property"))
		{
			inProperty = false;
			// NOW WE HAVE TO ADD THE PROPERTY TO THE FORMFIELD
			tempFormField.addProperty(tempProperty);
		}
		/************************************/
		/* Meta */
		else if (!inSection && localName.equalsIgnoreCase("id"))
		{
			String value = buffer.toString().trim();
			if (!value.isEmpty())
				tempForm.setId(Integer.parseInt(value));
			/* ByDefaultID */
			else
			{
				tempForm.setId(-1);
			}
			clearBuffer();
		}
		else if (!inSection && localName.equalsIgnoreCase("name"))
		{
			tempForm.setName(buffer.toString().trim());
			clearBuffer();
		}
		else if (!inSection && localName.equalsIgnoreCase("version"))
		{
			String value = buffer.toString().trim();
			if (!value.isEmpty())
				tempForm.setVersion(Integer.parseInt(value));
			clearBuffer();
		}
		else if (!inSection && localName.equalsIgnoreCase("user"))
		{
			instance.setAuthoruser(buffer.toString().trim());
			clearBuffer();
		}
		/* Section */
		else if (inSection && !inField && localName.equalsIgnoreCase("id"))
		{
			String value = buffer.toString().trim();
			if (!value.isEmpty())
				tempSection.setId(Integer.parseInt(value));
			clearBuffer();
		}
		else if (inSection && !inField && localName.equalsIgnoreCase("name"))
		{
			tempSection.setName(buffer.toString().trim());
			clearBuffer();
		}
		/* Field */
		else if (inSection && inField && localName.equalsIgnoreCase("id"))
		{
			String value = buffer.toString().trim();
			if (!value.isEmpty())
				tempFormField.setId(Integer.parseInt(value));
			clearBuffer();
		}
		else if (inSection && inField && localName.equalsIgnoreCase("label"))
		{
			tempFormField.setLabel(buffer.toString().trim());
			clearBuffer();
		}
		else if (inSection && inField && localName.equalsIgnoreCase("type"))
		{
			tempFormField.setType(FieldType.valueOf(buffer.toString().trim()));
			clearBuffer();
		}
		else if (inSection && inField && localName.equalsIgnoreCase("required"))
		{
			tempFormField.setRequired(true);
			clearBuffer();
		}
		/* Property */
		else if (inSection && inField && inProperty && localName.equalsIgnoreCase("name"))
		{
			tempProperty.setName(buffer.toString().trim());
			clearBuffer();
		}
		else if (inSection && inField && inProperty && localName.equalsIgnoreCase("value"))
		{
			tempProperty.setValue(buffer.toString().trim());
			clearBuffer();
		}
		
		/* Once finished, we always have to clean the buffer */
		clearBuffer();
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
	public InstanceBean getInstance()
	{
		return instance;
	}
}

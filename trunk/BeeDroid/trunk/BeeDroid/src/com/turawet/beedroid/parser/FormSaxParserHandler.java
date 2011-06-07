package com.turawet.beedroid.parser;

/**
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.turawet.beedroid.beans.DateFieldBean;
import com.turawet.beedroid.beans.FormFieldBean;
import com.turawet.beedroid.beans.GenericInstanceFieldBean;
import com.turawet.beedroid.beans.InstanceBean;
import com.turawet.beedroid.beans.SectionBean;
import com.turawet.beedroid.beans.TextFieldBean;


/**
 * @class FormSaxParserHandler: Our Sax Parser Handler
 * 
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @autor Romén Rodríguez Gil
 * 
 */
public class FormSaxParserHandler extends DefaultHandler
{
	/**
	 *
	 */	
	/* Result */
	private InstanceBean instance;
	/* Aux */
    private StringBuilder buffer;
    private boolean buffering;
    private HashMap<String, Integer> typeMap;
    private boolean inField;
    private boolean inSection;
    /* Temp atributes */
    private SectionBean tempSection;
    private FormFieldBean tempFormField;
    private GenericInstanceFieldBean tempInstanceField;



	
	@Override
	public void startDocument() throws SAXException	{
		typeMap = new HashMap<String, Integer>();
		typeMap.put("TEXT", 1);
		typeMap.put("DATE", 2);
	}
	
	@Override
	public void endDocument() throws SAXException {
	}
	
	@Override
	public void startElement(String uri, String qName, String localName, Attributes attributes) throws SAXException	{
		/* A section is being opened */
		if (localName.equalsIgnoreCase("section")) {
			inSection = true;
			tempSection = new SectionBean();
		}

		/* A field is being opened */
		else if (inSection && localName.equalsIgnoreCase("field")) {
			inField = true;
			tempFormField = new FormFieldBean();
		}
		/*
		 * Tags of a field
		 */
		else {
			buffering = true;
		}
	}
	
	@Override
	public void endElement(String uri, String qName, String localName) throws SAXException
	{
		/* Closing Section */
		if (localName.equalsIgnoreCase("section")) {
			inSection = false;
			//NOW WE HAVE TO ADD THE SECTION TO THE INSTANCE
		}
		/* Closing Field */
		else if (localName.equalsIgnoreCase("field")) {
			inField= false;
			switch (typeMap.get(tempFormField.getType())) {
				case 1: 
					// TO-DO: Arguments. This constructor exists in the GenericInstance, but there seems not to be inheritance
					tempInstanceField = new TextFieldBean(1, tempFormField);
				case 2: 
					tempInstanceField = new DateFieldBean(tempFormField); // TO-DO: Arguments
				break;
			}
			// NOW WE HAVE TO ADD THE NEW INSTANCEFIELD TO THE CURRENT SECTION
			
		}
		/* Section */
		else if (inSection && !inField && localName.equalsIgnoreCase("id")) {
			tempSection.setId(Integer.parseInt(buffer.toString().trim()));
			clearBuffer();
		}
		else if (inSection && !inField && localName.equalsIgnoreCase("name")) {
			tempSection.setName(buffer.toString().trim());
			clearBuffer();
		}
		/* Field */
		else if (inSection && inField && localName.equalsIgnoreCase("id")) {
			tempFormField.setId(Integer.parseInt(buffer.toString().trim()));
			clearBuffer();
		}
		else if (inSection && inField && localName.equalsIgnoreCase("label")) {
			tempFormField.setLabel(buffer.toString().trim());
			clearBuffer();
		}
		else if (inSection && inField && localName.equalsIgnoreCase("type")) {
			tempFormField.setLabel(buffer.toString().trim());
			clearBuffer();
		}
		else if (inSection && inField && localName.equalsIgnoreCase("required")) {
			tempFormField.setRequired(true);
			clearBuffer();
		}
		
		
		/* Once finished, we always have to clean the buffer*/
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
	public InstanceBean InstanceBean()
	{
		return instance;
	}
}

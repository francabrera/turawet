/**
 * 
 */
package com.turawet.beedroid.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


import android.util.Log;

/**
 * @author nicopernas
 * 
 */
public class XmlToViewsParser
{
	/**
	 *
	 */
	private FormSaxParserHandler	handler;
	
	public XmlToViewsParser(String xml) throws SAXException, ParserConfigurationException, IOException
	{
		/*
		 * handler = new XmlFormHandler();
		 * SAXParserFactory factory = SAXParserFactory.newInstance();
		 * SAXParser parser = factory.newSAXParser();
		 * XMLReader xmlReader = parser.getXMLReader();
		 * xmlReader.setContentHandler(handler);
		 * Reader reader = new StringReader(xml);
		 * InputSource inputSource = new InputSource(reader);
		 * xmlReader.parse(inputSource);
		 */
	}
	
	/**
	 * 
	 * @param in
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 */
	public XmlToViewsParser(InputStream in) throws SAXException, ParserConfigurationException, IOException
	{
		handler = new FormSaxParserHandler();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		XMLReader xmlReader = parser.getXMLReader();
		xmlReader.setContentHandler(handler);
		InputSource inputSource = new InputSource(in);
		xmlReader.parse(inputSource);
	}

	/**
	 * 
	 * @return
	 */
	public List<FieldContainer> getFields()
	{
		return handler.getFields();
	}
	
	/**
	 * Método que mostrará todo el proceso de parsing por el LOG
	 * -------- BORRAR AL FINAL ------
	 */
	public void showParse()
	{
		List<FieldContainer> list = handler.getFields();
		for (FieldContainer field : list)
		{
			Log.d("Campo ", field.toString());
		}
		
	}
	
}

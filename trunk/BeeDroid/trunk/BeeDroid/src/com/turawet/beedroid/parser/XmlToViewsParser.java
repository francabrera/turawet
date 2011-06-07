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

import com.turawet.beedroid.beans.InstanceBean;


import android.util.Log;


/**
 * @class XmlToViewsParser
 * 
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @autor Romén Rodríguez Gil
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
	public InstanceBean getInstance()
	{
		return handler.getInstance();
	}
	
	
	/**
	 * Método que mostrará todo el proceso de parsing por el LOG
	 * -------- BORRAR AL FINAL ------
	 */
	public void showParse()
	{
		//TO-DO
		
	}
	
}

package com.turawet.beedroid.parser;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.turawet.beedroid.dao.Instance;

public class XmlToInstanceParser
{
	private XmlToInstanceParserHandler	handler;
	private XMLReader							xmlReader;
	
	public XmlToInstanceParser() throws ParserConfigurationException, SAXException
	{
		handler = new XmlToInstanceParserHandler();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		xmlReader = parser.getXMLReader();
		xmlReader.setContentHandler(handler);
	}
	
	public void parse(InputStream xmlToParse) throws SAXException, ParserConfigurationException, IOException
	{
		InputSource inputSource = new InputSource(xmlToParse);
		xmlReader.parse(inputSource);
	}

	public Instance getInstance()
	{
		return handler.getInstance();
	}
}

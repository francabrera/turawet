package com.turawet.beedroid.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.turawet.beedroid.dao.Form;
import com.turawet.beedroid.parser.XmlToBeansParserHandler;

public class FormFromXmlTest
{
	
	private static final String	formulariBreveXmlFile	= "assets/formulario_breve_v1.xml";
	private Form						form;
	
	@Before
	public void setUp() throws Exception
	{
		form = new Form();
	}
	
	@After
	public void tearDown() throws Exception
	{
	}
	
	@Test
	public void openFormTest() throws FileNotFoundException
	{
		FileInputStream xmlFile = new FileInputStream(formulariBreveXmlFile);
		assertNotNull(xmlFile);
	}
}

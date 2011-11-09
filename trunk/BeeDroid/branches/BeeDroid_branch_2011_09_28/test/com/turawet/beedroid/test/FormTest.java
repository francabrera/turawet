package com.turawet.beedroid.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.turawet.beedroid.forms.Form;

public class FormTest
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
	
	@Test
	public void formIdTest()
	{
		String id = "123";
		form.setId(id);
		assertEquals(id, form.getId());
	}
	
	@Test
	public void formNameTest()
	{
		String name = "formulario";
		form.setName(name);
		assertEquals(name, form.getName());
	}
	
	@Test
	public void formAuthorTest()
	{
		String author = "Nicolás Pernas Maradei";
		form.setAuthor(author);
		assertEquals(author, form.getAuthor());
	}
	
	@Test
	public void formCreationDateTest()
	{
		Date creationDate = new Date();
		form.setCreationDate(creationDate);
		assertEquals(creationDate, form.getCreationDate());
	}
	
	@Test
	public void formGeolocalizedTest()
	{
		boolean geolocalized = true;
		form.setGeolocalized(geolocalized);
		assertEquals(geolocalized, form.isGeolocalized());
	}
	
	@Test
	public void formVersionTest()
	{
		String version = "2";
		form.setVersion(version);
		assertEquals(version, form.getVersion());
	}
	
}
